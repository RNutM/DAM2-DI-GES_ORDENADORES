package gestionordenadores.controlador;

import gestionordenadores.vista.VistaAyuda;
import gestionordenadores.vista.VistaPrincipal;
import gestionordenadores.vista.VistaUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.help.MainWindow;

/**
 *
 * @author Robert G
 */
public class ControladorAyuda implements ActionListener {

    VistaAyuda vistaAyuda;
    VistaPrincipal vistaPrincipal;

    public ControladorAyuda(VistaAyuda vistaAyuda) {
        this.vistaAyuda = vistaAyuda;
        listeners();
        sistemaAyuda();
    }

    void inicioAyuda() {
        vistaAyuda.setVisible(true);
    }

    private void listeners() {
        vistaAyuda.btnSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaAyuda.btnSalir) {
            ControladorPrincipal c = new ControladorPrincipal(
                    new VistaUsuarios(), new VistaPrincipal());
            vistaAyuda.setVisible(false);
            try {
                c.inicioPrincipal();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void sistemaAyuda() {
        try {
            //Carga el fichero de ayuda
            ClassLoader cl = MainWindow.class.getClassLoader();
            URL hsURL = HelpSet.findHelpSet(cl, "ayuda/help_set.hs");
            //HCrea el helpSet y el helpBrowser
            HelpSet helpset = new HelpSet(cl, hsURL);
            HelpBroker hb = helpset.createHelpBroker();
            //Pone ayuda al item de menu al pulsarlo
            hb.enableHelpOnButton(vistaAyuda.jmiAyuda, "principal", helpset);
            hb.enableHelpKey(vistaAyuda.getRootPane(), "principal", helpset);
            hb.enableHelpKey(vistaAyuda.btnAyudaOper, "operarios", helpset);
            hb.enableHelpKey(vistaAyuda.btnAyudaCli, "clientes", helpset);
            hb.enableHelpKey(vistaAyuda.btnAyudaPro, "proveedores", helpset);
            hb.enableHelpKey(vistaAyuda.btnAyudaTip, "tipos", helpset);
            hb.enableHelpKey(vistaAyuda.btnAyudaCom, "componentes", helpset);
            hb.enableHelpKey(vistaAyuda.btnAyudaOrd, "ordenadores", helpset);
            hb.enableHelpKey(vistaAyuda.btnAyudaMon, "montajes", helpset);
            hb.enableHelpKey(vistaAyuda.btnAyudaRep, "reparaciones", helpset);
            hb.enableHelpKey(vistaAyuda.btnAyudaInf, "informes", helpset);
        } catch (HelpSetException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
