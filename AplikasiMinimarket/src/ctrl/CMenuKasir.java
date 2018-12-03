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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Source = e.getSource();
        if(Source.equals(HK.getbTransaksi())){
            HK.dispose();
            CTransaksi cT = new CTransaksi(k);
        }else if(Source.equals(HK.getbLaporan())){
            HK.dispose();
            CLaporan cL = new CLaporan(k);
        }else if(Source.equals(HK.getbLogout())){
            HK.dispose();
            clogin l = new clogin();
        }
    }
}
