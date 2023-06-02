package jdbc.controller;

import java.sql.Date;
import java.util.List;

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
    
    public List<ReservasModelo> buscar(){
        return reservasDao.buscar();
    }
    
    public List<ReservasModelo> buscarId(int id){
        return reservasDao.buscarId(id);
    }

    public int modificar(int id, Date fechaEntrada, Date fechaSalida, String valor, String formaDePago) {
        
        return reservasDao.modificar(id, fechaEntrada, fechaSalida, valor, formaDePago);
    }
    
    public int eliminar(int id) {
    	return reservasDao.eliminarReserva(id);
    }
}










