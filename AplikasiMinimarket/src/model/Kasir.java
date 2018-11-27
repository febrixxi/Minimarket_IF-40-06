/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import sql.SqlStatement;

/**
 *
 * @author CakBin
 */
public class Kasir {
    private String id;
    private String pwd;
    private String Nama;
    private int umur;
    private Manager mng;
    
    public Kasir(){
        id = "";
        pwd = "";
        Nama = "";
        umur = 0;
        mng = new Manager();
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

    public Manager getMng() {
        return mng;
    }

    public void setMng(Manager mng) {
        this.mng = mng;
    }
    
    public void print(){
        System.out.println(Nama+" "+umur);
    }
    
    public String Add(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("INSERT INTO kasir VALUES ('"+ id +"', '"+ Nama +"', '"+ umur +"', '"+ pwd +"', '"+ mng.getId() +"' );");
        }catch(SQLException e){
            return e.getMessage();
        }
        return "Berhasil ditambahkan";
    }
    
    public String Delete(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("DELETE FROM kasir WHERE id_pegawai = '"+ id +"';");
        }catch(SQLException e){
            return e.getMessage();
        }
        return "Berhasil dihapus";
    }
    
    public String Update(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("UPDATE kasir SET nama = '"+ Nama +"', umur = '"+ umur +"',  password = '"+ pwd +"', id_manager = '"+ mng.getId() +"' WHERE id_pegawai = '"+ id +"';");
        }catch(SQLException e){
            return e.getMessage();
        }
        return "Berhasil di-update";
    }
    
    public boolean Auth() throws SQLException{
        boolean b;
        Statement s = new SqlStatement().makeStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM kasir WHERE id_pegawai = '"+id+"' AND password='"+pwd+"';");
        if(rs.first()){
            Nama = rs.getString("Nama");
            umur = rs.getInt("Umur");
            mng.SelectID(rs.getString("id_manager"));
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
        ResultSet rs = s.executeQuery("SELECT * FROM kasir WHERE id_pegawai = '"+ id +"' ;");
        if(rs.first()){
            Nama = rs.getString("Nama");
            umur = rs.getInt("Umur");
            b = true;   
        }else{
            b = false;
        }
        return b;
    }
    
    public List<Kasir> SelectbyMng(String _id) throws SQLException{
        List<Kasir> Daftar = new ArrayList<>();
        Kasir k = new Kasir();
        Statement s = new SqlStatement().makeStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM kasir WHERE id_manager = '"+ _id +"' ;");
        while(rs.next()){
            k.SelectID(rs.getString("id_pegawai"));
            Daftar.add(k);
        }
        return Daftar;
    }
}
