package gestionordenadores.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Robert G
 */
public class ConsultasClientes extends Conexion{
    Connection con = conectar();

    public ArrayList<Clientes> todosCli() throws SQLException {
        String sql = "select * from clientes";

        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListCli(rs);
    }

    private ArrayList<Clientes> rsaArrayListCli(ResultSet rs) throws SQLException {
        ArrayList<Clientes> listaCli = new ArrayList<>();
        while (rs.next()) {
            listaCli.add(new Clientes(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9)));
        }
        return listaCli;
    }

    public ArrayList<Clientes> buscaCli(String dni) throws SQLException {
        String sql = "select * from clientes where dni=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();
        return rsaArrayListCli(rs);
    }

    public void altas(String dni, String nom_cli, String ape_cli, String dir_cli, int codpos_cli, String pob_cli, String pro_cli, String tel_cli) throws SQLException {
        String insert = "insert into clientes (dni, nombre, apellidos, direccion, codigo_postal, poblacion, provincia, telefono, imagen_cliente)"
                + "values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);

        ps.setString(1, dni);
        ps.setString(2, nom_cli);
        ps.setString(3, ape_cli);
        ps.setString(4, dir_cli);
        ps.setInt(5, codpos_cli);
        ps.setString(6, pob_cli);
        ps.setString(7, pro_cli);
        ps.setString(8, tel_cli);
        ps.setString(9, "nuevo");
     
        ps.execute();
    }

    public void bajas(String dni) throws SQLException {
        String delete = "delete from clientes where dni=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setString(1, dni);
        ps.execute();
    }

    public void mod(String dni, String nom_cli, String ape_cli, String dir_cli,
            int codpos_cli, String pob_cli, String pro_cli, String tel_cli) throws SQLException {
        String modif = "update clientes set nombre=?, apellidos=?, direccion=?,"
                + " codigo_postal=?, poblacion=?, provincia=?, telefono=?"
                + "where dni=?";
        PreparedStatement ps = con.prepareStatement(modif);

        ps.setString(1, nom_cli);
        ps.setString(2, ape_cli);
        ps.setString(3, dir_cli);
        ps.setInt(4, codpos_cli);
        ps.setString(5, pob_cli);
        ps.setString(6, pro_cli);
        ps.setString(7, tel_cli);
        ps.setString(8, dni);
        ps.execute();
    }
}
