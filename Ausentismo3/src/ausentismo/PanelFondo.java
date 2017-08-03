/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ausentismo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;


public class PanelFondo extends javax.swing.JPanel{
    private Image fondo;
    private ImageIcon icono;
    public PanelFondo(){
        super();
        this.setSize(100, 100);
    }
    
    protected void paintComponent(Graphics g){
        if(fondo!=null){
            Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(fondo, 0, 0,this.getWidth(),this.getHeight(),null);
            
        }
    }
    
    public ImageIcon getIcon(){
        return icono;
    }
    
    public void setIcon(ImageIcon icono){
        this.icono = icono;
        if(icono!=null){
            fondo = icono.getImage();
        }
    }
 
}
