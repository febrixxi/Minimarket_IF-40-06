/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

import GUI.MenambahBarang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object Source = e.getSource();
        if(Source.equals(mB.getbSubmit())){
            
        }else if(Source.equals(mB.getbCancel())){
            mB.dispose();
            CMenuManager cM = new CMenuManager(m);
        }
    }
    
}
