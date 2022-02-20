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
 * @author Rober3
 */
public class ConsultasAdmin extends Conexion {

    Connection con = conectar();

    public ArrayList<Usuarios> todosUsu() throws SQLException {
        String sql = "select * from usuarios";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListUsu(rs);
    }

    private ArrayList<Usuarios> rsaArrayListUsu(ResultSet rs) throws SQLException {
        ArrayList<Usuarios> listaUsu = new ArrayList<>();
        while (rs.next()) {
            listaUsu.add(new Usuarios(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        return listaUsu;
    }

    public ArrayList<Usuarios> buscaUsu(int id) throws SQLException {
        String sql = "select * from usuarios where id=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListUsu(rs);
    }

    public Object idSiguiente() throws SQLException {
        int idusu = 0;
        String sql = "select max(id)+1 from usuarios";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            idusu = rs.getInt(1);
        }
        return idusu;
    }
    
    public void altas(int id_usu, String nom_usu, String ape_usu,
            String usu_usu, String pas_usu, String dir_usu) throws SQLException {
        String insert="insert into usuarios (id, nombre, "
                + "apellidos, usuario, password,"
                + "direccion)"
                + "values(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setInt(1, id_usu);
        ps.setString(2, nom_usu);
        ps.setString(3, ape_usu);
        ps.setString(4, usu_usu);
        ps.setString(5, pas_usu);
        ps.setString(6, dir_usu);
        ps.execute();
    }

    public void bajas(int id) throws SQLException {
        String delete = "delete from usuarios where id=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setInt(1, id);
        ps.execute();
    }

    public void mod(int id_usu, String nom_usu, String ape_usu, String usu_usu,
            String pas_usu, String dir_usu) throws SQLException {
        String mod = "update usuarios set nombre=?, apellidos=?,"
                + " usuario=?, password=?, direccion=?"
                + " where id=?";

        PreparedStatement ps = con.prepareStatement(mod);

        ps.setString(1, nom_usu);
        ps.setString(2, ape_usu);
        ps.setString(3, usu_usu);
        ps.setString(4, pas_usu);
        ps.setString(5, dir_usu);
        ps.setInt(6, id_usu);
        ps.execute();
    }
}//Fin clase
