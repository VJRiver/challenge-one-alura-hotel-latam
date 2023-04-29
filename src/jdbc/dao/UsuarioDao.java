package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Usuario;

public class UsuarioDao {
    
    private Connection con;

    public UsuarioDao(Connection con) {
        this.con = con;
    }

    public List<Usuario> listar() {
        List<Usuario> resultado = new ArrayList<Usuario>();

        try {
            String sql = "SELECT nombre, password FROM usuarios";

            final PreparedStatement statement = con.prepareStatement(sql);
            try (statement) {
                System.out.println(sql);
                final ResultSet resultSet = statement.executeQuery();
                
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Usuario(resultSet.getString("usuario"), resultSet.getString("password")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }    
    
    public boolean validar(String nombre, String password) {
//        Usuario resultado = new Usuario();
        boolean resultado = false;

        try {
            String sql = "SELECT nombre, password FROM usuarios WHERE nombre = ? AND password = ?";

            final PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, password);
            try (statement) {
                final ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()) {
                    return true;                    
                }                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }    

}



























