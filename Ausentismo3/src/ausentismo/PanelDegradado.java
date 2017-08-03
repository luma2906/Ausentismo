/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ausentismo;


import java.awt.Color;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class PanelDegradado extends JPanel {
    private Color superior;
    private Color inferior;
    public PanelDegradado(){
     super();
     this.superior = Color.WHITE;
     this.inferior = Color.BLUE;
     
    }

    @Override
    public void paintComponent(Graphics g){
     super.paintComponents(g);
     Graphics2D g2d=(Graphics2D)g;
     g2d.setPaint(new GradientPaint(40,200, getColorInferior(), 50, 0, getColorSuperior()));
     
     g2d.fillRect(0, 0,100, 100);
    }

    public void setColorSuperior(Color c){
        this.superior = c;
    }
    public void setColorInferior(Color c){
        this.inferior = c;
    }
    public Color getColorSuperior(){
        return this.superior;
    }
    public Color getColorInferior(){
        return this.inferior;
    }
 
}
