/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ausentismo;

import javax.swing.table.DefaultTableModel;

/**
 *vamos añadirla al inicio tu sabes como??
 * @author usuario
 */
public class ModeloTablaMostrarSeguimiento extends DefaultTableModel{
    private final String cabecera[] = {"FECHA DE REGISTRO","MOTIVO","ESTADO DEL CASO","CC","NOMBRE","DIAGNOSTICO","RECOMENDACIONES MÉDICAS","COMPROMISOS","RESPONSABLE","FECHA CUMPLIMIENTO DEL COMPROMISO"};
  
    public ModeloTablaMostrarSeguimiento(){
        super();
        agregarCabeceras();
      
    }
    private void agregarCabeceras(){
      for (String cabecera1 : cabecera) {
          this.addColumn(cabecera1);
      }
    }
}
