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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Source = e.getSource();
        if(Source.equals(UI.getbSubmit())){
            msg="";
            UI.setMsg(msg);
            UI.tes();
        }else if(Source.equals(UI.getbNavData())){
            UI.reset();
        }else if(Source.equals(UI.getbLogout())){
            UI.dispose();
            clogin l = new clogin();
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if(e.getColumn()!=-1){
            if((e.getColumn() == 0)||(e.getColumn() == 2)){
                msg=Integer.toString(e.getColumn());
                UI.setMsg(msg);
            }
        }else{
            msg=Integer.toString(e.getColumn());
            UI.setMsg(msg);
        }
    }
}
