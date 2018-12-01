/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

import GUI.MenambahPegawai;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Kasir;
import model.Manager;

/**
 *
 * @author CakBin
 */
public class CTbhPegawai implements ActionListener{
    private Manager m;
    private Manager temp;
    private Kasir k;
    private MenambahPegawai mP;
    private String msg;
    
    public CTbhPegawai(Manager _m){
        m = _m;
        k = new Kasir();
        temp = new Manager();
        mP = new MenambahPegawai();
        mP.addActionListener(this);
        mP.setMng(m.getId());
        mP.setVisible(true);
    }
    
    private boolean Validasi(){
        if(mP.getID().length()>10){
            msg = "ID melebihi 10 karakter";
        }else if(mP.getID().isEmpty() || mP.getPwd().isEmpty() || mP.getNama().isEmpty()||mP.getMng().isEmpty()){
            msg = "Ada kolom kosong kosong";
        }else if(mP.getPwd().length()>15){
            msg = "Password melebihi 15 karakter";
        }else try {
            if(k.SelectID(mP.getID())){
                msg = "ID sudah terdaftar";
            }else if(!temp.SelectID(mP.getMng())){
                msg = "Manager tak terdaftar";
            }else{
                return temp.SelectID(mP.getMng());
            }
        } catch (SQLException ex) {
            msg = ex.getMessage();
            mP.setMsg(msg);
        }
        mP.setMsg(msg);
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object Source = e.getSource();
        if(Source.equals(mP.getbSubmit())){
            if(Validasi()){
                k.setId(mP.getID());
                k.setNama(mP.getNama());
                k.setPwd(mP.getPwd());
                k.setUmur(mP.getUmur());
                k.setMng(temp);
                msg = k.Add();
                mP.setMsg(msg);
            }
        }else if(Source.equals(mP.getbCancel())){
            mP.dispose();
            CMenuManager cM = new CMenuManager(m);
        }
    }  
}
