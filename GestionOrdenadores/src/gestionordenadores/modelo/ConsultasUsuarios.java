/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionordenadores.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rober3
 */
public class ConsultasUsuarios extends Conexion {
    
    Connection con = conectar();
    
    public boolean comprobarUsuPass(String user, String pass) throws SQLException {
        System.out.println("Consultado si el usuario existe...");
        boolean existe = false;

        String sql = "select * from usuarios where usuario= ? and password= ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs;

        ps.setString(1, user);
        ps.setString(2, pass);
        rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("El usuario existe y es correcto");
            existe = true;
        }       
        return existe;
    }
}//Fin clase
