/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

import java.sql.SQLException;
import model.Kasir;
import model.Manager;

/**
 *
 * @author CakBin
 */
public class clogin {
    private Manager m;
    private Kasir k;
    private cManager Manager;
    private cKasir Kasir;
    private String msg;
    
    public clogin(){
        m = new Manager();
        k = new Kasir();
        Manager = new cManager();

        
        init();
    }
    private void init(){
        m.setId(null);
        k.setId(null);
        msg = "";
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
    
    private void hide(){
        
    }
    
    private void login(){
        
    }
    
    private void logout(){
        init();
    }
}
