package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.factory.ConnectionFactory;
import jdbc.modelo.HuespedesModelo;

public class HuespedesDao {
    final private Connection con;

    public HuespedesDao(Connection con) {
        this.con = con;
    }
    
    public void guardarHuesped(HuespedesModelo huesped) throws Exception {
        try {
            final Connection con = new ConnectionFactory().recuperarConexion();
            try(con){
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO "
                        + "huespedes (nombre, apellido, fecha_nacimiento, "
                        + "nacionalidad, telefono, id_reserva) VALUES "
                        + "(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ejecutarRegistro(huesped, pstmt);
            }
            
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void ejecutarRegistro(HuespedesModelo huesped, PreparedStatement ps) throws SQLException {
        ps.setString(1, huesped.getNombre());
        ps.setString(2, huesped.getApellido());
        ps.setDate(3, huesped.getFechaNacimiento());
        ps.setString(4, huesped.getNacionalidad());
        ps.setString(5, huesped.getTelefono());
        ps.setInt(6, huesped.getIdReserva());
        ps.execute();
        
        final ResultSet rs = ps.getGeneratedKeys();
        try(rs){
            while(rs.next()) {
                huesped.setId(rs.getInt(1));
            }
        }
    }
}












