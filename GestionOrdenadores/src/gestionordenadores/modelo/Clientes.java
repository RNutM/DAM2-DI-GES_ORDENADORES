
package gestionordenadores.modelo;

/**
 *
 * @author Robert G
 */
public class Clientes {
    private String dni;
    private String nombre;
    private String apellidos;
    private String direccion;
    private int codigo_postal;
    private String poblacion;
    private String provincia;
    private String telefono;
    private String imagen_cliente;

    public Clientes() {
    }

    public Clientes(String dni, String nombre, String apellidos, String direccion, int codigo_postal, String poblacion, String provincia, String telefono, String imagen_cliente) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.telefono = telefono;
        this.imagen_cliente = imagen_cliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getImagen_cliente() {
        return imagen_cliente;
    }

    public void setImagen_cliente(String imagen_cliente) {
        this.imagen_cliente = imagen_cliente;
    }
}