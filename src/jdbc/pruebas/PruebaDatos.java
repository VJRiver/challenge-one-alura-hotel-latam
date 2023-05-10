package jdbc.pruebas;

import java.sql.Connection;
import java.util.List;

import jdbc.controller.HuespedesController;
import jdbc.controller.ReservasController;
import jdbc.dao.HuespedesDao;
import jdbc.dao.ReservasDao;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.HuespedesModelo;
import jdbc.modelo.ReservasModelo;

public class PruebaDatos{
    private static List<ReservasModelo> reservas;
    private static ReservasDao reserva;

    public static void main(String[] args) {
        List<HuespedesModelo> huespedes;
        HuespedesDao huespedesDao;
        ConnectionFactory conecta;
        System.out.println("Devuelto por ReservasController.buscar(): ");
        try {
            ReservasController reservasController = new ReservasController();
            reservas = reservasController.buscar();
            for(ReservasModelo rm : reservas) {
                System.out.println(rm.getFechaEntrada() +" " + rm.getFechaSalida());
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
       
        // Prueba busqueda de todas las reservas
        try{
            ReservasController reservasController = new ReservasController();
            reservasController.buscar();
            conecta = new ConnectionFactory();
            Connection con = conecta.recuperarConexion();
            reserva = new ReservasDao(con);
            reservas = reserva.buscar();
//            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        // checamos busqueda por Id
        
        try{
            ReservasController reservasController = new ReservasController();
 
            conecta = new ConnectionFactory();
            Connection con = conecta.recuperarConexion();
            reserva = new ReservasDao(con);
            reservas = reservasController.buscarId(16);
            System.out.println("Prueba de buscarId");
            for(ReservasModelo rm : reservas) {
                System.out.println(rm.getId() +": " + rm.getValor());
            }
//            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        
        // Prueba busqueda todos los huespedes
        
        try{
            HuespedesController huespedesController = new HuespedesController();
            huespedesController.buscarHuespedes();
            conecta = new ConnectionFactory();
            Connection con = conecta.recuperarConexion();
            huespedesDao = new HuespedesDao(con);
            huespedes = huespedesDao.buscarHuespedes();
            System.out.println("Prueba de mostrar todos los huespedes:");
            for(HuespedesModelo h: huespedes) {
                System.out.println("Nombre huesped: " + h.getNombre() + " " + h.getApellido());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}















