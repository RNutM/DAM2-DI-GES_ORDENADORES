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
public class ConsultasTipos extends Conexion {

    Connection con = conectar();

    public ArrayList<Tipos> todosTip() throws SQLException {
        String sql = "select * from tipos";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListTip(rs);
    }

    private ArrayList<Tipos> rsaArrayListTip(ResultSet rs) throws SQLException {
        ArrayList<Tipos> listaTip = new ArrayList<>();
        while (rs.next()) {
            listaTip.add(new Tipos(rs.getInt(1), rs.getString(2)));
        }
        return listaTip;
    }

    public ArrayList<Tipos> buscaTip(int id) throws SQLException {
        String sql = "select * from tipos where id=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListTip(rs);
    }

    public Object idSiguiente() throws SQLException {
        int idtip = 0;
        String sql = "select max(id)+1 from tipos";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            idtip = rs.getInt(1);
        }
        return idtip;
    }

    public void altas(int id_tipo, String tipo) throws SQLException {
        String insert = "insert into tipos (id, tipo) values (?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setInt(1, id_tipo);
        ps.setString(2, tipo);
        ps.execute();
    }

    public void bajas(int id) throws SQLException {
        String delete = "delete from tipos where id=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setInt(1, id);
        ps.execute();
    }

    public void mod(int id_tipo, String tipo) throws SQLException {
        String mod = "update tipos set tipo=? where id=?";
        PreparedStatement ps = con.prepareStatement(mod);
        ps.setString(1, tipo);
        ps.setInt(2, id_tipo);
        ps.execute();
    }

    public ArrayList<String> buscaTipNom() throws SQLException {
        String sql = "select * from tipos";
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

    //Con este método obtengo un id int a partir de un tipo String
    public int[] obtenerTipCom(String nomTip) throws SQLException {
        int cb[] = new int[1];
        //Consulta a tipos para obtener el id a partir del tipo
        String sqlid = "select id from tipos where tipo=?";

        PreparedStatement psid = con.prepareStatement(sqlid);
        psid.setString(1, nomTip);
        ResultSet rsid = psid.executeQuery();
        rsid.next();
        cb[0] = rsid.getInt(1);
        rsid.close();
        return cb;
    }
}
