package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.ReservasModelo;

public class ReservasDao {

    final private Connection con;
    public ReservasDao(Connection con) {
        this.con = con;        
    }
    
    public boolean guardar(ReservasModelo reservasModelo){
        boolean guardado = false;
        try {
            final Connection con = new ConnectionFactory().recuperarConexion();
            try(con) {
                final PreparedStatement ps = con.prepareStatement("INSERT INTO reservas (fecha_entrada, fecha_salida, forma_de_pago, valor) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                
                try(ps){
                    guardado = registrar(reservasModelo, ps);
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        
        return guardado;
    }
    
    private boolean registrar(ReservasModelo reservasModelo, PreparedStatement ps) throws SQLException {
        
        boolean guardado = false;
        ps.setDate(1, reservasModelo.getFechaEntrada());
        ps.setDate(2, reservasModelo.getFechaSalida());
        ps.setString(3, reservasModelo.getValor());
        ps.setString(4, reservasModelo.getFormaDePago());
        ps.execute();
        
        final ResultSet rs = ps.getGeneratedKeys();
        try(rs){
            while(rs.next()) {
                reservasModelo.setId(rs.getInt(1));
                guardado = true;
            }
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return guardado;
    }
}










