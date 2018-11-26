/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author KURING
 */
public class Connect {
    private String dbname = "kelompokminimarket";

    public Connect() throws SQLException{
        makeConnection();
    } 

    private Connection koneksi;

    public  Connection makeConnection() throws SQLException {
        if (koneksi == null) {
        new Driver();
        // buat koneksi
        koneksi = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost/"+dbname,
                    "root",
                    "");
        }
        return koneksi;
    }  

}
