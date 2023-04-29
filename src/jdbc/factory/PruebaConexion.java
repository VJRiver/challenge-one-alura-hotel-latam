package jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;


public class PruebaConexion {

    public static void main(String[] args) throws SQLException {
         ConnectionFactory conecta;
         Connection con;
         String sql = "SELECT nombre, password FROM usuarios";
        try {
            conecta = new ConnectionFactory();
            con = conecta.recuperarConexion();
            Statement smt = con.createStatement();
            smt.execute(sql);
            ResultSet rs = smt.getResultSet();
            while(rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2));
            }
            
        } catch (Exception e) {
            System.out.println("Hubo un error");
            e.printStackTrace();
        }
        
         
        
        
        
        
//        DataSource dataSource;
//        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
//        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_db?useTimeZone=true&serverTimeZone=UTC");
//        comboPooledDataSource.setUser("root");
//        comboPooledDataSource.setPassword("toor");
////       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_db?useTimeZone=true&serverTimeZone=UTC", "root", "toor");
//        dataSource = comboPooledDataSource;
//        try {
//            Connection con = dataSource.getConnection();
//            System.out.println("Cerrando conexion");
//        }catch(SQLException e) {
//            throw new RuntimeException(e);
//        }
//       con.close();
               }

}











