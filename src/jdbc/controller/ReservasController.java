package jdbc.controller;

import jdbc.dao.ReservasDao;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.ReservasModelo;

public class ReservasController {
    private ReservasDao reservasDao;
    
    public ReservasController() throws Exception {
        var factory = new ConnectionFactory();
        this.reservasDao = new ReservasDao(factory.recuperarConexion());
        
    }
    
    public boolean guardar(ReservasModelo reservasModelo) {
        
        return reservasDao.guardar(reservasModelo);
        
    }
}








