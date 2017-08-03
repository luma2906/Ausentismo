/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ausentismo;

import Graficos.Ausentismo_Accidentes21;


/**
 *
 * @author mi-VAIO
 */
public class ValidarDatos {
    
    public ValidarDatos(){
    
    }
    public boolean Vnumero(String cedula){
       
         
        int detecta[]=null;
        char numeros[] = null;
       
        System.out.println("dkgkghjhasdg"+cedula);
        numeros=new char[cedula.length()];
        detecta=new int [cedula.length()];       
        for (int i = 0; i < cedula.length(); i++) {
            numeros[i]=cedula.charAt(i); 
            
             if(isnumeros(numeros[i])==true){
                 detecta[i]=0;
              }else{
                 detecta[i]=1;
             }
        }
        for (int i = 0; i < detecta.length; i++) {
            if(detecta[i]==1){ 
                
                return false;
                
            }
            
        }
        return true;
    }
    public boolean VcampoVacio(String valor){
        System.out.println("valor"+valor);
        if( (valor != null) && (!valor.equals("")) ){
            System.out.println("dentraaaa");
             return true;
            
        }else{
           return false;
        }
    }
    
     //MIS FUNCIONES
    private boolean isnumeros(char numero){       
        switch(numero){
            case '0':
                return true;
            case '1':
                return true;
            case '2':
                return true;
            case '3':
                return true;
            case '4':
                return true;
           case '5':
                return true;
           case '6':
                return true;
           case '7':
                return true;
           case '8':
                return true;
           case '9':
                return true;
           default:
               return false;
        }     
    }

//FUNCIONES PARA VALIDAR EN EL INGRESO DEL TRABAJADOR
    /**
     * METODO PARA COMPROBAR SI UN TRABAJADOR EXISTE EN LA BASE DE DATOS Y CUAL ES SU ESTADO
     * @param cc La cedula del trabador a comprobar
     * @return retorna null en caso de que el trabajador no exista en la base de datos, de lo contrario Object[0] esta el trabajador y Object[1] esta el estado si esta activo o retirado
     */
    public Object[] ComprobarTrabajador(int cc){
        BaseDatos.Demografia_BD bdDemografia = new BaseDatos.Demografia_BD();
        Object[] objetos = new Object[2];
        Trabajador tr;
        if(bdDemografia.existeTrabajador(cc)){//Se comprueba que exista un trabajador con la cc indicada       
           if(bdDemografia.TrabajadorRetirado(cc)){//Se pregunta si el trabajador está retirado
               tr = bdDemografia.getTrabajador(cc,true).get(0); //Debido a que el trabajador está retirado se le dice que me obtenga el trabajador de los retirados
               String tipo = "Retirado";
               objetos[0] = (Object) tr;
               objetos[1] = (Object) tipo;
               return objetos;
           } else { //Como el trabajador no está retirado significa que está activo
               tr = bdDemografia.getTrabajador(cc,false).get(0);//Se le dice que obtenga el trabajador de los trabajadores activos
               String tipo = "Activo";
               objetos[0] = (Object) tr;
               objetos[1] = (Object) tipo;
               return objetos;
           }
        }else{//Si se llega a este punto es porque el trabajador con el numero de cc indicado no existe en la BD
            return null;
        }
    }
   
    
    
}
