

package ausentismo;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaEmpleado extends DefaultTableModel{
  private String cabecera[] = {"C.C","NOMBRE","APELLIDOS","FECHA DE NACIMIENTO","PERSONAR A CARGO","ESTADO CIVIL","SEXO","EPS","AFP","TIPO DE TRANSPORTE","TIEMPO DE DESPLAZAMIENTO","EDUCACIÓN","CARGO","AREA","FECHA INGRESO"};
  private String area ="";
  private String idIngreso="";
    @Override
    public boolean isCellEditable(int i, int i1) {
        return (i1==0);
    }
  
    
    public ModeloTablaEmpleado(){
        super();
        agregarCabeceras();
        inicializarCampos();
        agregarCamposAdicionales();
        
      
    }
    public ModeloTablaEmpleado(String[] datos){
        super();
        agregarCabeceras();
        this.addRow(datos);
        inicializarCampos();
        agregarCamposAdicionales();
    }

    public String getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(String idIngreso) {
        this.idIngreso = idIngreso;
    }
    
    
    private void agregarCabeceras(){
      for (String cabecera1 : cabecera) {
          this.addColumn(cabecera1);
      }
    }
    private void inicializarCampos(){
        
        Object[] iniciar = {"","","","",""};
        for (int i = 0; i < 70; i++) {
            this.addRow(iniciar);
        }
        
    }
    public void setArea(String area){
        this.area = area;
    }
    public String getArea(){
        return this.area;
    }
    private void agregarCamposAdicionales(){
        String[] campos = BaseDatos.Demografia_BD.getNuevosCampos();
        for(String c : campos){
            this.addColumn(c);
        }
    }
    
}
