/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public String Add(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("INSERT INTO kasir VALUES (\""+ id +"\", \""+ Nama +"\", \""+ umur +"\", \""+ pwd +"\");");
        }catch(SQLException e){
            return "Error";
        }
        return "Berhasil ditambahkan";
    }
    
    public String Delete(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("DELETE * FROM kasir WHERE pegawai_id =\""+ id +"\";");
        }catch(SQLException e){
            return "Error";
        }
        return "Berhasil dihapus";
    }
    
    public String Update(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("UPDATE kasir SET nama = \""+ Nama +"\", umur = \""+ umur +"\",  password = \""+ pwd +"\"WHERE pegawai_id =\""+ id +"\";");
        }catch(SQLException e){
            return "Error";
        }
        return "Berhasil di-update";
    }
    
    public String Auth(){
        String msg;
        try{
            Statement s = new SqlStatement().makeStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM kasir WHERE id_pegawai = \""+id+"\" AND password=\""+pwd+"\";");
            if(rs.first()){
                Nama = rs.getString("Nama");
                umur = rs.getInt("Umur");
                msg = "login berhasil";
            }else{
             msg = "login gagal";
            }
        }catch(SQLException e){
            return "error";
        }
        return msg;
    }
}
