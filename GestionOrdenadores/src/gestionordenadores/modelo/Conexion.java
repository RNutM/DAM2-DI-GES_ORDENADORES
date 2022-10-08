/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionordenadores.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author examen
 */
public class Conexion {

    private final String BD = "gestionordenadores";
    private final String USER = "root";
    private final String PASS = "";
    private final String URL = "jdbc:mysql://localhost/" + BD;

    public Connection conectar() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conectado a la BD");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
        }
        return con;
    }
}//Fin clase
