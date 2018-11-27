/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

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
public class cManager {
    private Manager m;
    private Kasir k;
    private Barang b;
    private pembeli pembelian;
    private String msg;
    
    public cManager(Manager _m){
        m = new Manager();
        m = _m;
        init();
    }
    
    private void init(){
        k = new Kasir();
        k.setMng(m);
        b = new Barang();
        msg = "";
    }
    
    private boolean verifikasiKasir(){
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
    
}
