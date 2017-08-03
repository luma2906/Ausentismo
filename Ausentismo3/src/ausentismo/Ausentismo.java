

package ausentismo;


import Graficos.Inicio2;



public class Ausentismo {

    public static void main(String[] args) {

       try{
            Inicio2 start = new Inicio2();
            start.setLocationRelativeTo(null);
            BaseDatos.Conexion.conectar();
            start.setVisible(true);
       
        }catch(Exception e){
            System.out.println(e);
        }
      
    }
    
}
