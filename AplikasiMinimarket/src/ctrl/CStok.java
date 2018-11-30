/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

import GUI.CekStok;
import GUI.CekStok2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Barang;

/**
 *
 * @author CakBin
 */
public class CStok implements ActionListener{
    private Barang b;
    private CekStok cS;
    private CekStok2 cS2;
    private String msg;
    
    public CStok(){
        b = new Barang();
        cS = new CekStok();
        cS2 = new CekStok2();
        cS.addActionListener(this);
        cS.setVisible(false);
        cS2.setVisible(false);
    }
    
    public void Start(){
        cS.setVisible(true);
    }
    
    public void dispose(){
        cS.dispose();
        cS2.dispose();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String kode = cS.getKode().toUpperCase();
        try {
            if(b.Search(kode)){
                cS2.setNama(b.getNama());
                cS2.setStok(Integer.toString(b.getJumlah()));
                cS2.setVisible(true);
            }else{
                msg = "Barang tak ditemukan";
                cS.setMsg(msg);
            }
        } catch (SQLException ex) {
            msg = ex.getMessage();
            cS.setMsg(msg);
        }
    }
    
}
