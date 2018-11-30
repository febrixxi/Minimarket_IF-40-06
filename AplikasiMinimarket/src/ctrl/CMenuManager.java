/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

import GUI.HomepageManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Barang;
import model.Kasir;
import model.Manager;
import model.pembeli;

/**
 *
 * @author CakBin
 */
public class CMenuManager implements ActionListener{
    private Manager m;
    private HomepageManager HM;
    private CStok cek;
    //private pembeli pembelian;
    //private String msg;
    
    public CMenuManager(Manager _m){
        m = _m;
        HM = new HomepageManager();
        cek = new CStok();
        HM.addActionListener(this);
        HM.setVisible(true);
        
    }
    
    /* private boolean verifikasiKasir(){
        return true;
    }
    
    private boolean verifikasiBarang(){
        return true;
    }
    
    private int totalPenjualan(){
        int total = 0;
        try {
            List<Barang> B;
            B = pembelian.rekapBrg();
            for (Barang B1 : B) {
                total += B1.hargaTotal();
            }
        } catch (SQLException ex) {
            Logger.getLogger(cManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    private void addKasir(){
        msg = k.Add();
    }
    
    private void delKasir(){
        msg = k.Delete();
    }
    
    private void updateKasir(){
        msg = k.Update();
    }
    */

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Source = e.getSource();
        if(Source.equals(HM.getbPgw())){
            HM.dispose();
            CTbhPegawai Cpgw = new CTbhPegawai(m);
        }else if(Source.equals(HM.getbAddBrg())){
            HM.dispose();
            CTbhBarang Cbrg = new CTbhBarang(m);
        }else if(Source.equals(HM.getbCekBrg())){
            cek.Start();
        }else if(Source.equals(HM.getbLogout())){
            HM.dispose();
            cek.dispose();
            clogin l = new clogin();
        }
    }
}
