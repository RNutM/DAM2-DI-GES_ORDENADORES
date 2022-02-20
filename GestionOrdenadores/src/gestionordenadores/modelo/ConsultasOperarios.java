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
public class ConsultasOperarios extends Conexion {

    Connection con = conectar();

    public ArrayList<Operarios> todosOpe() throws SQLException {
        String sql = "select * from operarios";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListOpe(rs);
    }

    private ArrayList<Operarios> rsaArrayListOpe(ResultSet rs) throws SQLException {
        ArrayList<Operarios> listaOpe = new ArrayList<>();
        while (rs.next()) {
            listaOpe.add(new Operarios(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4)));
        }
        return listaOpe;
    }

    public ArrayList<Operarios> buscaOpe(int id) throws SQLException {
        String sql = "select * from operarios where id=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        return rsaArrayListOpe(rs);
    }

    public Object idSiguiente() throws SQLException {
        int idusu = 0;
        String sql = "select max(id)+1 from operarios";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            idusu = rs.getInt(1);
        }
        return idusu;
    }

    public void altas(int id_oper, String nom_oper, String ape_oper) throws SQLException {
        String insert = "insert into operarios (id, nombre, apellidos, imagen_operario) values (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setInt(1, id_oper);
        ps.setString(2, nom_oper);
        ps.setString(3, ape_oper);
        ps.setString(4, "nuevo");
        ps.execute();
    }

    public void bajas(int id) throws SQLException {
        String delete = "delete from operarios where id=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setInt(1, id);
        ps.execute();
    }

    public void mod(int id_oper, String nom_oper, String ape_oper) throws SQLException {
        String mod = "update operarios set nombre=?, apellidos=? where id=?";
        PreparedStatement ps = con.prepareStatement(mod);
        ps.setString(1, nom_oper);
        ps.setString(2, ape_oper);
        ps.setInt(3, id_oper);
        ps.execute();
    }
}
