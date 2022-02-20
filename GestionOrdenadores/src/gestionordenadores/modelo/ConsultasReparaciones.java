
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
public class ConsultasReparaciones extends Conexion{

    Connection con = conectar();

    public ArrayList<Reparaciones> todosRep() throws SQLException {
      String sql = "select * from reparaciones";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListRep(rs);
    }

    private ArrayList<Reparaciones> rsaArrayListRep(ResultSet rs) throws SQLException {
        ArrayList<Reparaciones> listaRep = new ArrayList<>();
        while (rs.next()) {
            listaRep.add(new Reparaciones(rs.getInt(1), rs.getInt(2), rs.getInt(3),
            rs.getDate(4), rs.getFloat(5)));
        }
        return listaRep;
    }

    public ArrayList<Reparaciones> buscaRep(int id) throws SQLException {
        String sql = "select * from reparaciones where id_ordenador=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListRep(rs);
    }
}
