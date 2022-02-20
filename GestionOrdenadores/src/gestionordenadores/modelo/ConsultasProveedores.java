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
public class ConsultasProveedores extends Conexion {

    Connection con = conectar();

    public ArrayList<Proveedores> todosPro() throws SQLException {
        String sql = "select * from proveedores";

        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListPro(rs);
    }

    private ArrayList<Proveedores> rsaArrayListPro(ResultSet rs) throws SQLException {
        ArrayList<Proveedores> listaPro = new ArrayList<>();
        while (rs.next()) {
            listaPro.add(new Proveedores(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getInt(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8)));
        }
        return listaPro;
    }

    public ArrayList<Proveedores> buscaPro(String cif) throws SQLException {
        String sql = "select * from proveedores where cif=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cif);
        ResultSet rs = ps.executeQuery();
        return rsaArrayListPro(rs);
    }

    public void altas(String cif, String nom_pro, String dir_pro, int codpos_pro, String pob_pro, String pro_pro, String tel_pro) throws SQLException {
        String insert = "insert into proveedores (cif, nombre, direccion, codigo_postal, poblacion, provincia, telefono, imagen_proveedor)"
                + "values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);

        ps.setString(1, cif);
        ps.setString(2, nom_pro);
        ps.setString(3, dir_pro);
        ps.setInt(4, codpos_pro);
        ps.setString(5, pob_pro);
        ps.setString(6, pro_pro);
        ps.setString(7, tel_pro);
        ps.setString(8, "nuevo");
        ps.execute();
    }

    public void bajas(String cif) throws SQLException {
        String delete = "delete from proveedores where cif=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setString(1, cif);
        ps.execute();
    }

    public void mod(String cif, String nom_pro, String dir_pro, int codpos_pro, String pob_pro, String pro_pro, String tel_pro) throws SQLException {
        String modif = "update proveedores set nombre=?, direccion=?,"
                + " codigo_postal=?, poblacion=?, provincia=?, telefono=?"
                + "where cif=?";
        PreparedStatement ps = con.prepareStatement(modif);

        ps.setString(1, nom_pro);
        ps.setString(2, dir_pro);
        ps.setInt(3, codpos_pro);
        ps.setString(4, pob_pro);
        ps.setString(5, pro_pro);
        ps.setString(6, tel_pro);
        ps.setString(7, cif);
        ps.execute();
    }

    public ArrayList<String> buscaProNom() throws SQLException {
        String sql = "select * from proveedores";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return rsaArrayListString(rs);
    }

    private ArrayList<String> rsaArrayListString(ResultSet rs) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        while (rs.next()) {
            array.add(rs.getString(2));
        }
        return array;
    }
    //Con este método obtengo un CIF String a partir de un nombre String
    public String obtenerCifProv(String nomProv) throws SQLException {
        String sql = "select cif from proveedores where nombre=?";
        String cif="";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nomProv);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            cif=rs.getString(1);
        }
        return cif;
    }
}
