package jdbc.factory;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.*;


public class ConnectionFactory {
    public DataSource dataSource;

    public ConnectionFactory() throws Exception {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_db?useTimeZone=true&serverTimeZone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("toor");

        this.dataSource = comboPooledDataSource;
    }

    public Connection recuperarConexion(){

    try{
        System.out.println("conexion creada");
       return this.dataSource.getConnection();

    }catch(SQLException e){
       throw new RuntimeException(e);
       }
    }
} 
 























    
    