package jdbc.controller;

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
}












