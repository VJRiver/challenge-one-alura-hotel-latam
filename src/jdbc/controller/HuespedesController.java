package jdbc.controller;

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
}
