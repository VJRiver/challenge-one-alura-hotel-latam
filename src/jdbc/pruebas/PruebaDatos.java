package jdbc.pruebas;

import java.sql.Connection;
import java.util.List;

import jdbc.controller.ReservasController;
import jdbc.dao.ReservasDao;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.ReservasModelo;

public class PruebaDatos{
    private static List<ReservasModelo> reservas;
    private static ReservasDao reserva;

    public static void main(String[] args) {
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
       
        try{
            ReservasController reservasController = new ReservasController();
            reservasController.buscar();
            conecta = new ConnectionFactory();
            Connection con = conecta.recuperarConexion();
            reserva = new ReservasDao(con);
            reservas = reserva.buscar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}















