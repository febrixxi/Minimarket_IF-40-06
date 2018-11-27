/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

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
public class cKasir {
    private Kasir k;
    private pembeli p;
    private Barang stok;
    private Barang belanja;
    private String msg;
    
    public cKasir(){
        k = new Kasir();
    }
    
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
            Logger.getLogger(cManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    } 
}
