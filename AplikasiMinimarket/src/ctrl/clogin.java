/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

import GUI.Login;
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
public class clogin implements ActionListener{
    private Manager m;
    private Kasir k;
    private String msg;
    private Login L;
    
    public clogin() {
        m = new Manager();
        k = new Kasir();
        L = new Login();
        L.addActionListener(this);
        L.setVisible(true);
    }
    private boolean authKasir(String id, String pwd) throws SQLException{
        k.setId(id);
        k.setPwd(pwd);
        return k.Auth();
    }
    
    private boolean authMng(String id, String pwd) throws SQLException{
        m.setId(id);
        m.setPwd(pwd);
        return m.Auth();
    }
    
    private int login() throws SQLException{
        String id = L.getID();
        String pwd = L.getPwd();
        if(authMng(id,pwd)){
            return 0;
        }else if(authKasir(id,pwd)){
            return 1;
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(login() == 0){
                L.dispose();
                CMenuManager M = new CMenuManager(m);
            }else if(login() == 1){
                L.dispose();
                CMenuKasir K = new CMenuKasir(k);
            }else{
                msg = "ID/Password salah";
                L.setMsg(msg);
            }
        } catch (SQLException ex) {
            msg = "Database error " + System.lineSeparator();
            msg = msg + ex.getMessage();
            L.setMsg(msg);
        }
    }
}
