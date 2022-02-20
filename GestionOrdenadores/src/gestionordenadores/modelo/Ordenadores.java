/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionordenadores.modelo;

import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class Ordenadores {
    private int id;
    private String modelo;
    private float precio;
    private String dni_cliente;
    private Date fecha_montaje;

    public Ordenadores() {
    }

    public Ordenadores(int id, String modelo, float precio, String dni_cliente, Date fecha_montaje) {
        this.id = id;
        this.modelo = modelo;
        this.precio = precio;
        this.dni_cliente = dni_cliente;
        this.fecha_montaje = fecha_montaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(String dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    public Date getFecha_montaje() {
        return fecha_montaje;
    }

    public void setFecha_montaje(Date fecha_montaje) {
        this.fecha_montaje = fecha_montaje;
    }

    
}
