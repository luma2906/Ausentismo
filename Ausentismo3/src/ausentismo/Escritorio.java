/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ausentismo;


import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;


public class Escritorio extends JDesktopPane{
    
     private Image IMG=new ImageIcon(getClass().getResource("/Imagenes/fondo.gif")).getImage();
  

     @Override
        public void paintChildren(Graphics g){
            g.drawImage(IMG, 0, 0, getWidth(), getHeight(), this);
            super.paintChildren(g);
        }
        
      /**
       * Metodo para cambiar la imagen de fondo
       * @param img  es un parametro de tipo ImageIcon que contiene el fondo
       */
        public void setImage(ImageIcon img){
            IMG= img.getImage();
      }
        
      
    
}
