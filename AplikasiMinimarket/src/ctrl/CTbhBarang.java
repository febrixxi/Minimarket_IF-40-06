/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

import GUI.MenambahBarang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Barang;
import model.Manager;

/**
 *
 * @author CakBin
 */
public class CTbhBarang implements ActionListener{
    private Manager m;
    private Barang b;
    private MenambahBarang mB;
    private String msg;
    
    public CTbhBarang(Manager _m){
        m = _m;
        b = new Barang();
        mB = new MenambahBarang();
        mB.addActionListener(this);
        mB.setVisible(true);
    }
    
    private boolean Validasi(){
        if(mB.getKode().length() != 10){
            msg = "Kode barang tidak 10 huruf";
            mB.setMsg(msg);
            return false;
        }else try {
            if(b.Search(mB.getKode())){
                msg = "Kode barang sudah ada";
                mB.setMsg(msg);
                return false;
            }else if(mB.getNama().isEmpty()){
                msg = "Nama barang kosong";
                mB.setMsg(msg);
                return false;
            }else{
                int a;
                try{
                    a = mB.getHarga();
                    return true;
                }catch(Exception e){
                    msg = "Harga harus berupa angka";
                    mB.setMsg(msg);
                    return false;
                }
            }
        } catch (SQLException ex) {
            msg = ex.getMessage();
            mB.setMsg(msg);
            return false;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object Source = e.getSource();
        if(Source.equals(mB.getbSubmit())){
            if(Validasi()){
                b.setKode(mB.getKode().toUpperCase());
                b.setNama(mB.getNama());
                b.setHarga(mB.getHarga());
                b.setJumlah(mB.getJumlah());
                msg = b.Add();
                mB.setMsg(msg);
                mB.EmptyAll();
            }
        }else if(Source.equals(mB.getbCancel())){
            mB.dispose();
            CMenuManager cM = new CMenuManager(m);
        }
    }
    
}
