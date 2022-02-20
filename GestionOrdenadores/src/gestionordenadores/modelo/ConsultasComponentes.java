
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
public class ConsultasComponentes extends Conexion{

    Connection con = conectar();

    public ArrayList<Componentes> todosCom() throws SQLException {
        String sql = "select * from componentes";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListCom(rs);
    }

    private ArrayList<Componentes> rsaArrayListCom(ResultSet rs) throws SQLException {
        ArrayList<Componentes> listaCom = new ArrayList<>();
        while (rs.next()) {
            listaCom.add(new Componentes(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getFloat(5),rs.getString(6),rs.getInt(7),
                    rs.getString(8),rs.getBoolean(9)));
        }
        return listaCom;
    }

    public ArrayList<Componentes> buscaCom(int id) throws SQLException {
         String sql = "select * from componentes where id=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListCom(rs);
    }

    public Object idSiguiente() throws SQLException {
        int idcom = 0;
        String sql = "select max(id)+1 from componentes";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            idcom = rs.getInt(1);
        }
        return idcom;
    }

    public void altas(int id_com, String nom_com, String mar_com, String des_com, float pre_com, String cif_pro, int tip_com, String est_com) throws SQLException {
        String insert = "insert into componentes (id, nombre, marca, descripcion,"
                + " precio, cif_proveedor, id_tipo, imagen_componente, estado)"
                + " values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setInt(1, id_com);
        ps.setString(2, nom_com);
        ps.setString(3, mar_com);
        ps.setString(4, des_com);
        ps.setFloat(5, pre_com);
        ps.setString(6, cif_pro);
        ps.setInt(7, tip_com);
        ps.setString(8, "nuevo");
        ps.setString(9, est_com);
        ps.execute();
    }

    public void bajas(int id) throws SQLException {
        String delete = "delete from componentes where id=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setInt(1, id);
        ps.execute();
    }

    public void mod(int id_com, String nom_com, String mar_com, String des_com,
            float pre_com, String cif_pro, int tip_com, String est_com) throws SQLException {
         String mod = "update componentes set nombre=?, marca=?, descripcion=?, "
                 + "precio=?, cif_proveedor=?, id_tipo=?, estado=? where id=?";
        PreparedStatement ps = con.prepareStatement(mod);
        ps.setString(1, nom_com);
        ps.setString(2, mar_com);
        ps.setString(3, des_com);
        ps.setFloat(4, pre_com);
        ps.setString(5, cif_pro);
        ps.setInt(6, tip_com);
        ps.setString(7, est_com);
        ps.setInt(8, id_com);
        ps.execute();
    }

    //Cargo el combo de los informes y muestro todas las marcas
    public ArrayList<String> buscaMarca() throws SQLException {
        String sql = "select * from componentes";

        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListString3(rs);
    }
    //Aqui le llamo rsaArrayListString3 por que es la posición de la marca en la BD
    private ArrayList<String> rsaArrayListString3(ResultSet rs) throws SQLException {
        ArrayList<String> array = new ArrayList<>();

        while (rs.next()) {
            array.add(rs.getString(3));
        }
        return array;
    }
}
