package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.factory.ConnectionFactory;
import jdbc.modelo.HuespedesModelo;
import jdbc.modelo.ReservasModelo;

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
    
    public List<HuespedesModelo> buscarHuespedes(){
        List<HuespedesModelo> huespedes = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id reserva FROM huespedes";
        try {
            final PreparedStatement ps = con.prepareStatement(sql);
            try(ps){
                ps.execute();
                transformarResultSetEnHuesped(huespedes, ps);
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return huespedes;
   }
    
    public List<HuespedesModelo> buscarHuespedesApellido(String apellido) {
        List<HuespedesModelo> huespedes = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id reserva FROM huespedes WHERE apellido = ?";
        try {
            final PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, apellido);
            ps.execute();
            transformarResultSetEnHuesped(huespedes, ps);
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return huespedes;
    }
    
    public void transformarResultSetEnHuesped(List<HuespedesModelo> huespedes, PreparedStatement ps) {
        try(ResultSet rs = ps.getResultSet()){
            while(rs.next()) {
                HuespedesModelo huesped;
                huesped = new HuespedesModelo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                huespedes.add(huesped);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tamaño de lista en"
                + " HuespedesDao.transformarResultSetEnHuesped(): " 
                + huespedes.size());
    }

	public int modificarhuesped(int id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
		try {
            final Connection con = new ConnectionFactory().recuperarConexion();
            try(con){
                 final PreparedStatement statement = con.prepareStatement("UPDATE"
                 		 + " huespedes SET "
                         + "nombre= ?, "
                         + "apellido = ?, "
                         + "fecha_nacimiento= ?,"
                         + "nacionalidad= ?, "
                         + "telefono = ? "
                         + "WHERE ID = ?");   
                 try(statement){ 
                      statement.setString(1, nombre);
                      statement.setString(2, apellido);
                      statement.setDate(3, fechaNacimiento);
                      statement.setString(4, nacionalidad);
                      statement.setString(5, telefono);
                      statement.setInt(6, id);
                      statement.execute();        
                      int updateCount = statement.getUpdateCount();       
                      return updateCount;
                 }
              }catch(SQLException e) {
                  throw new RuntimeException(e);
              }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }        
		
	}
}












