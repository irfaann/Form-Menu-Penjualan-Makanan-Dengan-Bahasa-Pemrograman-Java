/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeshop;

/**
 *
 * @author user
 */
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taufiqfiqria
 */
public class DBConnection {
    public Connection getConnection(){
        //klik bolep,import yang paling atas
        Connection con = null;
        try{
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/coffeshop", "root", "");
            //JOptionPane.showMessageDialog(null, "Koneksi Sukses");
            return con;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        
    }
}