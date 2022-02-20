
package gestionordenadores.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Robert G
 */
public class ConsultasOrdenadores extends Conexion{

    Connection con = conectar();

    public ArrayList<Ordenadores> todosOrd() throws SQLException {
        String sql = "select * from ordenadores";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListOrd(rs);
    }

    private ArrayList<Ordenadores> rsaArrayListOrd(ResultSet rs) throws SQLException {
        ArrayList<Ordenadores> listaOrd = new ArrayList<>();
        while (rs.next()) {
            listaOrd.add(new Ordenadores(rs.getInt(1), rs.getString(2),
                    rs.getFloat(3), rs.getString(4), rs.getDate(5)));
        }
        return listaOrd;
    }

    public ArrayList<Ordenadores> buscaOrd(int id) throws SQLException {
        String sql = "select * from ordenadores where id=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListOrd(rs);
    }

    public ArrayList<String> buscaDni() throws SQLException {
        String sql = "select * from ordenadores";

        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListString4(rs);
    }
    //Le llamo rsaArrayListString4 por el dni esta en la posición 4 de la tabla
    private ArrayList<String> rsaArrayListString4(ResultSet rs) throws SQLException {
        ArrayList<String> array = new ArrayList<>();

        while (rs.next()) {
            array.add(rs.getString(4));
        }
        return array;
    }

    public Object idSiguiente() throws SQLException {
        int idord = 0;
        String sql = "select max(id)+1 from ordenadores";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            idord = rs.getInt(1);
        }
        return idord;
    }

    public void altas(int id_ord, String modelo, float precio, String dni_cliente, Date jfecha) throws SQLException {
        String insert = "insert into ordenadores (id, modelo, precio, dni_cliente, fecha_montaje)"
                + "values(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);

        ps.setInt(1, id_ord);
        ps.setString(2, modelo);
        ps.setFloat(3, precio);
        ps.setString(4, dni_cliente);
        ps.setDate(5, jfecha);
        ps.execute();
    }

    public void bajas(int id) throws SQLException {
        String delete = "delete from ordenadores where id=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setInt(1, id);
        ps.execute();
    }

    public void mod(int id_ord, String modelo, float precio, String dni_cliente, Date jfecha) throws SQLException {
        String mod = "update ordenadores set modelo=?, precio=?, dni_cliente=?, fecha_montaje=? where id=?";
        PreparedStatement ps = con.prepareStatement(mod);
        ps.setString(1, modelo);
        ps.setFloat(2, precio);
        ps.setString(3, dni_cliente);
        ps.setDate(4, jfecha);
        ps.setInt(5, id_ord);
        ps.execute();
    }

}
