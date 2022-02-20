package gestionordenadores.vista;


import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tarde
 */
public class mycustombutton extends JButton implements Serializable{
   
    public mycustombutton() {
    }
       
      @Override
    public void setForeground(Color fg) {
        super.setForeground(new Color(151,219,248)); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setBackground(Color bg) {
        super.setBackground(new Color(0,64,92)); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isOpaque() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setFont(Font font) {
        super.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18)); //To change body of generated methods, choose Tools | Templates.
    } 
}
