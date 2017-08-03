

package ausentismo;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComponent;


public class GraphicsAdmin {
    
    
    public GraphicsAdmin(){
    
    }
    /**
     * Este metodo sirve para quitar los JInternalFrame que tenga un JDesktopPane y caragar solamente uno maximizado 
     * @param ventana Es el JInternalFrame a cargar
     * @param PANEL Es el JDesktopPane donde se cargara ventana
     */
    public static void cargarVentana(javax.swing.JInternalFrame ventana, javax.swing.JDesktopPane PANEL){
    PANEL.removeAll();
    ventana.setSize(PANEL.getSize());
    ventana.setVisible(true);
    PANEL.add(ventana);
        try {
            ventana.setMaximum(true);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        
    }
    
    
    /**
     * Sirve para ponerle una imagen de fondo a un panel de tipo PanelFondo
     * @param panel Es un JPanel que va a contener al PanelFondo 
     * @param url Es la direccion relativa de la imagen a poner de fondo
     * @param fondo Es de tipo PanelFondo y es al cual se le podrá aplicar el fondo (Este ya debe estar instanciado)
     */
    public static void fondoAPanel(javax.swing.JPanel panel, String url, PanelFondo fondo){
        fondo.setIcon(new javax.swing.ImageIcon(url));
        fondo.setSize(panel.getSize());
        panel.add(fondo);
    }
    public static void fondoAPanel(javax.swing.JPanel panel, javax.swing.ImageIcon img, PanelFondo fondo){
        fondo.setIcon(img);
        fondo.setSize(panel.getSize());
        panel.add(fondo);
        
    }
    
    public static void organizarPanel(javax.swing.JPanel panel, PanelFondo fondo){
        Component[] componentes = panel.getComponents();
        Dimension tam = panel.getSize();
        for(Component componente : componentes){
            if(componente != fondo){
                fondo.add(componente);
                panel.remove(componente);
            }
        }
        panel.setSize(tam);
        fondo.setSize(tam);
        
    }
    /**
     * Sirve para ocultar la barra de titutlo de un JInternalFrame
     * @param ventana Es el JInternalFrame a ocultarle la barra de Titulo
     */
    public static void ocultarBarraTitulo(javax.swing.JInternalFrame ventana){ 
        JComponent Barra;
        Dimension dimBarra = null;
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) ventana.getUI()).getNorthPane(); 
        dimBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        ventana.repaint(); 
    }

    

}
