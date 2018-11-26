/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sql.*;

/**
 *
 * @author CakBin
 */
public class Manager {
    private String id;
    private String pwd;
    private String Nama;
    private int umur;

    public Manager() {
        id = "";
        pwd = "";
        Nama = "";
        umur = 0;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public void print(){
        System.out.println(Nama+" "+umur);
    }
    
    public String Add(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("INSERT INTO manager VALUES ('"+ id +"', '"+ Nama +"', '"+ umur +"', '"+ pwd +"');");
        }catch(SQLException e){
            return e.getMessage();
        }
        return "Berhasil ditambahkan";
    }
    
    public String Delete(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("DELETE FROM manager WHERE id_pegawai = '"+ id +"';");
        }catch(SQLException e){
            return e.getMessage();
        }
        return "Berhasil dihapus";
    }
    
    public String Update(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("UPDATE manager SET nama = '"+ Nama +"', umur = '"+ umur +"',  password = '"+ pwd +"' WHERE id_pegawai = '"+ id +"';");
        }catch(SQLException e){
            return e.getMessage();
        }
        return "Berhasil di-update";
    }
    
    public boolean Auth() throws SQLException{
        boolean b;
        Statement s = new SqlStatement().makeStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM manager WHERE id_pegawai = '"+id+"' AND password='"+pwd+"';");
        if(rs.first()){
            Nama = rs.getString("Nama");
            umur = rs.getInt("Umur");
            b = true;   
        }else{
            b = false;
        }
        return b;
    }
    
    public boolean SelectID(String _id) throws SQLException{
        boolean b;
        id = _id;
        Statement s = new SqlStatement().makeStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM manager WHERE id_pegawai = '"+ id +"' ;");
        if(rs.first()){
            Nama = rs.getString("Nama");
            umur = rs.getInt("Umur");
            b = true;   
        }else{
            b = false;
        }
        return b;
    }
}
