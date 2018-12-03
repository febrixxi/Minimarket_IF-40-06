/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

import GUI.LaporanTransaksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.List;
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
public class CLaporan implements ActionListener, FocusListener{
    private Kasir k;
    private pembeli p;
    private LaporanTransaksi UI;
    private String msg;
    
    public CLaporan(Kasir _k){
        k = _k;
        p = new pembeli();
        p.setK(k);

        UI = new LaporanTransaksi();
        
        UI.addActonListener(this);
        UI.addFocusListenerE(this);
       
        UI.setVisible(true);  
        UI.setKasir(k.getNama());
    }
    
    public void update() throws SQLException{
        List<Barang> B = p.rekapBrgbyTgl(k.getId(), UI.getfTanggal().getText());
        UI.setTabel(B);
        
        int total = 0;
        for (Barang B1 : B) {
            total += B1.hargaTotal();
        }
        
        UI.setTotal(total);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == UI.getbLogout()){
            UI.dispose();
            clogin c = new clogin();
        }else{
            UI.dispose();
            CTransaksi cT = new CTransaksi(k);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
        try {
            update();
        } catch (SQLException ex) {
            msg = "Database error";
            UI.setMsg(msg);
        }
    }

}
