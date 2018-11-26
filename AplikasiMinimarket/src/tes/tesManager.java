/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tes;

import java.sql.SQLException;
import model.Manager;
/**
 *
 * @author CakBin
 */
public class tesManager {
    public static void main(String args[]){
        Manager m = new Manager();
        String msg;
        
        //Login
        m.setId("MNG-00-001");
        m.setPwd("asdfghjkl");
        try{
            if(m.Auth()){
                System.out.println("Login berhasil");
            }else{
                System.out.println("Login gagal");
            }
        }catch(SQLException e){
            System.out.println("Database error");
        }
        m.print();
        
        m.setId("MNG-00-002");
        m.setPwd("142857");
        m.setNama("Ajua");
        m.setUmur(99);
        
        //Add
        msg = m.Add();
        System.out.println(msg);
        
        //Update
        m.setNama("Akua");
        msg = m.Update();
        System.out.println(msg); 
        
        //Delete
        msg = m.Delete();
        System.out.println(msg);
    }
}
