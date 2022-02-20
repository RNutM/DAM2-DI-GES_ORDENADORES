
package gestionordenadores.modelo;

import java.sql.Date;

/**
 *
 * @author Robert G
 */
public class Reparaciones {
    private int id_ordenador;
    private int id_componente;
    private int id_operario;
    private Date fecha_reparacion;
    private float precio;

    public Reparaciones() {
    }

    public Reparaciones(int id_ordenador, int id_componente, int id_operario, Date fecha_reparacion, float precio) {
        this.id_ordenador = id_ordenador;
        this.id_componente = id_componente;
        this.id_operario = id_operario;
        this.fecha_reparacion = fecha_reparacion;
        this.precio = precio;
    }

    public int getId_ordenador() {
        return id_ordenador;
    }

    public void setId_ordenador(int id_ordenador) {
        this.id_ordenador = id_ordenador;
    }

    public int getId_componente() {
        return id_componente;
    }

    public void setId_componente(int id_componente) {
        this.id_componente = id_componente;
    }

    public int getId_operario() {
        return id_operario;
    }

    public void setId_operario(int id_operario) {
        this.id_operario = id_operario;
    }

    public Date getFecha_reparacion() {
        return fecha_reparacion;
    }

    public void setFecha_reparacion(Date fecha_reparacion) {
        this.fecha_reparacion = fecha_reparacion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
}
