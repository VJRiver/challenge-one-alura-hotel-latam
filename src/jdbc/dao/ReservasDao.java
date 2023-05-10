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
import jdbc.modelo.ReservasModelo;

public class ReservasDao {

    final private Connection con;
//    private List<ReservasModelo> reservas = new ArrayList<>();
    public ReservasDao(Connection con) {
        this.con = con;        
    }
    
    public boolean guardar(ReservasModelo reservasModelo){
        boolean guardado = false;
        try {
            final Connection con = new ConnectionFactory().recuperarConexion();
            try(con) {
                final PreparedStatement ps = con.prepareStatement("INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_de_pago) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);                
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
    
    public List<ReservasModelo> buscar(){
        List<ReservasModelo> reservas = new ArrayList<>();
        String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas";
        try {
            final PreparedStatement ps = con.prepareStatement(sql);
            try(ps){
                ps.execute();
                transformarResultSetEnReserva(reservas, ps);
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
   }
    
    public List<ReservasModelo> buscarId(int id){  
        List<ReservasModelo> reservas = new ArrayList<>();
        String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas WHERE id = ?";
        try {
            final PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            transformarResultSetEnReserva(reservas, ps);
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }
    
    public void transformarResultSetEnReserva(List<ReservasModelo> reservas, PreparedStatement ps) {
        try(ResultSet rs = ps.getResultSet()){
            while(rs.next()) {
                ReservasModelo reserva;
                reserva = new ReservasModelo(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tamaño de lista en"
                + " ReservasDao.transformarResultSetEnReserva(): " 
                + reservas.size());
    }

    public int modificar(int id, Date fechaEntrada, Date fechaSalida, String valor, String formaDePago) {
        try {
            final Connection con = new ConnectionFactory().recuperarConexion();
            try(con){
                 final PreparedStatement statement = con.prepareStatement("UPDATE reservas SET "
//                         + "id = ?"
                         + "fecha_entrada= ?, "
                         + "fecha_salida = ?, "
                         + "valor= ?,"
                         + "forma_de_pago= ? "
                         + "WHERE ID= ?");   
                 try(statement){ 
//                     statement.setInt(1, id);
                      statement.setDate(1, fechaEntrada);
                      statement.setDate(2, fechaSalida);
                      statement.setString(3, valor);
                      statement.setString(4, formaDePago);
                      statement.setInt(5, id);
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










