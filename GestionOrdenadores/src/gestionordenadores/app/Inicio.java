/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionordenadores.app;

import gestionordenadores.controlador.ControladorUsuarios;
import gestionordenadores.modelo.ConsultasUsuarios;
import gestionordenadores.modelo.Usuarios;
import gestionordenadores.vista.VistaUsuarios;

/**
 *
 * @author Usuario
 */
public class Inicio {
    
    public static void main(String[] args){
        ControladorUsuarios c = new ControladorUsuarios(new Usuarios(),
        new ConsultasUsuarios(), new VistaUsuarios());
        c.inicioApp();
    }
}
