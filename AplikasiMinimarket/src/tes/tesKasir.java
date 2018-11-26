/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tes;

import java.sql.SQLException;
import model.Kasir;
import model.Manager;

/**
 *
 * @author CakBin
 */
public class tesKasir {
    public static void main(String args[]){
        Kasir k = new Kasir();
        String msg;
        
        //Login
        k.setId("KSR-00-001");
        k.setPwd("qwerty");
        try{
            if(k.Auth()){
                System.out.println("Login berhasil");
            }else{
                System.out.println("Login gagal");
            }
        }catch(SQLException e){
            System.out.println("Database error");
        }
        k.print();
        
        k.setId("KSR-00-002");
        k.setPwd("142857");
        k.setNama("Sijua");
        k.setUmur(6);
        k.getMng().setId("MNG-00-001");
        
        //Add
        msg = k.Add();
        System.out.println(msg);
        
        //Update
        k.setNama("Kumparna");
        msg = k.Update();
        System.out.println(msg); 
        
        //Delete
        msg = k.Delete();
        System.out.println(msg);
    }
}
