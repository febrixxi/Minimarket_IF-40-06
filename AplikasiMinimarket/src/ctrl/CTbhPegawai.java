/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

import GUI.MenambahPegawai;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Kasir;
import model.Manager;

/**
 *
 * @author CakBin
 */
public class CTbhPegawai implements ActionListener{
    private Manager m;
    private Kasir k;
    private MenambahPegawai mP;
    private String msg;
    
    public CTbhPegawai(Manager _m){
        m = _m;
        k = new Kasir();
        mP = new MenambahPegawai();
        mP.addActionListener(this);
        mP.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object Source = e.getSource();
        if(Source.equals(mP.getbSubmit())){
            
        }else if(Source.equals(mP.getbCancel())){
            mP.dispose();
            CMenuManager cM = new CMenuManager(m);
        }
    }  
}
