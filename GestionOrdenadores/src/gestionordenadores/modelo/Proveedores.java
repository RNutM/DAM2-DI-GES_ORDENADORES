package gestionordenadores.modelo;

/**
 *
 * @author Robert G
 */
public class Proveedores {
    private String cif;
    private String nombre;
    private String direccion;
    private int codigo_postal;
    private String poblacion;
    private String provincia;
    private String telefono;
    private String imagen_proveedor;

    public Proveedores() {
    }

    public Proveedores(String cif, String nombre, String direccion, int codigo_postal, String poblacion, String provincia, String telefono, String imagen_proveedor) {
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.telefono = telefono;
        this.imagen_proveedor = imagen_proveedor;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(int codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getImagen_proveedor() {
        return imagen_proveedor;
    }

    public void setImagen_proveedor(String imagen_proveedor) {
        this.imagen_proveedor = imagen_proveedor;
    }
    
    
}
