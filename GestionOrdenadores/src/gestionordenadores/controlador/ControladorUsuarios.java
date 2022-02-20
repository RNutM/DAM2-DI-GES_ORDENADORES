package gestionordenadores.controlador;

import static gestionordenadores.controlador.ControladorPrincipal.usu;
import gestionordenadores.modelo.ConsultasAdmin;
import gestionordenadores.modelo.ConsultasUsuarios;
import gestionordenadores.modelo.Usuarios;
import gestionordenadores.vista.VistaAdmin;
import gestionordenadores.vista.VistaPrincipal;
import gestionordenadores.vista.VistaUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Robert G
 */
public class ControladorUsuarios implements ActionListener {

    Usuarios usuarios;
    ConsultasUsuarios consultasUsuarios;
    VistaUsuarios vistaUsuarios;
    VistaPrincipal vistaPrincipal;

    public ControladorUsuarios(Usuarios usuarios, ConsultasUsuarios consultasUsuarios, VistaUsuarios vistaUsuarios) {
        this.usuarios = usuarios;
        this.consultasUsuarios = consultasUsuarios;
        this.vistaUsuarios = vistaUsuarios;
    }

    public void inicioApp() {
        vistaUsuarios.setVisible(true);
        listeners();
        vistaUsuarios.txtUsuario.requestFocus();
    }

    private void listeners() {
        vistaUsuarios.btnSalir.addActionListener(this);
        vistaUsuarios.btnEntrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaUsuarios.btnEntrar) {

            try {
                if (consultasUsuarios.comprobarUsuPass(vistaUsuarios.txtUsuario.getText(), String.valueOf(vistaUsuarios.txtPassword.getPassword()))) {

                    if ("admin".equals(vistaUsuarios.txtUsuario.getText())) {
                        JOptionPane.showMessageDialog(null, "Entras en zona de administrador", "Administrador", JOptionPane.INFORMATION_MESSAGE);
                        //Modo Administrador para altas, bajas y modificaciones de usuarios
                        ControladorAdmin c = new ControladorAdmin(
                                new Usuarios(), new ConsultasAdmin(), new VistaUsuarios(), new VistaAdmin());
                        vistaUsuarios.setVisible(false);
                        c.inicioAdmin();
                    } else {
                        ControladorPrincipal c = new ControladorPrincipal(
                                new VistaUsuarios(), new VistaPrincipal());
                        vistaUsuarios.setVisible(false);
                        //Almaceno en la variable usu el usuario introducido
                        usu = vistaUsuarios.txtUsuario.getText();
                        c.inicioPrincipal();
                    }
                } else {
                    JOptionPane.showMessageDialog(vistaUsuarios,
                            "El usuario o contraseña son incorrectos o no existen en la BD",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == vistaUsuarios.btnSalir) {
            System.exit(0);
        }
    }
}//Fin clase