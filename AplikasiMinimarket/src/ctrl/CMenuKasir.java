/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

import GUI.HomepageKasir;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Barang;
import model.Kasir;
import model.pembeli;

/**
 *
 * @author CakBin
 */
public class CMenuKasir implements ActionListener{
    private Kasir k;
    private HomepageKasir HK;
    
    public CMenuKasir(Kasir _k){
        k = _k;
        HK = new HomepageKasir();
        HK.addActionListener(this);
        HK.setVisible(true);
    }
    
    /*
    public void init(Kasir _k){
        k = _k;
        p = new pembeli();
        try{
            p.setLastId();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        stok = new Barang();
        belanja = new Barang();
        msg = "";
    }
    
    private boolean ValidasiPembelian(){
        return true;
    }
    
    private void savePembelian(){
        msg = p.Add();
    }
    
    private int totalPenjualan(){
        int total = 0;
        try {
            List<Barang> B;
            B = p.rekapBrgbyKsr(k.getId());
            for (Barang B1 : B) {
                total += B1.hargaTotal();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CMenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    } 
    */

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Source = e.getSource();
        if(Source.equals(HK.getbTransaksi())){
            
        }else if(Source.equals(HK.getbLaporan())){
            
        }else if(Source.equals(HK.getbLogout())){
            HK.dispose();
            clogin l = new clogin();
        }
    }
}
