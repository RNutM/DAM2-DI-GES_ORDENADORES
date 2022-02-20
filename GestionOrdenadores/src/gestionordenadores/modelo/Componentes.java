
package gestionordenadores.modelo;

/**
 *
 * @author Robert G
 */
public class Componentes {

    private int id;
    private String nombre;
    private String marca;
    private String descripcion;
    private float precio;
    private String cif_proveedor;
    private int id_tipo;
    private String imagen_componente;
    private boolean estado;

    public Componentes() {
    }

    public Componentes(int id, String nombre, String marca, String descripcion, float precio, String cif_proveedor, int id_tipo, String imagen_componente, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cif_proveedor = cif_proveedor;
        this.id_tipo = id_tipo;
        this.imagen_componente = imagen_componente;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCif_proveedor() {
        return cif_proveedor;
    }

    public void setCif_proveedor(String cif_proveedor) {
        this.cif_proveedor = cif_proveedor;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getImagen_componente() {
        return imagen_componente;
    }

    public void setImagen_componente(String imagen_componente) {
        this.imagen_componente = imagen_componente;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
