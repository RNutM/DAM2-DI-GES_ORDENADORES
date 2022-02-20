/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionordenadores.controlador;

import gestionordenadores.modelo.ConsultasAdmin;
import gestionordenadores.modelo.ConsultasUsuarios;
import gestionordenadores.modelo.Usuarios;
import gestionordenadores.vista.VistaAdmin;
import gestionordenadores.vista.VistaUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rober3
 */
/*NOTA IMPORTANTE: Si no voy a utilizar todos los métodos de MouseListener
 *no hace falta hacer implements sino heredar con extends MouseAdapter 
 */
public class ControladorAdmin extends MouseAdapter implements ActionListener {

    Usuarios usuarios;
    ConsultasAdmin consulAdmin;
    VistaUsuarios vistaUsuarios;
    VistaAdmin vistaAdmin;
    DefaultTableModel tbAdmin;

    public ControladorAdmin(Usuarios usuarios, ConsultasAdmin consulAdmin,
            VistaUsuarios vistaUsuarios, VistaAdmin vistaAdmin) {
        this.usuarios = usuarios;
        this.consulAdmin = consulAdmin;
        this.vistaUsuarios = vistaUsuarios;
        this.vistaAdmin = vistaAdmin;
    }

    void inicioAdmin() throws SQLException {
        vistaAdmin.setVisible(true);
        tbAdmin = new DefaultTableModel();
        vistaAdmin.jTable1.setModel(tbAdmin);
        cargarEncabezados();
        cargarUsuarios();
        listeners();
        //Muy importante cargarElementos siempre después de haber cargado
        //cargarUsuarios en este caso ya que si no, no recogera datos
        //y dara error de IndexOutOfBounds 0>=0
        cargarElementoTabla(0);//Selección primer elemento - 0 el primero

        vistaAdmin.btnOpciones.setVisible(false);
    }

    private void listeners() {
        vistaAdmin.btnVolver.addActionListener(this);
        vistaAdmin.btnOpciones.addActionListener(this);
        vistaAdmin.jTable1.addMouseListener(this);
        vistaAdmin.itemAltas.addActionListener(this);
        vistaAdmin.itemBajas.addActionListener(this);
        vistaAdmin.itemMod.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaAdmin.btnVolver) {
            vistaAdmin.setVisible(false);
            ControladorUsuarios c = new ControladorUsuarios(new Usuarios(),
                    new ConsultasUsuarios(), new VistaUsuarios());
            c.inicioApp();
        }
        if (e.getSource() == vistaAdmin.itemAltas) {
            vistaAdmin.btnOpciones.setVisible(true);
            vistaAdmin.btnOpciones.setText("Alta Usuario");
            try {//He creado un método con una consulta para que me ponga en 
                //el txt el siempre el siguiente id disponible
                vistaAdmin.txtIdUsuario.setText(String.valueOf(consulAdmin.idSiguiente()));
            } catch (SQLException ex) {
                Logger.getLogger(ControladorAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            vistaAdmin.txtNombre.setText(null);
            vistaAdmin.txtApellidos.setText(null);
            vistaAdmin.txtUsuario.setText(null);
            vistaAdmin.txtPass.setText(null);
            vistaAdmin.txtDir.setText(null);
        } else if (e.getSource() == vistaAdmin.itemBajas) {
            vistaAdmin.btnOpciones.setVisible(true);
            vistaAdmin.btnOpciones.setText("Baja Usuario");
        } else if (e.getSource() == vistaAdmin.itemMod) {
            vistaAdmin.btnOpciones.setVisible(true);
            vistaAdmin.btnOpciones.setText("Modificar Usuario");
        }

        if (e.getSource() == vistaAdmin.btnOpciones) {
            String boton = vistaAdmin.btnOpciones.getText();
            if (boton.contains("Alta Usuario")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de alta un usuario nuevo");
                try {
                    altaUsu();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("Baja Usuario")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de baja un usuario");
                try {
                    bajaUsu();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("Modificar Usuario")) {
                JOptionPane.showMessageDialog(null, "Vamos a modificar un usuario");
                try {
                    modUsu();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void cargarEncabezados() {
        tbAdmin.addColumn("ID Usuario");
        tbAdmin.addColumn("Nombre");
        tbAdmin.addColumn("Usuario");
        tbAdmin.addColumn("Contraseña");

        vistaAdmin.jTable1.getColumnModel().getColumn(0).setPreferredWidth(4);
        vistaAdmin.jTable1.getColumnModel().getColumn(1).setPreferredWidth(40);
        vistaAdmin.jTable1.getColumnModel().getColumn(2).setPreferredWidth(40);
        vistaAdmin.jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
    }

    private void cargarUsuarios() throws SQLException {
        tbAdmin.setRowCount(0); //Vaciamos la tabla

        ArrayList<Usuarios> listaUsu = consulAdmin.todosUsu();
        vistaAdmin.jTable1.setModel(tbAdmin);
        for (Usuarios usu : listaUsu) {
            Object[] objeto = new Object[4];
            objeto[0] = usu.getId();
            objeto[1] = usu.getNombre_usuario();
            objeto[2] = usu.getUsuario_usuario();
            objeto[3] = usu.getPassword_usuario();

            tbAdmin.addRow(objeto);
        }
    }

    private void cargarElementoTabla(int fila) throws SQLException {
        int id = (int) vistaAdmin.jTable1.getValueAt(fila, 0);
        cargarBuscarUsu(id);
    }

    private void cargarBuscarUsu(int id) throws SQLException {
        ArrayList<Usuarios> listaUsu = consulAdmin.buscaUsu(id);

        for (Usuarios usu : listaUsu) {
            vistaAdmin.txtIdUsuario.setText(String.valueOf(usu.getId()));
            vistaAdmin.txtNombre.setText(usu.getNombre_usuario());
            vistaAdmin.txtApellidos.setText(usu.getApellidos_usuario());
            vistaAdmin.txtUsuario.setText(usu.getUsuario_usuario());
            vistaAdmin.txtPass.setText(usu.getPassword_usuario());
            vistaAdmin.txtDir.setText(usu.getDireccion_usuario());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila, id;

        fila = vistaAdmin.jTable1.getSelectedRow();
        id = Integer.parseInt(vistaAdmin.jTable1.getValueAt(fila, 0).toString());

        try {
            cargarBuscarUsu(id);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void altaUsu() throws SQLException {
        if (vistaAdmin.txtNombre.getText().isEmpty()
                || vistaAdmin.txtApellidos.getText().isEmpty()
                || vistaAdmin.txtUsuario.getText().isEmpty()
                || vistaAdmin.txtPass.getText().isEmpty()
                || vistaAdmin.txtDir.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alguno de los campos está vacío", "vacio", JOptionPane.ERROR_MESSAGE);
        } else {
            int id_usu = Integer.parseInt(vistaAdmin.txtIdUsuario.getText());
            String nom_usu = vistaAdmin.txtNombre.getText();
            String ape_usu = vistaAdmin.txtApellidos.getText();
            String usu_usu = vistaAdmin.txtUsuario.getText();
            String pas_usu = vistaAdmin.txtPass.getText();
            String dir_usu = vistaAdmin.txtDir.getText();
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulAdmin.altas(id_usu, nom_usu, ape_usu, usu_usu, pas_usu, dir_usu);

            JOptionPane.showMessageDialog(null, "El alta del usuario con la Id: " + vistaAdmin.txtIdUsuario.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarUsuarios();//Cargo la pantalla de nuevo con los productos

            //Pongo en blanco todos los campos
            vistaAdmin.txtIdUsuario.setText(null);
            vistaAdmin.txtNombre.setText(null);
            vistaAdmin.txtApellidos.setText(null);
            vistaAdmin.txtUsuario.setText(null);
            vistaAdmin.txtPass.setText(null);
            vistaAdmin.txtDir.setText(null);
        }
    }

    private void bajaUsu() throws SQLException {
        int fila, id;
        fila = vistaAdmin.jTable1.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            id = Integer.parseInt(vistaAdmin.jTable1.getValueAt(fila, 0).toString());
            JOptionPane.showMessageDialog(null, "La baja de la Id " + vistaAdmin.txtIdUsuario.getText() + " ha sido correcta", "Baja correcta", JOptionPane.INFORMATION_MESSAGE);

            consulAdmin.bajas(id);
            cargarUsuarios(); //Cargo la pantalla de nuevo con los usuarios
        }
    }

    private void modUsu() throws SQLException {
        int fila;
        fila = vistaAdmin.jTable1.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            int id_usu = Integer.parseInt(vistaAdmin.txtIdUsuario.getText());
            String nom_usu = vistaAdmin.txtNombre.getText();
            String ape_usu = vistaAdmin.txtApellidos.getText();
            String usu_usu = vistaAdmin.txtUsuario.getText();
            String pas_usu = vistaAdmin.txtPass.getText();
            String dir_usu = vistaAdmin.txtDir.getText();
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulAdmin.mod(id_usu, nom_usu, ape_usu, usu_usu, pas_usu, dir_usu);

            JOptionPane.showMessageDialog(null, "La modificación del usuario con ID: " + vistaAdmin.txtIdUsuario.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarUsuarios();//Cargo la pantalla de nuevo con los productos
        }
    }
}//Fin clase
