/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

/**
 *
 * @author Mateus Anjas S
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class koneksiDB {
    //deklarasi variabel koneksi bernama conn
    public static Connection conn;
    
    //method koneksi bernama getKoneksi()
    public static Connection getKoneksi(){
        //isian untuk server
        String  host = "jdbc:mysql://localhost/toko_cat",
                user = "root",
                pass = "";
        
        //ujilah koneksi dengan pernyataan try catch
        try{
            conn = (Connection) DriverManager.getConnection(host, user, pass);
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
        return conn;
    }
}


