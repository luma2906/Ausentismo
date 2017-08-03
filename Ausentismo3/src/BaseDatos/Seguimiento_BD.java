
package BaseDatos;

import java.util.ArrayList;


public class Seguimiento_BD {
    
    
    public static ArrayList<String[]> datosTablaSeguimiento(){//RecomendacionesMedicas
        String sql = "SELECT b.id_seguimiento,a.Fecha_Registro,a.CC,b.Nombres,b.Estado,a.Diagnostico,a.Dias_Incapacidad,b.Apellidos,a.Id_Motivo " +
                     "FROM (select * from CIE10 NATURAL JOIN Accidente_Ausente) as a NATURAL JOIN (select * from Trabajador NATURAL JOIN seguimiento) as b " +
                     "ORDER BY a.Fecha_Registro";
        
        ArrayList<String[]> datos = Conexion.Query(sql);
        
        
        return datos;
    }
    
    
    
    public static String motivo(int id_motivo){
        String sql = "SELECT Nombre FROM Motivo WHERE id_motivo="+id_motivo;
        return Conexion.Query(sql).get(0)[0];
    }
    
    
    public static ArrayList<String[]> compromisos(int id_seguimiento){
        String sql = "SELECT * FROM Compromiso WHERE id_seguimiento="+id_seguimiento;
        return Conexion.Query(sql);
    }
    
    /**
     * Método para insertar un compromiso en la tabla de compromisos correspondiente a un seguimiento en especifico
     * @param Compromiso 
     * @param fecha_Registro
     * @param fecha_Limite
     * @param Id_Seguimiento
     * @param Responsable 
     * @param observacion 
     */
    public static void insertarCompromiso(String Compromiso,ausentismo.Fecha fecha_Registro,ausentismo.Fecha fecha_Limite,String Id_Seguimiento,String Responsable,String observacion){
        String sql = "INSERT INTO Compromiso "
                + "(Id_Compromiso,Id_Seguimiento,Fecha_Registro,Fecha_Limite,Compromiso,Observacion,Responsable)"
                + " VALUES("
                + "null,"
                + Id_Seguimiento+","
                + "'"+fecha_Registro.toString()+"',"
                + "'"+fecha_Limite.toString()+"',"
                + "'"+Compromiso+"',"
                + "'"+observacion+"',"
                + "'"+Responsable+"')";
        Conexion.insertar(sql);
    }
    
    
    
    //Método que retorna un array list con la informacion de la tabla del cie 10
    public ArrayList<ausentismo.CIE10> cie10(){
        String sql = "SELECT * FROM CIE10";
        ArrayList<String[]> result = Conexion.Query(sql);
        ArrayList<ausentismo.CIE10> listaCie10 = new ArrayList();
        for (int i = 0; i < result.size(); i++) {
            ausentismo.CIE10 cie = new ausentismo.CIE10();
            cie.setCodigo(result.get(i)[0]);
            cie.setNombre(result.get(i)[1]);
            cie.setGrupo(result.get(i)[2]);
            listaCie10.add(cie);
        }
        return (result.size()<=0)?null:listaCie10;
    }
    
    /**
     * Este metodo es usado para obtener el diagnostico correspondiente a un codigo en especifico
     * @param codigo del cual se quiere obtener el diagnostico
     * @return String con el diagnostico correspondiente al codigo enviado
     */
    public String getNombreCie10(String codigo){
        String sql = "SELECT Diagnostico FROM CIE10 WHERE C_Diagnostico='"+codigo+"'";
        return Conexion.Query(sql).get(0)[0];
    }
    
    /**
     * Este método es usado para insertar un examen de ingreso a la base de datos
     * @param ei Es el examen de ingreso que se desea guardar en la base de datos
     */
    public static void insertExamenesDeIngreso(ausentismo.ExamenDeIngreso ei){
        
        String sql = "INSERT INTO ExamenesIniciales"
                + "(Id_Examen,CC,RecomendacionesMedicas,Id_Diagnostico,Fecha_Registro,Fecha_Limite,Responsable)"
                + " VALUES("
                + "null,"
                + ei.getCC()+","
                + "'"+ei.getRecomendaciones_Medicas()+"',"
                + ei.getId_Diagnostico()+","
                + "'"+ei.getFecha_Registro()+"',"
                + "'"+ei.getFecha_Limite()+"',"
                + "'"+ei.getResponsable()+"'"
                + ")";
        Conexion.insertar(sql);
        
    }
    /**
     * Método para consultar a la base de datos por los examenes de ingreso
     * @return Devuelve una lista de examenes de ingreso ArrayList<ausentismo.ExamenDeIngreso> con todos los examenes existentes en la tabla
     */
    public static ArrayList<ausentismo.ExamenDeIngreso> obtenerExamenesDeIngreso(){
        String sql = "SELECT "
                + "Id_Examen,Trabajador.CC,RecomendacionesMedicas,Id_Diagnostico,"
                + "ExamenesIniciales.Fecha_Registro,Fecha_Limite,Responsable,Diagnostico,Nombres,Apellidos "
                + "FROM ExamenesIniciales NATURAL JOIN CIE10 NATURAL JOIN Trabajador "
                + "WHERE ExamenesIniciales.Id_Diagnostico = CIE10.C_Diagnostico and ExamenesIniciales.CC = Trabajador.CC";
        ArrayList<String[]> result = Conexion.Query(sql);
        ArrayList<ausentismo.ExamenDeIngreso> Examenes;
        Examenes = new ArrayList<>();
        for(String[] r : result){
            ausentismo.ExamenDeIngreso examen= new ausentismo.ExamenDeIngreso();
            examen.setID_Examen(r[0]);
            examen.setCC(r[1]);
            examen.setRecomendaciones_Medicas(r[2]);
            examen.setId_Diagnostico(r[3]);
            ausentismo.Fecha f = new ausentismo.Fecha(r[4]);
            examen.setFecha_Registro(f);
            f = new ausentismo.Fecha(r[5]);
            examen.setFecha_Limite(f);
            examen.setResponsable(r[6]);
            examen.setDiagnostico(r[7]);
            examen.setNombre(r[8]);
            examen.setApellido(r[9]);
            Examenes.add(examen);            
        }
        return Examenes;
    }
    /**
     * Método que se usa para cerrar un caso de la tabla Seguimiento es decir un caso normal no un examen de ingreso
     * @param id_Seguimiento El codigo (ID) del seguimiento el cual se quiere dar por cerrado
     */
    public static void cerrarCaso(String id_Seguimiento){
        String sql = "UPDATE Seguimiento SET Estado='CERRADO' WHERE id_seguimiento="+id_Seguimiento;
        Conexion.insertar(sql);
    }
    /**
     * Método utilizado para obtener los casos de un trabajador en especifico
     * @param cc la cedula del trabajador delcual se quieren obtener los casos
     * @return Devuelve un lista con los la informacion de los casos de ese trabajador
     */
    public static ArrayList<String[]> obtenerCasosDe(String cc){
        String sql = "SELECT b.id_seguimiento,a.Fecha_Registro,a.CC,b.Nombres,b.Estado,a.Diagnostico,a.Dias_Incapacidad,b.Apellidos,a.Id_Motivo " +
                     "FROM (select * from CIE10 NATURAL JOIN Accidente_Ausente) as a NATURAL JOIN (select * from Trabajador NATURAL JOIN seguimiento) as b " +
                     "WHERE a.CC="+cc+" ORDER BY a.Fecha_Registro";
        return Conexion.Query(sql);
    }
    /**
     * Método para consultar la base dedatos y traer los Examenes de ingreso de un trabajador en especifico
     * @param cc La cedula del trabajador del cual se quiere obtener el examen de ingreso
     * @return Devuelve una lista con los Examenes de ingreso que correspondan a la cedula pasada comoparametro
     */
    public static ArrayList<ausentismo.ExamenDeIngreso> obtenerExamenesDeIngresoDe(String cc){
        String sql = "SELECT "
                + "Id_Examen,Trabajador.CC,RecomendacionesMedicas,Id_Diagnostico,"
                + "ExamenesIniciales.Fecha_Registro,Fecha_Limite,Responsable,Diagnostico,Nombres,Apellidos "
                + "FROM ExamenesIniciales NATURAL JOIN CIE10 NATURAL JOIN Trabajador "
                + "WHERE ExamenesIniciales.Id_Diagnostico = CIE10.C_Diagnostico and ExamenesIniciales.CC = Trabajador.CC and Trabajador.CC="+cc;
        ArrayList<String[]> result = Conexion.Query(sql);
        ArrayList<ausentismo.ExamenDeIngreso> Examenes;
        Examenes = new ArrayList<>();
        for(String[] r : result){
            ausentismo.ExamenDeIngreso examen= new ausentismo.ExamenDeIngreso();
            examen.setID_Examen(r[0]);
            examen.setCC(r[1]);
            examen.setRecomendaciones_Medicas(r[2]);
            examen.setId_Diagnostico(r[3]);
            ausentismo.Fecha f = new ausentismo.Fecha(r[4]);
            examen.setFecha_Registro(f);
            f = new ausentismo.Fecha(r[5]);
            examen.setFecha_Limite(f);
            examen.setResponsable(r[6]);
            examen.setDiagnostico(r[7]);
            examen.setNombre(r[8]);
            examen.setApellido(r[9]);
            Examenes.add(examen);            
        }
        return Examenes;
    }
    
    /**
     * Método para obtener los compromisos de un Examen de Ingreso en especifico 
     * @param id_EI El identificador del examene de ingreso
     */
    public static ArrayList<String[]> compromisosEI(String id_EI){
        String sql="SELECT * FROM Compromisos_ExamenesIngreso WHERE Id_ExamenIngreso="+id_EI;
        return Conexion.Query(sql);
    }
    /**
     * Método para insertar un compromiso correspondiente a un caso de un examen de ingreso
     * @param ID_EI EL ID del examen de ingreso al cual se le quiere poner el compromiso
     * @param FR La fecha de registro
     * @param FL LA fecha limite
     * @param comp Compromiso
     * @param obs Observacion
     * @param resp Responsable
     */
    public static void insertarCompromisoEI(String ID_EI,ausentismo.Fecha FR,ausentismo.Fecha FL,String comp,String obs,String resp){
        String sql = "INSERT INTO Compromisos_ExamenesIngreso"
                + "(ID,Id_ExamenIngreso,Fecha_Registro,Fecha_Limite,Compromiso,Observacion,Responsable) "
                + "VALUES("
                + "null,"
                + "'"+ID_EI+"',"
                + "'"+FR.toString()+"',"
                + "'"+FL.toString()+"',"
                + "'"+comp+"',"
                + "'"+obs+"',"
                + "'"+resp+"'"
                + ")";
        Conexion.insertar(sql);
    }
    
    public static void main(String args[]){
      BaseDatos.Conexion.conectar();
      ausentismo.ExamenDeIngreso ei = new ausentismo.ExamenDeIngreso();
      ei.setCC("87654");
      ei.setFecha_Registro(new ausentismo.Fecha("2014-01-01"));
      ei.setFecha_Limite(new ausentismo.Fecha("2014-03-01"));
      ei.setId_Diagnostico("1010");
      ei.setRecomendaciones_Medicas("Alguna recomendacion");
      ei.setResponsable("Pepito");
      insertExamenesDeIngreso(ei);
      
    }
}
