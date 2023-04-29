package jdbc.controller;

import java.util.List;

import jdbc.dao.UsuarioDao;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Usuario;

public class UsuarioController {
    
    private UsuarioDao usuarioDao;
    private String nombre;
    private String password;

    
    public UsuarioController() throws Exception {
        var factory = new ConnectionFactory();
        this.usuarioDao = new UsuarioDao(factory.recuperarConexion());
    }
    
    public List<Usuario> listar(){
        return this.usuarioDao.listar();
    }
    
    public boolean validar(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
        return this.usuarioDao.validar(nombre, password);
        
    }

}



















