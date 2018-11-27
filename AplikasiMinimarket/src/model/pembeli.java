/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sql.SqlStatement;

/**
 *
 * @author CakBin
 */
public class pembeli {
    private int id;
    private String tanggal;
    private Kasir k;
    private List<Barang> Daftar;
    
    public pembeli(){
        id = 0;
        tanggal = "2018-11-30";
        k = new Kasir();
        Daftar = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Kasir getK() {
        return k;
    }

    public void setK(Kasir k) {
        this.k = k;
    }

    public List<Barang> getDaftar() {
        return Daftar;
    }
    
    public void addBarang(Barang b){
        Daftar.add(b);
    }
    public void Next(){
        id ++;
        Daftar.clear();
    }
    
    public void print(){
        k.print();
        System.out.println(id+" "+ tanggal);
        for (Barang Daftar1 : Daftar) {
            Daftar1.print();
        }
    }
    
    public String Add(){
         try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("INSERT INTO pembeli VALUES ('"+ id +"', '"+ tanggal +"' );");
             for (Barang Daftar1 : Daftar) {
                 s.execute("INSERT INTO pembelian VALUES ('"+ k.getId() +"', '"+ id + "', '"+ Daftar1.getKode() +"', '"+ Daftar1.getJumlah() + "' );");
             }
        }catch(SQLException e){
            return e.getMessage();
        }
        return "Berhasil ditambahkan";
    }
    
    public String Delete(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("DELETE FROM pembeli WHERE No_Pengunjung = '"+ id +"';");
        }catch(SQLException e){
            return e.getMessage();
        }
        return "Berhasil dihapus";
    }
    
    public boolean SelectID() throws SQLException{
        boolean b;
        Barang brg = new Barang();
        Statement s = new SqlStatement().makeStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM pembeli WHERE No_Pengunjung = '"+ id +"';");
        if(rs.first()){
            tanggal = rs.getString("tanggal");
            b = true;   
            rs = s.executeQuery("SELECT * FROM pembelian WHERE No_Pengunjung = '"+ id + "';");
            while(rs.next()){
                brg.Search(rs.getString("kodebarang"));
                brg.setJumlah(rs.getInt("jumlah"));
            }
        }else{
            b = false;
        }
        
        return b;
    }
    
    public void setLastId() throws SQLException{
        Statement s = new SqlStatement().makeStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM pembeli ORDER BY No_Pengunjung DESC;");
        if(rs.first()){
            id = rs.getInt("No_Pengunjung");
        }else{
            id = 0;
        }
    }
}
