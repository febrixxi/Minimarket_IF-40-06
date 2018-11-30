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
public class Barang {
    private String kode;
    private String nama;
    private int harga;
    private int jumlah;
    
    public Barang(){
        kode = "";
        nama = "";
        harga = 0;
        jumlah = 0;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    public void print(){
        System.out.println(kode +" "+ nama +" "+ harga +" "+ jumlah);
    }
    
    public int hargaTotal(){
        return (jumlah * harga);
    }
    
    public String Add(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("INSERT INTO barang_barang VALUES ('"+ kode +"', '"+ nama +"', '"+ harga +"', '"+ jumlah +"' );");
        }catch(SQLException e){
            return e.getMessage();
        }
        return "Berhasil ditambahkan";
    }
    
    public String Delete(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("DELETE FROM barang_barang WHERE kodebarang = '"+ kode +"';");
        }catch(SQLException e){
            return e.getMessage();
        }
        return "Berhasil dihapus";
    }
    
    public String Update(){
        try{
            Statement s = new SqlStatement().makeStatement();
            s.execute("UPDATE kasir SET namabarang = '"+ nama +"', harga = '"+ harga +"',  jumlah = '"+ jumlah +"' WHERE kodebarang = '"+ kode +"';");
        }catch(SQLException e){
            return e.getMessage();
        }
        return "Berhasil di-update";
    }
    
    public boolean Search(String _kode) throws SQLException{
        boolean b;
        kode = _kode;
        Statement s = new SqlStatement().makeStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM barang_barang WHERE kodebarang = '"+ kode +"' ;");
        if(rs.first()){
            nama = rs.getString("namabarang");
            harga = rs.getInt("harga");
            jumlah = rs.getInt("jumlah");
            b = true;   
        }else{
            b = false;
        }
        return b;
    }
}
