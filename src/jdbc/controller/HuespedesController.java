package jdbc.controller;

import java.sql.Date;
import java.util.List;

import jdbc.dao.HuespedesDao;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.HuespedesModelo;

public class HuespedesController {
    private HuespedesDao huespedesDao;
    
    public HuespedesController() throws Exception {
        var factory = new ConnectionFactory();
        this.huespedesDao = new HuespedesDao(factory.recuperarConexion());
    }
    
    public void guardarHuesped(HuespedesModelo huesped) throws Exception {        
        huespedesDao.guardarHuesped(huesped);        
    }
    
    public List<HuespedesModelo> buscarHuespedes(){
        return this.huespedesDao.buscarHuespedes();
    }

    public List<HuespedesModelo> buscarHuespedesApellido(String apellido) {
        return this.huespedesDao.buscarHuespedesApellido(apellido);
    }
    
    public int modificarHuesped(int id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
    	return huespedesDao.modificarhuesped(id, nombre, apellido, fechaNacimiento,  nacionalidad, telefono, idReserva);
    }
    
    public int eliminar(int id) {
    	return huespedesDao.eliminarHuesped(id);
    }
}












