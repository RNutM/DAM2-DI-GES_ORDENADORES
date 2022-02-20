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
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ConsultasMontajes extends Conexion{
    
    Connection con = conectar();

    public ArrayList<Montajes> todosMon() throws SQLException {
      String sql = "select * from montajes";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListMon(rs);
    }

    private ArrayList<Montajes> rsaArrayListMon(ResultSet rs) throws SQLException {
        ArrayList<Montajes> listaMon = new ArrayList<>();
        while (rs.next()) {
            listaMon.add(new Montajes(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
        }
        return listaMon;
    }

    public ArrayList<Montajes> buscaMon(int id) throws SQLException {
        String sql = "select * from montajes where id_ordenador=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListMon(rs);
    }


}
