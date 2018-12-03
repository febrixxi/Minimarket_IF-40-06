/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

import GUI.KonfirmasiPembelian;
import GUI.PembelianUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import model.Barang;
import model.Kasir;
import model.pembeli;

/**
 *
 * @author CakBin
 */
public class CTransaksi implements ActionListener, TableModelListener{
    private Kasir k;
    private pembeli p;
    private Barang stok;
    private Barang belian;
    private PembelianUI UI;
    private KonfirmasiPembelian UI2;
    private String msg;
    
    public CTransaksi(Kasir _k){
        k = _k;
        p = new pembeli();
        p.setK(k);
        
        stok = new Barang();
        belian = new Barang();
        UI = new PembelianUI();
        UI2 = new KonfirmasiPembelian();
        
        UI.addActonListener(this);
        UI.addTableModelListener(this);
        
        UI2.addActionListener(this);
        
        UI.setVisible(true);
        UI2.setVisible(false);
        
        init();
    }
    
    private void init(){
        UI.setKasir(k.getNama());
        try {
            p.setLastId();
            UI.setPembeli(p.getId());
        } catch (SQLException ex) {
            msg = ex.getMessage();
            UI.setMsg(msg);
        }
    }
    
    private void update(int row) throws SQLException{
        belian = new Barang();
        if(row >= p.getDaftar().size()){
            if(belian.Search(UI.getSelectedKode(row).toUpperCase())){
                if(UI.getSelectedJumlah(row) <= belian.getJumlah()){
                    belian.setJumlah(UI.getSelectedJumlah(row));
                }else{
                    msg = "Stok tak mencukupi";
                    UI.setMsg(msg);
                }
                p.addBarang(belian);
                UI.setTabel(p.getDaftar());
                UI.setTotal(p.totalHarga());
            }else{
                msg = "Barang tak ditemukan";
                UI.setMsg(msg);
            }         
        }else{
            if(belian.Search(UI.getSelectedKode(row).toUpperCase())){
               if(UI.getSelectedJumlah(row) <= belian.getJumlah()){
                    belian.setJumlah(UI.getSelectedJumlah(row));
               }else{
                    msg = "Stok tak mencukupi";
                    UI.setMsg(msg);
                }
               p.getDaftar().set(row, belian);
               UI.setTabel(p.getDaftar());
               UI.setTotal(p.totalHarga());
            }else{
                msg = "Barang tak ditemukan";
                UI.setMsg(msg);
            }
        }
    }
    
    private void setKonfirmasi(){
        UI2.setTotal(p.totalHarga());
    }
    
    private boolean HitungKembalian(){
        try{
            int kembali = UI2.getBayar() - p.totalHarga();
            if(kembali >= 0){
                UI2.setKembali(kembali);
                return true;
            }else{
                msg = "Uang tak cukup";
                UI2.setMsg(msg);
                return false;
            }
        }catch(Exception e){
            msg = "Uang berupa angka";
            UI2.setMsg(msg);
            return false;
        }
    }
    
    private void proses() throws SQLException{
        if(HitungKembalian()){
            for (Barang daftar : p.getDaftar()) {
                stok.Search(daftar.getKode());
                stok.setJumlah(stok.getJumlah() - daftar.getJumlah());
                stok.Update();
            }
            msg = p.Add();
            UI2.setMsg(msg);
            UI2.getbConfirm().setVisible(false);
            next();
        }
    }
    private void next(){
        p.Next();
        UI.setPembeli(p.getId());
        UI.reset();
        UI.setMsg(null);
        UI.setTotal(p.totalHarga());
    }
    
    private void delete(){
        if(UI.getSelectedRow()<p.getDaftar().size()){
            p.getDaftar().remove(UI.getSelectedRow());
            UI.setTabel(p.getDaftar());
            UI.setTotal(p.totalHarga());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Source = e.getSource();
        if(Source.equals(UI.getbDelete())){
            delete();
        }else if(Source.equals(UI.getbSubmit())){
            setKonfirmasi();
            UI2.setVisible(true);
            UI2.getbConfirm().setVisible(true);
        }else if(Source.equals(UI.getbNavData())){
            UI.dispose();
            UI2.dispose();
            CLaporan cL = new CLaporan(k);
        }else if(Source.equals(UI.getbLogout())){
            UI.dispose();
            UI2.dispose();
            clogin l = new clogin();
        }
        
        else if(Source.equals(UI2.getbCancel())){
            UI2.setVisible(false);
        }else if(Source.equals(UI2.getbConfirm())){
            try {
                proses();
            } catch (SQLException ex) {
                msg = ex.getMessage();
                UI2.setMsg(msg);
            }
            
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if(e.getColumn()!=-1){
            if((e.getColumn() == 0)||(e.getColumn() == 2)){
                UI.setMsg(null);
                try {
                    update(e.getFirstRow());
                } catch (SQLException ex) {
                    msg="Database error";
                    UI.setMsg(msg);
                }
            }
        }
    }
}
