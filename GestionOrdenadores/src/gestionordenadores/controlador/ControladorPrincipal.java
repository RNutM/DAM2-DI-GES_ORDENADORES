package gestionordenadores.controlador;

import gestionordenadores.modelo.Clientes;
import gestionordenadores.modelo.Componentes;
import gestionordenadores.modelo.ConsultasClientes;
import gestionordenadores.modelo.ConsultasComponentes;
import gestionordenadores.modelo.ConsultasMontajes;
import gestionordenadores.modelo.ConsultasOperarios;
import gestionordenadores.modelo.ConsultasOrdenadores;
import gestionordenadores.modelo.ConsultasProveedores;
import gestionordenadores.modelo.ConsultasReparaciones;
import gestionordenadores.modelo.ConsultasTipos;
import gestionordenadores.modelo.ConsultasUsuarios;
import gestionordenadores.modelo.Montajes;
import gestionordenadores.modelo.Operarios;
import gestionordenadores.modelo.Ordenadores;
import gestionordenadores.modelo.Proveedores;
import gestionordenadores.modelo.Reparaciones;
import gestionordenadores.modelo.Tipos;
import gestionordenadores.modelo.Usuarios;
import gestionordenadores.vista.VistaAyuda;
import gestionordenadores.vista.VistaPrincipal;
import gestionordenadores.vista.VistaUsuarios;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Robert G
 */
public class ControladorPrincipal extends MouseAdapter implements ActionListener, ItemListener {

    public static String usu;
    VistaUsuarios vistaUsuarios;
    VistaPrincipal vistaPrincipal;
    VistaAyuda vistaAyuda;
    Operarios operarios;
    ConsultasOperarios consulOper;
    Clientes clientes;
    ConsultasClientes consulCli;
    Proveedores proveedores;
    ConsultasProveedores consulPro;
    Tipos tipos;
    ConsultasTipos consulTip;
    Componentes componentes;
    ConsultasComponentes consulCom;
    Ordenadores ordenadores;
    ConsultasOrdenadores consulOrd;
    Montajes montajes;
    ConsultasMontajes consulMon;
    Reparaciones reparaciones;
    ConsultasReparaciones consulRep;
    DefaultTableModel tbOper, tbClien, tbProv, tbTip, tbCom, tbOrd, tbMon, tbRep;

    public ControladorPrincipal(VistaUsuarios vistaUsuarios, VistaPrincipal vistaPrincipal) {
        this.vistaUsuarios = vistaUsuarios;
        this.vistaPrincipal = vistaPrincipal;
        consulOper = new ConsultasOperarios();
        consulCli = new ConsultasClientes();
        consulPro = new ConsultasProveedores();
        consulTip = new ConsultasTipos();
        consulCom = new ConsultasComponentes();
        consulOrd = new ConsultasOrdenadores();
        consulMon = new ConsultasMontajes();
        consulRep = new ConsultasReparaciones();
    }

    public void cambiaTamaImagenOper(String operarios, int ancho, int alto) {
        ImageIcon original = new ImageIcon(operarios);
        Image aux = original.getImage();
        aux = aux.getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        vistaPrincipal.lblImagenOper.setIcon(new ImageIcon(aux));
    }

    public void cambiaTamaImagenCli(String clientes, int ancho, int alto) {
        ImageIcon original = new ImageIcon(clientes);
        Image aux = original.getImage();
        aux = aux.getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        vistaPrincipal.lblImagenCli.setIcon(new ImageIcon(aux));
    }

    public void cambiaTamaImagenPro(String proveedores, int ancho, int alto) {
        ImageIcon original = new ImageIcon(proveedores);
        Image aux = original.getImage();
        aux = aux.getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        vistaPrincipal.lblImagenPro.setIcon(new ImageIcon(aux));
    }

    public void cambiaTamaImagenCom(String componentes, int ancho, int alto) {
        ImageIcon original = new ImageIcon(componentes);
        Image aux = original.getImage();
        aux = aux.getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        vistaPrincipal.lblImagenCom.setIcon(new ImageIcon(aux));
    }

    void inicioPrincipal() throws SQLException {
        vistaPrincipal.setVisible(true);
        //Muestro en que usuario me encuentro logueado
        vistaPrincipal.lblUsuariio.setText(usu);
        cargarCombos();//Cargo los comboBox
        vistaPrincipal.cbNomProv.setEnabled(false);
        vistaPrincipal.cbNomTip.setEnabled(false);
        //vistaPrincipal.cbNomProv.setSelectedItem(null);//Los pongo en blanco
        //vistaPrincipal.cbNomTip.setSelectedItem(null);//Los pongo en blanco
        listeners();

        tbOper = new DefaultTableModel();
        vistaPrincipal.jTableOper.setModel(tbOper);
        cargarencabezadosOper();
        cargarOper();
        //Muy importante cargarElementos siempre después de haber cargado
        //cargar en este caso ya que si no, no recogera datos
        //y dara error de IndexOutOfBounds 0>=0
        cargarElementoTablaOper(0);//Selección primer elemento - 0 el primero

        tbClien = new DefaultTableModel();
        vistaPrincipal.jTableCli.setModel(tbClien);
        cargarencabezadosCli();
        cargarCli();
        //Muy importante cargarElementos siempre después de haber cargado
        //cargar en este caso ya que si no, no recogera datos
        //y dara error de IndexOutOfBounds 0>=0
        cargarElementoTablaCli(0);//Selección primer elemento - 0 el primero

        tbProv = new DefaultTableModel();
        vistaPrincipal.jTablePro.setModel(tbProv);
        cargarencabezadosPro();
        cargarPro();
        //Muy importante cargarElementos siempre después de haber cargado
        //cargar en este caso ya que si no, no recogera datos
        //y dara error de IndexOutOfBounds 0>=0
        cargarElementoTablaPro(0);//Selección primer elemento - 0 el primero

        tbTip = new DefaultTableModel();
        vistaPrincipal.jTableTipos.setModel(tbTip);
        cargarencabezadosTip();
        cargarTip();
        //Muy importante cargarElementos siempre después de haber cargado
        //cargar en este caso ya que si no, no recogera datos
        //y dara error de IndexOutOfBounds 0>=0
        cargarElementoTablaTip(0);//Selección primer elemento - 0 el primero

        tbCom = new DefaultTableModel();
        vistaPrincipal.jTableCom.setModel(tbCom);
        cargarencabezadosCom();
        cargarCom();
        //Muy importante cargarElementos siempre después de haber cargado
        //cargar en este caso ya que si no, no recogera datos
        //y dara error de IndexOutOfBounds 0>=0
        cargarElementoTablaCom(0);//Selección primer elemento - 0 el primero

        tbOrd = new DefaultTableModel();
        vistaPrincipal.jTableOrd.setModel(tbOrd);
        cargarencabezadosOrd();
        cargarOrd();
        //Muy importante cargarElementos siempre después de haber cargado
        //cargar en este caso ya que si no, no recogera datos
        //y dara error de IndexOutOfBounds 0>=0
        cargarElementoTableOrd(0);//Selección primer elemento - 0 el primero

        tbMon = new DefaultTableModel();
        vistaPrincipal.jTableMon.setModel(tbMon);
        cargarencabezadosMon();
        cargarMon();
        //Muy importante cargarElementos siempre después de haber cargado
        //cargar en este caso ya que si no, no recogera datos
        //y dara error de IndexOutOfBounds 0>=0
        cargarElementoTableMon(0);//Selección primer elemento - 0 el primero

        tbRep = new DefaultTableModel();
        vistaPrincipal.jTableRep.setModel(tbRep);
        cargarencabezadosRep();
        cargarRep();
        //Muy importante cargarElementos siempre después de haber cargado
        //cargar en este caso ya que si no, no recogera datos
        //y dara error de IndexOutOfBounds 0>=0
        cargarElementoTableRep(0);//Selección primer elemento - 0 el primero
    }

    private void listeners() {
        vistaPrincipal.btnDeslog.addActionListener(this);
        vistaPrincipal.btnAyuda.addActionListener(this);

        vistaPrincipal.btnOpcionesOper.addActionListener(this);
        vistaPrincipal.jTableOper.addMouseListener(this);
        vistaPrincipal.jRAltasOper.addActionListener(this);
        vistaPrincipal.jRBajasOper.addActionListener(this);
        vistaPrincipal.jRModifOper.addActionListener(this);

        vistaPrincipal.btnOpcionesCli.addActionListener(this);
        vistaPrincipal.jTableCli.addMouseListener(this);
        vistaPrincipal.jRAltasCli.addActionListener(this);
        vistaPrincipal.jRBajasCli.addActionListener(this);
        vistaPrincipal.jRModifCli.addActionListener(this);

        vistaPrincipal.btnOpcionesPro.addActionListener(this);
        vistaPrincipal.jTablePro.addMouseListener(this);
        vistaPrincipal.jRAltasPro.addActionListener(this);
        vistaPrincipal.jRBajasPro.addActionListener(this);
        vistaPrincipal.jRModifPro.addActionListener(this);

        vistaPrincipal.btnOpcionesTipo.addActionListener(this);
        vistaPrincipal.jTableTipos.addMouseListener(this);
        vistaPrincipal.jRAltasTipo.addActionListener(this);
        vistaPrincipal.jRBajasTipo.addActionListener(this);
        vistaPrincipal.jRModifTipo.addActionListener(this);

        vistaPrincipal.btnOpcionesCom.addActionListener(this);
        vistaPrincipal.jTableCom.addMouseListener(this);
        vistaPrincipal.jRAltasCom.addActionListener(this);
        vistaPrincipal.jRBajasCom.addActionListener(this);
        vistaPrincipal.jRModifCom.addActionListener(this);

        vistaPrincipal.btnOpcionesOrd.addActionListener(this);
        vistaPrincipal.jTableOrd.addMouseListener(this);
        vistaPrincipal.jRAltasOrd.addActionListener(this);
        vistaPrincipal.jRBajasOrd.addActionListener(this);
        vistaPrincipal.jRModifOrd.addActionListener(this);

        vistaPrincipal.jTableMon.addMouseListener(this);
        vistaPrincipal.jTableRep.addMouseListener(this);

        vistaPrincipal.cbNomProv.addItemListener(this);
        vistaPrincipal.cbNomTip.addItemListener(this);
        vistaPrincipal.cbDniCli.addItemListener(this);
        
        vistaPrincipal.btnVistaAgrup.addActionListener(this);
        vistaPrincipal.btnPdfAgrup.addActionListener(this);
        vistaPrincipal.btnVistaParam.addActionListener(this);
        vistaPrincipal.btnPdfParam.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaPrincipal.btnDeslog) {
            vistaPrincipal.setVisible(false);
            ControladorUsuarios c = new ControladorUsuarios(new Usuarios(),
                    new ConsultasUsuarios(), new VistaUsuarios());
            c.inicioApp();
        }
        if (e.getSource() == vistaPrincipal.btnAyuda){
            vistaPrincipal.setVisible(false);
            ControladorAyuda c = new ControladorAyuda(new VistaAyuda());
            c.inicioAyuda();
        }
        ////////////////////////////OPERARIOS/////////////////////////////////    
        if (e.getSource() == vistaPrincipal.jRAltasOper) {
            vistaPrincipal.btnOpcionesOper.setText("ALTAS");
            try {
                vistaPrincipal.txtIdOper.setText(String.valueOf(consulOper.idSiguiente()));
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Pongo en blanco todos los campos
            vistaPrincipal.txtNombreOper.setText(null);
            vistaPrincipal.txtApellidosOper.setText(null);

        } else if (e.getSource() == vistaPrincipal.jRBajasOper) {
            vistaPrincipal.btnOpcionesOper.setText("BAJAS");
            vistaPrincipal.txtIdOper.requestFocus();//Pongo el foco en el id
            vistaPrincipal.jTableOper.setSelectionMode(0);//Deseleccion la tabla
        } else if (e.getSource() == vistaPrincipal.jRModifOper) {
            vistaPrincipal.btnOpcionesOper.setText("MOFIFICACIONES");
            vistaPrincipal.txtIdOper.requestFocus();//Pongo el foco en el id
            vistaPrincipal.jTableOper.setSelectionMode(0);//Deseleccion la tabla
        }

        if (e.getSource() == vistaPrincipal.btnOpcionesOper) {
            String boton = vistaPrincipal.btnOpcionesOper.getText();
            if (boton.contains("ALTAS")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de alta un operario nuevo");
                try {
                    altaOper();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("BAJAS")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de baja un operario");
                try {
                    bajaOper();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("MOFIFICACIONES")) {
                JOptionPane.showMessageDialog(null, "Vamos a modificar un operario");
                try {
                    modOper();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }//////////////////////////FIN OPERARIOS//////////////////////////////

        ////////////////////////////CLIENTES/////////////////////////////////    
        if (e.getSource() == vistaPrincipal.jRAltasCli) {
            vistaPrincipal.btnOpcionesCli.setText("ALTAS");
            //Pongo en blanco todos los campos
            vistaPrincipal.txtDniCli.setText(null);
            vistaPrincipal.txtNombreCli.setText(null);
            vistaPrincipal.txtApellidosCli.setText(null);
            vistaPrincipal.txtDireccCli.setText(null);
            vistaPrincipal.txtCodPostCli.setText(null);
            vistaPrincipal.txtPoblaCli.setText(null);
            vistaPrincipal.txtProvCli.setText(null);
            vistaPrincipal.txtTelCli.setText(null);
        } else if (e.getSource() == vistaPrincipal.jRBajasCli) {
            vistaPrincipal.btnOpcionesCli.setText("BAJAS");
            vistaPrincipal.txtDniCli.requestFocus();//Pongo el foco en el id
            vistaPrincipal.jTableCli.setSelectionMode(0);//Deselecciono la tabla
        } else if (e.getSource() == vistaPrincipal.jRModifCli) {
            vistaPrincipal.btnOpcionesCli.setText("MOFIFICACIONES");
            vistaPrincipal.txtDniCli.requestFocus();//Pongo el foco en el id
            vistaPrincipal.jTableCli.setSelectionMode(0);//Deselecciono la tabla
        }

        if (e.getSource() == vistaPrincipal.btnOpcionesCli) {
            String boton = vistaPrincipal.btnOpcionesCli.getText();
            if (boton.contains("ALTAS")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de alta un cliente nuevo");
                try {
                    altaCli();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("BAJAS")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de baja un cliente");
                try {
                    bajaCli();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("MOFIFICACIONES")) {
                JOptionPane.showMessageDialog(null, "Vamos a modificar un cliente");
                try {
                    modCli();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }//////////////////////////FIN CLIENTES//////////////////////////////
        ////////////////////////////PROVEEDORES//////////////////////////////    
        if (e.getSource() == vistaPrincipal.jRAltasPro) {
            vistaPrincipal.btnOpcionesPro.setText("ALTAS");
            //Pongo en blanco todos los campos
            vistaPrincipal.txtCifPro.setText(null);
            vistaPrincipal.txtNombrePro.setText(null);
            vistaPrincipal.txtDireccPro.setText(null);
            vistaPrincipal.txtCodPostPro.setText(null);
            vistaPrincipal.txtPoblaPro.setText(null);
            vistaPrincipal.txtProvPro.setText(null);
            vistaPrincipal.txtTelPro.setText(null);
        } else if (e.getSource() == vistaPrincipal.jRBajasPro) {
            vistaPrincipal.btnOpcionesPro.setText("BAJAS");
            vistaPrincipal.txtCifPro.requestFocus();//Pongo el foco en el id
            vistaPrincipal.jTablePro.setSelectionMode(0);//Deselecciono la tabla
        } else if (e.getSource() == vistaPrincipal.jRModifPro) {
            vistaPrincipal.btnOpcionesPro.setText("MOFIFICACIONES");
            vistaPrincipal.txtCifPro.requestFocus();//Pongo el foco en el id
            vistaPrincipal.jTablePro.setSelectionMode(0);//Deselecciono la tabla
        }

        if (e.getSource() == vistaPrincipal.btnOpcionesPro) {
            String boton = vistaPrincipal.btnOpcionesPro.getText();
            if (boton.contains("ALTAS")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de alta un proveedor nuevo");
                try {
                    altaPro();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("BAJAS")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de baja un proveedor");
                try {
                    bajaPro();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("MOFIFICACIONES")) {
                JOptionPane.showMessageDialog(null, "Vamos a modificar un proveedor");
                try {
                    modPro();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }//////////////////////////FIN PROVEEDORES//////////////////////////////
        ////////////////////////////TIPOS/////////////////////////////////    
        if (e.getSource() == vistaPrincipal.jRAltasTipo) {
            vistaPrincipal.btnOpcionesTipo.setText("ALTAS");
            try {
                vistaPrincipal.txtIdTipo.setText(String.valueOf(consulTip.idSiguiente()));
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Pongo en blanco todos los campos
            vistaPrincipal.txtTipoCom.setText(null);

        } else if (e.getSource() == vistaPrincipal.jRBajasTipo) {
            vistaPrincipal.btnOpcionesTipo.setText("BAJAS");
            vistaPrincipal.txtIdTipo.requestFocus();
            vistaPrincipal.jTableTipos.setSelectionMode(0);
            vistaPrincipal.txtIdTipo.setText(null);
        } else if (e.getSource() == vistaPrincipal.jRModifTipo) {
            vistaPrincipal.btnOpcionesTipo.setText("MOFIFICACIONES");
        }

        if (e.getSource() == vistaPrincipal.btnOpcionesTipo) {
            String boton = vistaPrincipal.btnOpcionesTipo.getText();
            if (boton.contains("ALTAS")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de alta un tipo nuevo");
                try {
                    altaTip();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("BAJAS")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de baja un tipo");
                try {
                    bajaTip();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("MOFIFICACIONES")) {
                JOptionPane.showMessageDialog(null, "Vamos a modificar un tipo");
                try {
                    modTip();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }//////////////////////////FIN TIPOS//////////////////////////////
        //////////////////////////COMPONENTES/////////////////////////////    
        if (e.getSource() == vistaPrincipal.jRAltasCom) {
            vistaPrincipal.btnOpcionesCom.setText("ALTAS");
            try {
                vistaPrincipal.txtIdCom.setText(String.valueOf(consulCom.idSiguiente()));
                vistaPrincipal.cbNomProv.setEnabled(true);
                vistaPrincipal.cbNomTip.setEnabled(true);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Pongo en blanco todos los campos
            vistaPrincipal.txtNombreCom.setText(null);
            vistaPrincipal.txtMarcaCom.setText(null);
            vistaPrincipal.txtDescriCom.setText(null);
            vistaPrincipal.txtPrecioCom.setText(null);
            vistaPrincipal.txtCifProCom.setText(null);
            vistaPrincipal.txtIdTipoCom.setText(null);
            vistaPrincipal.txtEstadoCom.setText("1");

        } else if (e.getSource() == vistaPrincipal.jRBajasCom) {
            vistaPrincipal.btnOpcionesCom.setText("BAJAS");
            vistaPrincipal.cbNomProv.setEnabled(false);
            vistaPrincipal.cbNomTip.setEnabled(false);
            vistaPrincipal.txtIdCom.requestFocus();
            vistaPrincipal.jTableCom.setSelectionMode(0);
        } else if (e.getSource() == vistaPrincipal.jRModifCom) {
            vistaPrincipal.btnOpcionesCom.setText("MOFIFICACIONES");
            vistaPrincipal.cbNomProv.setEnabled(false);
            vistaPrincipal.cbNomTip.setEnabled(false);

        }

        if (e.getSource() == vistaPrincipal.btnOpcionesCom) {
            String boton = vistaPrincipal.btnOpcionesCom.getText();
            if (boton.contains("ALTAS")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de alta un componente nuevo");
                try {
                    altaCom();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("BAJAS")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de baja un componente");
                try {
                    bajaCom();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("MOFIFICACIONES")) {
                JOptionPane.showMessageDialog(null, "Vamos a modificar un componente");
                try {
                    modCom();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }//////////////////////////FIN COMPONENTES///////////////////////////
        ////////////////////////////ORDENADORES/////////////////////////////    
        if (e.getSource() == vistaPrincipal.jRAltasOrd) {
            vistaPrincipal.btnOpcionesOrd.setText("ALTAS");
            try {
                vistaPrincipal.txtIdOrd.setText(String.valueOf(consulOrd.idSiguiente()));
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Pongo en blanco todos los campos
            vistaPrincipal.txtModeloOrd.setText(null);
            vistaPrincipal.txtPrecioOrd.setText(null);
            vistaPrincipal.cbDniCli.setSelectedItem(null);
            vistaPrincipal.fechaMont.setDate(null);

        } else if (e.getSource() == vistaPrincipal.jRBajasOrd) {
            vistaPrincipal.btnOpcionesOrd.setText("BAJAS");
            vistaPrincipal.txtModeloOrd.requestFocus();
            vistaPrincipal.jTableOrd.setSelectionMode(0);

        } else if (e.getSource() == vistaPrincipal.jRModifOrd) {
            vistaPrincipal.btnOpcionesOrd.setText("MOFIFICACIONES");
        }

        if (e.getSource() == vistaPrincipal.btnOpcionesOrd) {
            String boton = vistaPrincipal.btnOpcionesOrd.getText();
            if (boton.contains("ALTAS")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de alta un ordenador nuevo");
                try {
                    altaOrd();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("BAJAS")) {
                JOptionPane.showMessageDialog(null, "Vamos a dar de baja un ordenador");
                try {
                    bajaOrd();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (boton.contains("MOFIFICACIONES")) {
                JOptionPane.showMessageDialog(null, "Vamos a modificar un ordenador");
                try {
                    modOrd();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }/////////////////////////FIN ORDENADORES///////////////////////////////
        
        //////////////////////////INFORMES//////////////////////////////////////
        
        //////////////////////////AGRUPADO VISTA PREVIA/////////////////////////
        if (e.getSource() == vistaPrincipal.btnVistaAgrup){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            Connection con = null;
            try {
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionordenadores", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Añadir librerias JasperReport
            JasperPrint print = null;
            try {
                print = JasperFillManager.fillReport("informes/componentesagrupadostipo.jasper", null, con);
            } catch (JRException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Vista Previa por pantalla", "Vista en Pantalla", JOptionPane.WARNING_MESSAGE);
            JasperViewer.viewReport(print,false);
            //////////////////////////FIN AGRUPADO VISTA PREVIA/////////////////
            //////////////////////////AGRUPADO PDF//////////////////////////////
        } else if (e.getSource() == vistaPrincipal.btnPdfAgrup){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            Connection con = null;
            try {
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionordenadores", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Añadir librerias JasperReport
            JasperPrint print = null;
            try {
                print = JasperFillManager.fillReport("informes/componentesagrupadostipo.jasper", null, con);
            } catch (JRException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                JOptionPane.showMessageDialog(null, "Listado en pdf - Ve a la carpeta informes para ver el archivo", "Conversión a PDF", JOptionPane.INFORMATION_MESSAGE);
                JasperExportManager.exportReportToPdfFile(print, "informes/componentesagrupadostipo.pdf");
            } catch (JRException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }//////////////////////////FIN AGRUPADO PDF/////////////////////////
             ///////////////////PARAMETRIZADO VISTA PREVIA//////////////////////   
        }else if (e.getSource() == vistaPrincipal.btnVistaParam){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            Connection con = null;
            try {
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionordenadores", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Pasar parametros
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("MARCA", vistaPrincipal.cbParam.getSelectedItem());
            
            //Añadir librerias JasperReport
            JasperPrint print = null;
            try {
                print = JasperFillManager.fillReport("informes/componentesparametrizadosmarca.jasper", parametros, con);
            } catch (JRException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
           JOptionPane.showMessageDialog(null, "Vista Previa por pantalla", "Vista en Pantalla", JOptionPane.WARNING_MESSAGE);
            JasperViewer.viewReport(print,false);
        }///////////////////FIN PARAMETRIZADO VISTA PREVIA//////////////////////
        //////////////////////////PARAMETRIZADO PDF///////////////////////////
        else if (e.getSource() == vistaPrincipal.btnPdfParam){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            Connection con = null;
            try {
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionordenadores", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Pasar parametros
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("MARCA", vistaPrincipal.cbParam.getSelectedItem());
            
            //Añadir librerias JasperReport
            JasperPrint print = null;
            try {
                print = JasperFillManager.fillReport("informes/componentesparametrizadosmarca.jasper", parametros, con);
            } catch (JRException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                JOptionPane.showMessageDialog(null, "Listado en pdf - Ve a la carpeta informes para ver el archivo", "Conversión a PDF", JOptionPane.INFORMATION_MESSAGE);
                JasperExportManager.exportReportToPdfFile(print, "informes/componentesparametrizadosmarca.pdf");
            } catch (JRException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//////////////////////////FIN PARAMETRIZADO PDF///////////////////////////
        

//////////////////////////FIN INFORMES///////////////////////////////
    }//Fin actionPerformed

    //Cargo todos los combos con datos de la BD
    private void cargarCombos() throws SQLException {
        ArrayList<String> proveedores = consulPro.buscaProNom();
        ArrayList<String> tipos = consulTip.buscaTipNom();
        ArrayList<String> ordenadores = consulOrd.buscaDni();
        ArrayList<String> componentes = consulCom.buscaMarca();
        for (String proveedor : proveedores) {
            vistaPrincipal.cbNomProv.addItem(proveedor);
        }
        for (String tipo : tipos) {
            vistaPrincipal.cbNomTip.addItem(tipo);
        }
        for (String ordenador : ordenadores) {
            vistaPrincipal.cbDniCli.addItem(ordenador);
        }
        for (String componente : componentes){
            vistaPrincipal.cbParam.addItem(componente);
        }
    }

    private void cargarencabezadosOper() {
        tbOper.addColumn("ID");
        tbOper.addColumn("Nombre");
        tbOper.addColumn("Apellidos");
        vistaPrincipal.jTableOper.getColumnModel().getColumn(0).setPreferredWidth(8);
        vistaPrincipal.jTableOper.getColumnModel().getColumn(1).setPreferredWidth(40);
        vistaPrincipal.jTableOper.getColumnModel().getColumn(2).setPreferredWidth(40);
    }

    private void cargarencabezadosCli() {
        tbClien.addColumn("DNI");
        tbClien.addColumn("Nombre");
        tbClien.addColumn("Apellidos");
        tbClien.addColumn("Teléfono");
        vistaPrincipal.jTableCli.getColumnModel().getColumn(0).setPreferredWidth(15);
        vistaPrincipal.jTableCli.getColumnModel().getColumn(1).setPreferredWidth(50);
        vistaPrincipal.jTableCli.getColumnModel().getColumn(2).setPreferredWidth(80);
        vistaPrincipal.jTableCli.getColumnModel().getColumn(0).setPreferredWidth(12);
    }

    private void cargarencabezadosPro() {
        tbProv.addColumn("CIF");
        tbProv.addColumn("Nombre");
        tbProv.addColumn("Provincia");
        tbProv.addColumn("Teléfono");
        vistaPrincipal.jTablePro.getColumnModel().getColumn(0).setPreferredWidth(15);
        vistaPrincipal.jTablePro.getColumnModel().getColumn(1).setPreferredWidth(50);
        vistaPrincipal.jTablePro.getColumnModel().getColumn(2).setPreferredWidth(80);
        vistaPrincipal.jTablePro.getColumnModel().getColumn(3).setPreferredWidth(12);
    }

    private void cargarencabezadosTip() {
        tbTip.addColumn("ID");
        tbTip.addColumn("Tipo");
        vistaPrincipal.jTableTipos.getColumnModel().getColumn(0).setPreferredWidth(15);
        vistaPrincipal.jTableTipos.getColumnModel().getColumn(1).setPreferredWidth(50);
    }

    private void cargarencabezadosCom() {
        tbCom.addColumn("ID");
        tbCom.addColumn("Nombre");
        tbCom.addColumn("Descripción");
        tbCom.addColumn("Precio");
        vistaPrincipal.jTableCom.getColumnModel().getColumn(0).setPreferredWidth(6);
        vistaPrincipal.jTableCom.getColumnModel().getColumn(1).setPreferredWidth(50);
        vistaPrincipal.jTableCom.getColumnModel().getColumn(2).setPreferredWidth(100);
        vistaPrincipal.jTableCom.getColumnModel().getColumn(3).setPreferredWidth(6);
    }

    private void cargarencabezadosOrd() {
        tbOrd.addColumn("ID");
        tbOrd.addColumn("Modelo");
        tbOrd.addColumn("Precio");
        tbOrd.addColumn("Fecha");
        vistaPrincipal.jTableOrd.getColumnModel().getColumn(0).setPreferredWidth(6);
        vistaPrincipal.jTableOrd.getColumnModel().getColumn(1).setPreferredWidth(80);
        vistaPrincipal.jTableOrd.getColumnModel().getColumn(2).setPreferredWidth(20);
        vistaPrincipal.jTableOrd.getColumnModel().getColumn(3).setPreferredWidth(6);
    }

    private void cargarencabezadosMon() {
        tbMon.addColumn("ID ordenador");
        tbMon.addColumn("ID Componente");
        tbMon.addColumn("ID Operario");
        vistaPrincipal.jTableMon.getColumnModel().getColumn(0).setPreferredWidth(10);
        vistaPrincipal.jTableMon.getColumnModel().getColumn(1).setPreferredWidth(10);
        vistaPrincipal.jTableMon.getColumnModel().getColumn(2).setPreferredWidth(10);
    }

    private void cargarencabezadosRep() {
        tbRep.addColumn("ID ordenador");
        tbRep.addColumn("ID Componente");
        tbRep.addColumn("ID Operario");
        vistaPrincipal.jTableRep.getColumnModel().getColumn(0).setPreferredWidth(10);
        vistaPrincipal.jTableRep.getColumnModel().getColumn(1).setPreferredWidth(10);
        vistaPrincipal.jTableRep.getColumnModel().getColumn(2).setPreferredWidth(10);
    }

    private void cargarOper() throws SQLException {
        tbOper.setRowCount(0); //Vaciamos la tabla
        ArrayList<Operarios> listaOpe = consulOper.todosOpe();
        vistaPrincipal.jTableOper.setModel(tbOper);
        for (Operarios oper : listaOpe) {
            Object[] objeto = new Object[3];
            objeto[0] = oper.getId();
            objeto[1] = oper.getNombre();
            objeto[2] = oper.getApellidos();
            tbOper.addRow(objeto);
        }
    }

    private void cargarCli() throws SQLException {
        tbClien.setRowCount(0); //Vaciamos la tabla
        ArrayList<Clientes> listaCli = consulCli.todosCli();
        vistaPrincipal.jTableCli.setModel(tbClien);
        for (Clientes cli : listaCli) {
            Object[] objeto = new Object[4];
            objeto[0] = cli.getDni();
            objeto[1] = cli.getNombre();
            objeto[2] = cli.getApellidos();
            objeto[3] = cli.getTelefono();
            tbClien.addRow(objeto);
        }
    }

    private void cargarPro() throws SQLException {
        tbProv.setRowCount(0); //Vaciamos la tabla
        ArrayList<Proveedores> listaPro = consulPro.todosPro();
        vistaPrincipal.jTablePro.setModel(tbProv);
        for (Proveedores pro : listaPro) {
            Object[] objeto = new Object[4];
            objeto[0] = pro.getCif();
            objeto[1] = pro.getNombre();
            objeto[2] = pro.getProvincia();
            objeto[3] = pro.getTelefono();
            tbProv.addRow(objeto);
        }
    }

    private void cargarTip() throws SQLException {
        tbTip.setRowCount(0); //Vaciamos la tabla
        ArrayList<Tipos> listaTip = consulTip.todosTip();
        vistaPrincipal.jTableTipos.setModel(tbTip);
        for (Tipos tip : listaTip) {
            Object[] objeto = new Object[2];
            objeto[0] = tip.getId();
            objeto[1] = tip.getTipo();
            tbTip.addRow(objeto);
        }
    }

    private void cargarCom() throws SQLException {
        tbCom.setRowCount(0); //Vaciamos la tabla
        ArrayList<Componentes> listaCom = consulCom.todosCom();
        vistaPrincipal.jTableCom.setModel(tbCom);
        for (Componentes com : listaCom) {
            Object[] objeto = new Object[4];
            objeto[0] = com.getId();
            objeto[1] = com.getNombre();
            objeto[2] = com.getDescripcion();
            objeto[3] = com.getPrecio();
            tbCom.addRow(objeto);
        }
    }

    private void cargarOrd() throws SQLException {
        tbOrd.setRowCount(0); //Vaciamos la tabla
        ArrayList<Ordenadores> listaOrd = consulOrd.todosOrd();
        vistaPrincipal.jTableOrd.setModel(tbOrd);
        for (Ordenadores ord : listaOrd) {
            Object[] objeto = new Object[4];
            objeto[0] = ord.getId();
            objeto[1] = ord.getModelo();
            objeto[2] = ord.getPrecio();
            objeto[3] = ord.getFecha_montaje();
            tbOrd.addRow(objeto);
        }
    }

    private void cargarMon() throws SQLException {
        tbMon.setRowCount(0);//Vaciamos la tabla
        ArrayList<Montajes> listaMon = consulMon.todosMon();
        vistaPrincipal.jTableMon.setModel(tbMon);
        for (Montajes mon : listaMon) {
            Object[] objeto = new Object[3];
            objeto[0] = mon.getId_ordenador();
            objeto[1] = mon.getId_componente();
            objeto[2] = mon.getId_operario();
            tbMon.addRow(objeto);
        }
    }

    private void cargarRep() throws SQLException {
        tbRep.setRowCount(0);//Vaciamos la tabla
        ArrayList<Reparaciones> listaRep = consulRep.todosRep();
        vistaPrincipal.jTableRep.setModel(tbRep);
        for (Reparaciones rep : listaRep) {
            Object[] objeto = new Object[3];
            objeto[0] = rep.getId_ordenador();
            objeto[1] = rep.getId_componente();
            objeto[2] = rep.getId_operario();
            tbRep.addRow(objeto);
        }
    }

    private void cargarElementoTablaOper(int fila) throws SQLException {
        int id = (int) vistaPrincipal.jTableOper.getValueAt(fila, 0);
        cargarBuscarOpe(id);
    }

    private void cargarElementoTablaCli(int fila) throws SQLException {
        String dni = (String) vistaPrincipal.jTableCli.getValueAt(fila, 0);
        cargarBuscarCli(dni);
    }

    private void cargarElementoTablaPro(int fila) throws SQLException {
        String cif = (String) vistaPrincipal.jTablePro.getValueAt(fila, 0);
        cargarBuscarPro(cif);
    }

    private void cargarElementoTablaTip(int fila) throws SQLException {
        int id = (int) vistaPrincipal.jTableTipos.getValueAt(fila, 0);
        cargarBuscarTip(id);
    }

    private void cargarElementoTablaCom(int fila) throws SQLException {
        int id = (int) vistaPrincipal.jTableCom.getValueAt(fila, 0);
        cargarBuscarCom(id);
    }

    private void cargarElementoTableOrd(int fila) throws SQLException {
        int id = (int) vistaPrincipal.jTableOrd.getValueAt(fila, 0);
        cargarBuscarOrd(id);
    }

    private void cargarElementoTableMon(int fila) throws SQLException {
        int id = (int) vistaPrincipal.jTableMon.getValueAt(fila, 0);
        cargarBuscarMon(id);
    }

    private void cargarElementoTableRep(int fila) throws SQLException {
        int id = (int) vistaPrincipal.jTableRep.getValueAt(fila, 0);
        cargarBuscarRep(id);
    }

    private void cargarBuscarOpe(int id) throws SQLException {
        ArrayList<Operarios> listaOpe = consulOper.buscaOpe(id);
        for (Operarios oper : listaOpe) {
            vistaPrincipal.txtIdOper.setText(String.valueOf(oper.getId()));
            vistaPrincipal.txtNombreOper.setText(oper.getNombre());
            vistaPrincipal.txtApellidosOper.setText(oper.getApellidos());
            cambiaTamaImagenOper("src/imagenes/oper/" + oper.getImagen_operario() + ".png", vistaPrincipal.lblImagenOper.getWidth(),
                    vistaPrincipal.lblImagenOper.getHeight());
        }
    }

    private void cargarBuscarCli(String dni) throws SQLException {
        ArrayList<Clientes> listaCli = consulCli.buscaCli(dni);
        for (Clientes cli : listaCli) {
            vistaPrincipal.txtDniCli.setText(cli.getDni());
            vistaPrincipal.txtNombreCli.setText(cli.getNombre());
            vistaPrincipal.txtApellidosCli.setText(cli.getApellidos());
            vistaPrincipal.txtDireccCli.setText(cli.getDireccion());
            vistaPrincipal.txtCodPostCli.setText(String.valueOf(cli.getCodigo_postal()));
            vistaPrincipal.txtPoblaCli.setText(cli.getPoblacion());
            vistaPrincipal.txtProvCli.setText(cli.getProvincia());
            vistaPrincipal.txtTelCli.setText(cli.getTelefono());
            cambiaTamaImagenCli("src/imagenes/cli/" + cli.getImagen_cliente() + ".png", vistaPrincipal.lblImagenCli.getWidth(),
                    vistaPrincipal.lblImagenCli.getHeight());
        }
    }

    private void cargarBuscarPro(String cif) throws SQLException {
        ArrayList<Proveedores> listaPro = consulPro.buscaPro(cif);
        for (Proveedores pro : listaPro) {
            vistaPrincipal.txtCifPro.setText(pro.getCif());
            vistaPrincipal.txtNombrePro.setText(pro.getNombre());
            vistaPrincipal.txtDireccPro.setText(pro.getDireccion());
            vistaPrincipal.txtCodPostPro.setText(String.valueOf(pro.getCodigo_postal()));
            vistaPrincipal.txtPoblaPro.setText(pro.getPoblacion());
            vistaPrincipal.txtProvPro.setText(pro.getProvincia());
            vistaPrincipal.txtTelPro.setText(pro.getTelefono());
            cambiaTamaImagenPro("src/imagenes/pro/" + pro.getImagen_proveedor() + ".png", vistaPrincipal.lblImagenPro.getWidth(),
                    vistaPrincipal.lblImagenPro.getHeight());
        }
    }

    private void cargarBuscarTip(int id) throws SQLException {
        ArrayList<Tipos> listaTip = consulTip.buscaTip(id);
        for (Tipos tip : listaTip) {
            vistaPrincipal.txtIdTipo.setText(String.valueOf(tip.getId()));
            vistaPrincipal.txtTipoCom.setText(tip.getTipo());
        }
    }

    private void cargarBuscarCom(int id) throws SQLException {
        ArrayList<Componentes> listaCom = consulCom.buscaCom(id);
        for (Componentes com : listaCom) {
            vistaPrincipal.txtIdCom.setText(String.valueOf(com.getId()));
            vistaPrincipal.txtNombreCom.setText(com.getNombre());
            vistaPrincipal.txtMarcaCom.setText(com.getMarca());
            vistaPrincipal.txtDescriCom.setText(com.getDescripcion());
            vistaPrincipal.txtPrecioCom.setText(String.valueOf(com.getPrecio()));
            vistaPrincipal.txtCifProCom.setText(com.getCif_proveedor());
            vistaPrincipal.txtIdTipoCom.setText(String.valueOf(com.getId_tipo()));
            cambiaTamaImagenCom("src/imagenes/com/" + com.getImagen_componente() + ".png", vistaPrincipal.lblImagenCom.getWidth(),
                    vistaPrincipal.lblImagenCom.getHeight());
            vistaPrincipal.txtEstadoCom.setText(String.valueOf(com.isEstado()));
            String estado = vistaPrincipal.txtEstadoCom.getText();
            if (estado.contains("false")) {
                vistaPrincipal.txtEstadoCom.setText("0");
                vistaPrincipal.txtEstadoCom2.setText("Sin Stock");
            } else {
                vistaPrincipal.txtEstadoCom.setText("1");
                vistaPrincipal.txtEstadoCom2.setText("En Stock");
            }
        }
    }

    private void cargarBuscarOrd(int id) throws SQLException {
        ArrayList<Ordenadores> listaOrd = consulOrd.buscaOrd(id);
        for (Ordenadores ord : listaOrd) {
            vistaPrincipal.txtIdOrd.setText(String.valueOf(ord.getId()));
            vistaPrincipal.txtModeloOrd.setText(ord.getModelo());
            vistaPrincipal.txtPrecioOrd.setText(String.valueOf(ord.getPrecio()));
            vistaPrincipal.cbDniCli.setSelectedItem(ord.getDni_cliente());
            vistaPrincipal.fechaMont.setDate(ord.getFecha_montaje());
        }
    }

    private void cargarBuscarMon(int id) throws SQLException {
        ArrayList<Montajes> listaMon = consulMon.buscaMon(id);
        for (Montajes mon : listaMon) {
            vistaPrincipal.txtMonIdOrd.setText(String.valueOf(mon.getId_ordenador()));
            vistaPrincipal.txtMonIdCom.setText(String.valueOf(mon.getId_componente()));
            vistaPrincipal.txtMonIdOper.setText(String.valueOf(mon.getId_operario()));
        }
        ArrayList<Ordenadores> listaOrd = consulOrd.buscaOrd(id);
        for (Ordenadores ord : listaOrd) {
            vistaPrincipal.txtDniCliMon.setText(ord.getDni_cliente());

            String dni = vistaPrincipal.txtDniCliMon.getText();
            ArrayList<Clientes> listaCli = consulCli.buscaCli(dni);
            for (Clientes cli : listaCli) {
                vistaPrincipal.txtNomCliMon.setText(cli.getNombre());
            }
        }

    }

    private void cargarBuscarRep(int id) throws SQLException {
        ArrayList<Reparaciones> listaRep = consulRep.buscaRep(id);
        for (Reparaciones rep : listaRep) {
            vistaPrincipal.txtRepIdOrd.setText(String.valueOf(rep.getId_ordenador()));
            vistaPrincipal.txtRepIdCom.setText(String.valueOf(rep.getId_componente()));
            vistaPrincipal.txtRepIdOper.setText(String.valueOf(rep.getId_operario()));
            vistaPrincipal.fechaRep.setDate(rep.getFecha_reparacion());
            vistaPrincipal.txtPrecioRep.setText(String.valueOf(rep.getPrecio()));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int filaOper, filaCli, filaPro, filaTip, filaCom, filaOrd, filaMon,
                filaRep, id1, id2, id3, id4, id5, id6;
        String dni, cif;
        if (e.getSource() == vistaPrincipal.jTableOper) {
            filaOper = vistaPrincipal.jTableOper.getSelectedRow();
            id1 = Integer.parseInt(vistaPrincipal.jTableOper.getValueAt(filaOper, 0).toString());
            try {
                cargarBuscarOpe(id1);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == vistaPrincipal.jTableCli) {
            filaCli = vistaPrincipal.jTableCli.getSelectedRow();
            dni = vistaPrincipal.jTableCli.getValueAt(filaCli, 0).toString();
            try {
                cargarBuscarCli(dni);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == vistaPrincipal.jTablePro) {
            filaPro = vistaPrincipal.jTablePro.getSelectedRow();
            cif = vistaPrincipal.jTablePro.getValueAt(filaPro, 0).toString();
            try {
                cargarBuscarPro(cif);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == vistaPrincipal.jTableTipos) {
            filaTip = vistaPrincipal.jTableTipos.getSelectedRow();
            id2 = Integer.parseInt(vistaPrincipal.jTableTipos.getValueAt(filaTip, 0).toString());
            try {
                cargarBuscarTip(id2);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == vistaPrincipal.jTableCom) {
            filaCom = vistaPrincipal.jTableCom.getSelectedRow();
            id3 = Integer.parseInt(vistaPrincipal.jTableCom.getValueAt(filaCom, 0).toString());
            try {
                cargarBuscarCom(id3);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == vistaPrincipal.jTableOrd) {
            filaOrd = vistaPrincipal.jTableOrd.getSelectedRow();
            id4 = Integer.parseInt(vistaPrincipal.jTableOrd.getValueAt(filaOrd, 0).toString());
            try {
                cargarBuscarOrd(id4);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == vistaPrincipal.jTableMon) {
            filaMon = vistaPrincipal.jTableMon.getSelectedRow();
            id5 = Integer.parseInt(vistaPrincipal.jTableMon.getValueAt(filaMon, 0).toString());
            try {
                cargarBuscarMon(id5);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == vistaPrincipal.jTableRep) {
            filaRep = vistaPrincipal.jTableRep.getSelectedRow();
            id6 = Integer.parseInt(vistaPrincipal.jTableRep.getValueAt(filaRep, 0).toString());
            try {
                cargarBuscarRep(id6);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void altaOper() throws SQLException {
        if (vistaPrincipal.txtNombreOper.getText().isEmpty()
                || vistaPrincipal.txtApellidosOper.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alguno de los campos está vacío", "vacio", JOptionPane.ERROR_MESSAGE);
        } else {
            int id_oper = Integer.parseInt(vistaPrincipal.txtIdOper.getText());
            String nom_oper = vistaPrincipal.txtNombreOper.getText();
            String ape_oper = vistaPrincipal.txtApellidosOper.getText();
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulOper.altas(id_oper, nom_oper, ape_oper);
            JOptionPane.showMessageDialog(null, "El alta del operario con la Id: " + vistaPrincipal.txtIdOper.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarOper();//Cargo la pantalla de nuevo con los operarios
            //Pongo en blanco todos los campos
            vistaPrincipal.txtIdOper.setText(null);
            vistaPrincipal.txtNombreOper.setText(null);
            vistaPrincipal.txtApellidosOper.setText(null);
        }
    }

    private void altaCli() throws SQLException {
        if (vistaPrincipal.txtDniCli.getText().isEmpty()
                || vistaPrincipal.txtNombreCli.getText().isEmpty()
                || vistaPrincipal.txtApellidosCli.getText().isEmpty()
                || vistaPrincipal.txtDireccCli.getText().isEmpty()
                || vistaPrincipal.txtCodPostCli.getText().isEmpty()
                || vistaPrincipal.txtPoblaCli.getText().isEmpty()
                || vistaPrincipal.txtProvCli.getText().isEmpty()
                || vistaPrincipal.txtTelCli.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alguno de los campos está vacío", "vacio", JOptionPane.ERROR_MESSAGE);
        } else {
            String dni = vistaPrincipal.txtDniCli.getText();
            String nom_cli = vistaPrincipal.txtNombreCli.getText();
            String ape_cli = vistaPrincipal.txtApellidosCli.getText();
            String dir_cli = vistaPrincipal.txtDireccCli.getText();
            int codpos_cli = Integer.parseInt(vistaPrincipal.txtCodPostCli.getText());
            String pob_cli = vistaPrincipal.txtPoblaCli.getText();
            String pro_cli = vistaPrincipal.txtProvCli.getText();
            String tel_cli = vistaPrincipal.txtTelCli.getText();
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulCli.altas(dni, nom_cli, ape_cli, dir_cli, codpos_cli, pob_cli, pro_cli, tel_cli);
            JOptionPane.showMessageDialog(null, "El alta del cliente con el DNI: " + vistaPrincipal.txtDniCli.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarCli();
            //Pongo en blanco todos los campos
            vistaPrincipal.txtDniCli.setText(null);
            vistaPrincipal.txtNombreCli.setText(null);
            vistaPrincipal.txtApellidosCli.setText(null);
            vistaPrincipal.txtDireccCli.setText(null);
            vistaPrincipal.txtCodPostCli.setText(null);
            vistaPrincipal.txtPoblaCli.setText(null);
            vistaPrincipal.txtProvCli.setText(null);
            vistaPrincipal.txtTelCli.setText(null);
        }
    }

    private void altaPro() throws SQLException {
        if (vistaPrincipal.txtCifPro.getText().isEmpty()
                || vistaPrincipal.txtNombrePro.getText().isEmpty()
                || vistaPrincipal.txtDireccPro.getText().isEmpty()
                || vistaPrincipal.txtCodPostPro.getText().isEmpty()
                || vistaPrincipal.txtPoblaPro.getText().isEmpty()
                || vistaPrincipal.txtProvPro.getText().isEmpty()
                || vistaPrincipal.txtTelPro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alguno de los campos está vacío", "vacio", JOptionPane.ERROR_MESSAGE);
        } else {
            String cif = vistaPrincipal.txtCifPro.getText();
            String nom_pro = vistaPrincipal.txtNombrePro.getText();
            String dir_pro = vistaPrincipal.txtDireccPro.getText();
            int codpos_pro = Integer.parseInt(vistaPrincipal.txtCodPostPro.getText());
            String pob_pro = vistaPrincipal.txtPoblaPro.getText();
            String pro_pro = vistaPrincipal.txtProvPro.getText();
            String tel_pro = vistaPrincipal.txtTelPro.getText();
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulPro.altas(cif, nom_pro, dir_pro, codpos_pro, pob_pro, pro_pro, tel_pro);
            JOptionPane.showMessageDialog(null, "El alta del proveedor con el CIF: " + vistaPrincipal.txtCifPro.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarPro();
            //Pongo en blanco todos los campos
            vistaPrincipal.txtCifPro.setText(null);
            vistaPrincipal.txtNombrePro.setText(null);
            vistaPrincipal.txtDireccPro.setText(null);
            vistaPrincipal.txtCodPostPro.setText(null);
            vistaPrincipal.txtPoblaPro.setText(null);
            vistaPrincipal.txtProvPro.setText(null);
            vistaPrincipal.txtTelPro.setText(null);
        }
    }

    private void altaTip() throws SQLException {
        if (vistaPrincipal.txtTipoCom.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alguno de los campos está vacío", "vacio", JOptionPane.ERROR_MESSAGE);
        } else {
            int id_tipo = Integer.parseInt(vistaPrincipal.txtIdTipo.getText());
            String tipo = vistaPrincipal.txtTipoCom.getText();
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulTip.altas(id_tipo, tipo);
            JOptionPane.showMessageDialog(null, "El alta del tipo con la Id: " + vistaPrincipal.txtIdTipo.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarTip();//Cargo la pantalla de nuevo con los tipos
            //Pongo en blanco todos los campos
            vistaPrincipal.txtIdTipo.setText(null);
            vistaPrincipal.txtTipoCom.setText(null);
        }
    }

    private void altaCom() throws SQLException {
        if (vistaPrincipal.txtIdCom.getText().isEmpty()
                || vistaPrincipal.txtNombreCom.getText().isEmpty()
                || vistaPrincipal.txtMarcaCom.getText().isEmpty()
                || vistaPrincipal.txtDescriCom.getText().isEmpty()
                || vistaPrincipal.txtPrecioCom.getText().isEmpty()
                || vistaPrincipal.txtCifProCom.getText().isEmpty()
                || vistaPrincipal.txtIdTipoCom.getText().isEmpty()
                || vistaPrincipal.txtEstadoCom.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alguno de los campos está vacío", "vacio", JOptionPane.ERROR_MESSAGE);
        } else {
            int id_com = Integer.parseInt(vistaPrincipal.txtIdCom.getText());
            String nom_com = vistaPrincipal.txtNombreCom.getText();
            String mar_com = vistaPrincipal.txtMarcaCom.getText();
            String des_com = vistaPrincipal.txtDescriCom.getText();
            float pre_com = Float.parseFloat(vistaPrincipal.txtPrecioCom.getText());
            String cif_pro = vistaPrincipal.txtCifProCom.getText();
            int tip_com = Integer.parseInt(vistaPrincipal.txtIdTipoCom.getText());
            String est_com = vistaPrincipal.txtEstadoCom.getText();
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulCom.altas(id_com, nom_com, mar_com, des_com, pre_com, cif_pro, tip_com, est_com);
            JOptionPane.showMessageDialog(null, "El alta del componente con la Id: " + vistaPrincipal.txtIdCom.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarCom();//Cargo la pantalla de nuevo con los tipos
            //Pongo en blanco todos los campos
            vistaPrincipal.txtIdCom.setText(null);
            vistaPrincipal.txtNombreCom.setText(null);
            vistaPrincipal.txtMarcaCom.setText(null);
            vistaPrincipal.txtDescriCom.setText(null);
            vistaPrincipal.txtPrecioCom.setText(null);
            vistaPrincipal.txtCifProCom.setText(null);
            vistaPrincipal.txtIdTipoCom.setText(null);
            vistaPrincipal.txtEstadoCom.setText("1");
        }
    }

    private void altaOrd() throws SQLException {
        if (vistaPrincipal.txtIdOrd.getText().isEmpty()
                || vistaPrincipal.txtModeloOrd.getText().isEmpty()
                || vistaPrincipal.txtPrecioOrd.getText().isEmpty()
                || vistaPrincipal.cbDniCli.getSelectedItem() == null
                || vistaPrincipal.fechaMont.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Alguno de los campos está vacío", "vacio", JOptionPane.ERROR_MESSAGE);
        } else {
            int id_ord = Integer.parseInt(vistaPrincipal.txtIdOrd.getText());
            String modelo = vistaPrincipal.txtModeloOrd.getText();
            float precio = Float.parseFloat(vistaPrincipal.txtPrecioOrd.getText());
            String dni_cliente = (String) vistaPrincipal.cbDniCli.getSelectedItem();
            //Cojo la fecha del jFecha y se la paso como aux a jfecha
            long aux = vistaPrincipal.fechaMont.getDate().getTime();
            java.sql.Date jfecha = new java.sql.Date(aux);
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulOrd.altas(id_ord, modelo, precio, dni_cliente, jfecha);
            JOptionPane.showMessageDialog(null, "El alta del ordenador con la Id: " + vistaPrincipal.txtIdOrd.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarOrd();//Cargo la pantalla de nuevo con los tipos
            //Pongo en blanco todos los campos
            vistaPrincipal.txtIdOrd.setText(null);
            vistaPrincipal.txtModeloOrd.setText(null);
            vistaPrincipal.txtPrecioOrd.setText(null);
            vistaPrincipal.cbDniCli.setSelectedItem(null);
            vistaPrincipal.fechaMont.setDate(null);
        }
    }

    private void bajaOper() throws SQLException {
        int fila = vistaPrincipal.jTableOper.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            int id = Integer.parseInt(vistaPrincipal.jTableOper.getValueAt(fila, 0).toString());
            JOptionPane.showMessageDialog(null, "La baja del operario con Id " + vistaPrincipal.txtIdOper.getText() + " ha sido correcta", "Baja correcta", JOptionPane.INFORMATION_MESSAGE);
            consulOper.bajas(id);
            cargarOper();//Cargo la pantalla de nuevo con los operarios
        }
    }

    private void bajaCli() throws SQLException {
        int fila = vistaPrincipal.jTableCli.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            String dni = vistaPrincipal.jTableCli.getValueAt(fila, 0).toString();
            JOptionPane.showMessageDialog(null, "La baja del cliente " + vistaPrincipal.txtNombreCli.getText() + " ha sido correcta", "Baja correcta", JOptionPane.INFORMATION_MESSAGE);
            consulCli.bajas(dni);
            cargarCli();//Cargo la pantalla de nuevo con los clientes
        }
    }

    private void bajaPro() throws SQLException {
        int fila = vistaPrincipal.jTablePro.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            String cif = vistaPrincipal.jTablePro.getValueAt(fila, 0).toString();
            JOptionPane.showMessageDialog(null, "La baja del proveedor " + vistaPrincipal.txtNombrePro.getText() + " ha sido correcta", "Baja correcta", JOptionPane.INFORMATION_MESSAGE);
            consulPro.bajas(cif);
            cargarPro();//Cargo la pantalla de nuevo con los proveedores
        }
    }

    private void bajaTip() throws SQLException {
        int fila = vistaPrincipal.jTableTipos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            int id = Integer.parseInt(vistaPrincipal.jTableTipos.getValueAt(fila, 0).toString());
            JOptionPane.showMessageDialog(null, "La baja del tipo con Id " + vistaPrincipal.txtIdTipo.getText() + " ha sido correcta", "Baja correcta", JOptionPane.INFORMATION_MESSAGE);
            consulTip.bajas(id);
            cargarTip();//Cargo la pantalla de nuevo con los tipos
        }
    }

    private void bajaCom() throws SQLException {
        int fila = vistaPrincipal.jTableCom.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            int id = Integer.parseInt(vistaPrincipal.jTableCom.getValueAt(fila, 0).toString());
            JOptionPane.showMessageDialog(null, "La baja del componente con Id " + vistaPrincipal.txtIdCom.getText() + " ha sido correcta", "Baja correcta", JOptionPane.INFORMATION_MESSAGE);
            consulCom.bajas(id);
            cargarCom();//Cargo la pantalla de nuevo con los componentes
        }
    }

    private void bajaOrd() throws SQLException {
        int fila = vistaPrincipal.jTableOrd.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            int id = Integer.parseInt(vistaPrincipal.jTableOrd.getValueAt(fila, 0).toString());
            JOptionPane.showMessageDialog(null, "La baja del ordenador con Id " + vistaPrincipal.txtIdOrd.getText() + " ha sido correcta", "Baja correcta", JOptionPane.INFORMATION_MESSAGE);
            consulOrd.bajas(id);
            cargarOrd();//Cargo la pantalla de nuevo con los ordenadores
        }
    }

    private void modOper() throws SQLException {
        int fila = vistaPrincipal.jTableOper.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            int id_oper = Integer.parseInt(vistaPrincipal.txtIdOper.getText());
            String nom_oper = vistaPrincipal.txtNombreOper.getText();
            String ape_oper = vistaPrincipal.txtApellidosOper.getText();
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulOper.mod(id_oper, nom_oper, ape_oper);
            JOptionPane.showMessageDialog(null, "La modificación del operario con la Id: " + vistaPrincipal.txtIdOper.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarOper();//Cargo la pantalla de nuevo con los operarios
        }
    }

    private void modCli() throws SQLException {
        int fila = vistaPrincipal.jTableCli.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            String dni = vistaPrincipal.txtDniCli.getText();
            String nom_cli = vistaPrincipal.txtNombreCli.getText();
            String ape_cli = vistaPrincipal.txtApellidosCli.getText();
            String dir_cli = vistaPrincipal.txtDireccCli.getText();
            int codpos_cli = Integer.parseInt(vistaPrincipal.txtCodPostCli.getText());
            String pob_cli = vistaPrincipal.txtPoblaCli.getText();
            String pro_cli = vistaPrincipal.txtProvCli.getText();
            String tel_cli = vistaPrincipal.txtTelCli.getText();
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulCli.mod(dni, nom_cli, ape_cli, dir_cli, codpos_cli, pob_cli, pro_cli, tel_cli);
            JOptionPane.showMessageDialog(null, "La modificación del cliente con DNI: " + vistaPrincipal.txtDniCli.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarCli();//Cargo la pantalla de nuevo con los clientes
        }
    }

    private void modPro() throws SQLException {
        int fila = vistaPrincipal.jTablePro.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            String cif = vistaPrincipal.txtCifPro.getText();
            String nom_pro = vistaPrincipal.txtNombrePro.getText();
            String dir_pro = vistaPrincipal.txtDireccPro.getText();
            int codpos_pro = Integer.parseInt(vistaPrincipal.txtCodPostPro.getText());
            String pob_pro = vistaPrincipal.txtPoblaPro.getText();
            String pro_pro = vistaPrincipal.txtProvPro.getText();
            String tel_pro = vistaPrincipal.txtTelPro.getText();
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulPro.mod(cif, nom_pro, dir_pro, codpos_pro, pob_pro, pro_pro, tel_pro);
            JOptionPane.showMessageDialog(null, "La modificación del proveedor con el CIF: " + vistaPrincipal.txtCifPro.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarPro();//Cargo la pantalla de nuevo con los proveedores
        }
    }

    private void modTip() throws SQLException {
        int fila = vistaPrincipal.jTableTipos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            int id_tipo = Integer.parseInt(vistaPrincipal.txtIdTipo.getText());
            String tipo = vistaPrincipal.txtTipoCom.getText();
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulTip.mod(id_tipo, tipo);
            JOptionPane.showMessageDialog(null, "La modificación del tipo con la Id: " + vistaPrincipal.txtIdTipo.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarTip();//Cargo la pantalla de nuevo con los tipos
        }
    }

    private void modCom() throws SQLException {
        int fila = vistaPrincipal.jTableCom.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            int id_com = Integer.parseInt(vistaPrincipal.txtIdCom.getText());
            String nom_com = vistaPrincipal.txtNombreCom.getText();
            String mar_com = vistaPrincipal.txtMarcaCom.getText();
            String des_com = vistaPrincipal.txtDescriCom.getText();
            float pre_com = Float.parseFloat(vistaPrincipal.txtPrecioCom.getText());
            String cif_pro = vistaPrincipal.txtCifProCom.getText();
            int tip_com = Integer.parseInt(vistaPrincipal.txtIdTipoCom.getText());
            String est_com = vistaPrincipal.txtEstadoCom.getText();
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulCom.mod(id_com, nom_com, mar_com, des_com, pre_com, cif_pro, tip_com, est_com);
            JOptionPane.showMessageDialog(null, "La modificación del componente con la Id: " + vistaPrincipal.txtIdCom.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarCom();//Cargo la pantalla de nuevo con los componentes
        }
    }

    private void modOrd() throws SQLException {
        int fila = vistaPrincipal.jTableOrd.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error selección fila", JOptionPane.ERROR_MESSAGE);
        } else {
            int id_ord = Integer.parseInt(vistaPrincipal.txtIdOrd.getText());
            String modelo = vistaPrincipal.txtModeloOrd.getText();
            float precio = Float.parseFloat(vistaPrincipal.txtPrecioOrd.getText());
            String dni_cliente = (String) vistaPrincipal.cbDniCli.getSelectedItem();
            //Cojo la fecha del jFecha y se la paso como aux a jfecha
            long aux = vistaPrincipal.fechaMont.getDate().getTime();
            java.sql.Date jfecha = new java.sql.Date(aux);
            //Cojo todos los datos y los envío a consultas al método altas para insertar datos nuevos en la BD
            consulOrd.mod(id_ord, modelo, precio, dni_cliente, jfecha);
            JOptionPane.showMessageDialog(null, "La modificación del ordenador con la Id: " + vistaPrincipal.txtIdOrd.getText() + " ha sido correcta", "altacorrecta", JOptionPane.INFORMATION_MESSAGE);
            cargarOrd();//Cargo la pantalla de nuevo con los tipos
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        //Estas dos lineas son para el combo del nombre del proveedor y tipo de componente
        String nomProv = (String) vistaPrincipal.cbNomProv.getSelectedItem();
        String nomTip = (String) vistaPrincipal.cbNomTip.getSelectedItem();
        try {
            //Va a mostrar en el txt el CIF que pertenece al proveedor seleccionado
            vistaPrincipal.txtCifProCom.setText(String.valueOf(consulPro.obtenerCifProv(nomProv)));
            int[] obtenerTipCom = consulTip.obtenerTipCom(nomTip);
            //Va a mostrar en el txt el ID que pertenece al tipo seleccionado
            vistaPrincipal.txtIdTipoCom.setText(String.valueOf(obtenerTipCom[0]));
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
