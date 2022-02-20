/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionordenadores.modelo;

/**
 *
 * @author Usuario
 */
public class Operarios {
    private int id;
    private String nombre;
    private String apellidos;
    private String imagen_operario;

    public Operarios() {
    }

    public Operarios(int id, String nombre, String apellidos, String imagen_operario) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.imagen_operario = imagen_operario;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getImagen_operario() {
        return imagen_operario;
    }

    public void setImagen_operario(String imagen_operario) {
        this.imagen_operario = imagen_operario;
    }
}
