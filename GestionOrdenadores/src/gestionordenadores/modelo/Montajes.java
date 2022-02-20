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
public class Montajes {
    private int id_ordenador;
    private int id_componente;
    private int id_operario;

    public Montajes() {
    }

    public Montajes(int id_ordenador, int id_componente, int id_operario) {
        this.id_ordenador = id_ordenador;
        this.id_componente = id_componente;
        this.id_operario = id_operario;
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
    
    
}
