/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDatos;

import ausentismo.Fecha;
import ausentismo.ModeloTablaEmpleado;
import ausentismo.Trabajador;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;


public class Demografia_BD {
    /**
     * METODO USADO PARA CONSULTAR SI UNA CEDULA (TRABAJADOR) EXISTE EN LA BASE DE DATOS
     * @param cc ES LA CEDULA DEL TRABAJADOR A BUSCAR
     * @return RETORNA TRUE EN CASO DE QUE EXISTA Y RETORNA FALSE EN CASO CONTRARIO
     */
    public boolean existeTrabajador(int cc){
        String result[][];
        //Se busca la cc en la tabla Ingreso_Reingreso
        result = Conexion.consultar("Ingreso_Reingreso WHERE cc="+cc);
        if(result.length==0){ //Si no se encontro la cc en la tabla Ingreso_Reingreso
            //Se busca la cc en la tabla Retirado
            result = Conexion.consultar("Retirado WHERE cc="+cc);
            //Si encuentra la cc devuelve true si no la encuentra devuelve false
            return result.length != 0;
        }else{//En caso de que si se encuentre la cc en la tabla Ingreso_Reingreso, se devuelve true            
            return true;
        }
       
    }
    
/**
 * METODO USADO PARA COMPROBAR SI UN TRABAJADOR SE ENCUENTRA ACTUALMENTE RETIRADO
 * (Para usar este metodo se debe estar seguro de que el trabajador existe en la BD)
 * @param cc CEDULA DEL TRABAJADOR 
 * @return True en caso de que este retirado, False en caso contrario
 */
    public boolean TrabajadorRetirado(int cc){
        //Esta sentencia es para preguntar si el trabajador está activo
        String sql ="Ingreso_Reingreso WHERE Id not in (SELECT Id_Ingreso FROM Retirado) and cc="+cc;
        //Si la longitud es cero, es porque el trabajador está retirado
        return Conexion.consultar(sql).length==0;
    }
    
    public void RegistrarTrabajador(ausentismo.Trabajador trabajador){
        //Sentencia SQL para agregar los datos a la tabla trabajador
        String sqlTrabajador = "INSERT INTO Trabajador "
                + "(CC,Nombres,Apellidos,Fecha_Nacimiento,Personas_A_Cargo,Estado_Civil,Sexo,Eps,Afp,Tipo_De_Transporte,Tiempo_De_Desplazamiento,Educacion) "
                + "VALUES ("
                + "'"+trabajador.getCC()+"',"
                + "'"+trabajador.getNombre()+"',"
                + "'"+trabajador.getApellidos()+"',"
                + "'"+trabajador.getFecha_nacimiento()+"',"
                + "'"+trabajador.getPersonas_a_Cargo()+"',"
                + "'"+trabajador.getEstado_Civil()+"',"
                + "'"+trabajador.getSexo()+"',"
                + "'"+trabajador.getEps()+"',"
                + "'"+trabajador.getAfp()+"',"
                + "'"+trabajador.getTipo_Desplazamiento()+"',"
                + "'"+trabajador.getTiempo_Desplazamiento()+"',"
                + "'"+trabajador.getEducacion()+"'"
                +")";
        
        
        //SENTENCIA SQL PARA INGRESAR LOS CAMPOS ADICIONALES QUE TIENE CADA TRABAJADOR
        String sqlCamposNuevos ="INSERT INTO "
                + "Trabajador_PuedeTener_NuevoCampo (id_campo,CC,Valor_Campo,id_relacion)"
                + "VALUES(";
        for (int i = 0; i < trabajador.getAtributos().length; i++) {
           String insertSql = sqlCamposNuevos +i+1+","+trabajador.getCC()+",'"+trabajador.getAtributos()[i]+"',null)";
           //Se insertan los datos de los campos nuevos 
           Conexion.insertar(insertSql);
        }
        //Se inserta los datos en la tabla trabajador
        Conexion.insertar(sqlTrabajador);
        
        //Sentencia sql para insertar en la tabla de ingreso
        JOptionPane.showMessageDialog(null, "El area es"+trabajador.getArea());
        String sqlIngreso = "INSERT INTO Ingreso_Reingreso (id_Area,Cargo,Fecha_Ingreso,CC,Id)"
                + "VALUES("
                + trabajador.getArea()+","
                + "'"+trabajador.getCargo()+"',"
                + "'"+trabajador.getFecha_ingreso()+"',"
                + trabajador.getCC()+","
                + "null)";
        //Se ingresan los datos a la tabla de Ingreso_Reingreso
        Conexion.insertar(sqlIngreso);
        
    }
    
    //Este metodo es para hacer un update de los datos de un empleado
    
    public void reingresoTrabajador(Trabajador t){
        String sql = "UPDATE Trabajador"
                + " SET Nombres='"+t.getNombre()+"',"
                + " Apellidos='"+t.getApellidos()+"',"
                + " Personas_A_Cargo="+t.getPersonas_a_Cargo()+","
                + " Estado_Civil='"+t.getEstado_Civil()+"',"
                + " Sexo='"+t.getSexo()+"',"
                + " Eps='"+t.getEps()+"',"
                + " Afp='"+t.getAfp()+"',"
                + " Tipo_De_Transporte='"+t.getTipo_Desplazamiento()+"',"
                + " Tiempo_De_Desplazamiento="+t.getTiempo_Desplazamiento()+","
                + " Educacion='"+t.getEducacion()+"'"
                + " WHERE CC="+t.getCC();
        Conexion.insertar(sql);
        ArrayList<String[]> listaId = Conexion.Query("Select id_campo FROM Nuevo_Campo");   
        for (int i = 0; i < listaId.size(); i++) {
            String sql2 ="UPDATE Trabajador_PuedeTener_NuevoCampo SET Valor_Campo='"+t.getAtributos()[i]+" WHERE id_relacion="+listaId.get(i);
            boolean ins_ok = Conexion.insertar(sql2);  
            if(!ins_ok){ //Si no se pudieron actualizar los datos es porque antes no los tenia y hay que ponerselos por primera vez
                String sql3 ="INSERT INTO "
                + "Trabajador_PuedeTener_NuevoCampo (id_campo,CC,Valor_Campo,id_relacion)"
                + "VALUES(";
                String insertSql = sql3 +i+1+","+t.getCC()+",'"+t.getAtributos()[i]+"',null)";
                if(!Conexion.insertar(insertSql)){ //Si no se puede actualizar ni insertar los datos
                    JOptionPane.showMessageDialog(null, "Error grave al intentar ingresar algunos campos","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
           
        }
        JOptionPane.showMessageDialog(null,"El area es: "+t.getArea());
        String sqlIngreso = "INSERT INTO Ingreso_Reingreso (id_Area,Cargo,Fecha_Ingreso,CC,Id)"
                + "VALUES("
                + t.getArea()+","
                + "'"+t.getCargo()+"',"
                + "'"+t.getFecha_ingreso()+"',"
                + t.getCC()+","
                + "null)";
        //Se ingresan los datos a la tabla de Ingreso_Reingreso
        Conexion.insertar(sqlIngreso);
        
        
    }
    
    //Esta funcion se debe usar con pleno conocimiento de si el trabajador está retirado o no
    public ArrayList<Trabajador> getTrabajador(int cc,boolean retirado){
        ArrayList<Trabajador> Trabajadores = new ArrayList<>();
        String[] camposAdicionales;
        String result[][];
        if(!retirado){
            result = Conexion.consultar("Trabajador NATURAL JOIN (SELECT * FROM Ingreso_Reingreso WHERE Id not in (SELECT Id_Ingreso FROM Retirado) ) natural join Areas WHERE cc="+cc);
        }else{
            result = Conexion.relizarConsulta("select t.cc,t.Nombres,t.Apellidos,t.Fecha_Nacimiento,t.Personas_A_Cargo,t.Estado_Civil,t.Sexo,t.Eps,t.Afp,t.Tipo_de_Transporte,t.Tiempo_De_Desplazamiento,t.Educacion,ir.Id_Area,ir.Cargo,ir.Fecha_Ingreso,ir.Id,Areas.Nombre,Max(r.Fecha_Retiro) from Trabajador as t natural join Retirado as r natural join Ingreso_Reingreso as ir natural join Areas where t.cc="+cc);
        }
        
       
        
        for (int i = 0; i < result.length; i++) {
            Trabajadores.add(new Trabajador());
            Trabajadores.get(i).setCC(result[i][0]); 
            Trabajadores.get(i).setNombre(result[i][1]); 
            Trabajadores.get(i).setApellidos(result[i][2]); 
            Trabajadores.get(i).setFecha_nacimiento(new Fecha(result[i][3])); 
            Trabajadores.get(i).setPersonas_a_Cargo(result[i][4]); 
            Trabajadores.get(i).setEstado_Civil(result[i][5]); 
            Trabajadores.get(i).setSexo(result[i][6]); 
            Trabajadores.get(i).setEps(result[i][7]); 
            Trabajadores.get(i).setAfp(result[i][8]); 
            Trabajadores.get(i).setTipo_Desplazamiento(result[i][9]); 
            Trabajadores.get(i).setTiempo_Desplazamiento(Integer.parseInt(result[i][10])); 
            Trabajadores.get(i).setEducacion(result[i][11]); 
            Trabajadores.get(i).setCargo(result[i][13]); 
            Trabajadores.get(i).setArea(result[i][16]); 
            Trabajadores.get(i).setFecha_ingreso(new Fecha(result[i][14]));
            
            //Se obtienen los campos nuevos para este trabajador
            String[][] result2 = Conexion.consultar("Trabajador_PuedeTener_NuevoCampo WHERE CC="+result[i][0]+" ORDER BY id_campo");
            camposAdicionales = new String[result2.length];
            //En el siguiente ciclo se toman solo los datos correspondientes 
            //al valor del campo ya que deben quedar en orden
            for (int j = 0; j < result2.length; j++) {
               camposAdicionales[j] = result2[j][2];
              
            }
            
            Trabajadores.get(i).setAtributos(camposAdicionales);
            
        }
        return Trabajadores;
    }
    public ArrayList<Trabajador> getTrabajadores(){
        ArrayList<Trabajador> Trabajadores = new ArrayList<>();
        String[] camposAdicionales;
        
        String result[][] = Conexion.consultar("Trabajador NATURAL JOIN (SELECT * FROM Ingreso_Reingreso WHERE Id not in (SELECT Id_Ingreso FROM Retirado) ) natural join Areas");
        
        for (int i = 0; i < result.length; i++) {
            Trabajadores.add(new Trabajador());
            Trabajadores.get(i).setCC(result[i][0]); 
            Trabajadores.get(i).setNombre(result[i][1]); 
            Trabajadores.get(i).setApellidos(result[i][2]); 
            Trabajadores.get(i).setFecha_nacimiento(new Fecha(result[i][3])); 
            Trabajadores.get(i).setPersonas_a_Cargo(result[i][4]); 
            Trabajadores.get(i).setEstado_Civil(result[i][5]); 
            Trabajadores.get(i).setSexo(result[i][6]); 
            Trabajadores.get(i).setEps(result[i][7]); 
            Trabajadores.get(i).setAfp(result[i][8]); 
            Trabajadores.get(i).setTipo_Desplazamiento(result[i][9]); 
            Trabajadores.get(i).setTiempo_Desplazamiento(Integer.parseInt(result[i][10])); 
            Trabajadores.get(i).setEducacion(result[i][11]); 
            Trabajadores.get(i).setCargo(result[i][13]); 
            Trabajadores.get(i).setArea(result[i][16]); 
            Trabajadores.get(i).setFecha_ingreso(new Fecha(result[i][14]));
            
            //Se obtienen los campos nuevos para este trabajador
            String[][] result2 = Conexion.consultar("Trabajador_PuedeTener_NuevoCampo WHERE CC="+result[i][0]+" ORDER BY id_campo");
            camposAdicionales = new String[result2.length];
            //En el siguiente ciclo se toman solo los datos correspondientes 
            //al valor del campo ya que deben quedar en orden
            for (int j = 0; j < result2.length; j++) {
               camposAdicionales[j] = result2[j][2];
              
            }
            
            Trabajadores.get(i).setAtributos(camposAdicionales);
            
        }
        return Trabajadores;
    }
    
    public ModeloTablaEmpleado getTrabajador(int cc){
        String[][] result = Conexion.consultar("Trabajador NATURAL JOIN (SELECT * FROM Ingreso_Reingreso WHERE CC= "+cc+" and Id not in (SELECT Id_Ingreso FROM Retirado) ) natural join Areas");
        if(result.length==0){
            return null;
        }
        ModeloTablaEmpleado modelo = new ModeloTablaEmpleado(result[0]); 
        modelo.setArea(result[0][16]);
        modelo.setIdIngreso(result[0][15]);
        return modelo;
    }
    
    public static void nuevoCampo(String nombreCampo){
        String sql = "INSERT INTO Nuevo_Campo VALUES (null,'"+nombreCampo+"')";
        Conexion.insertar(sql);
    }
    
    public static String[] getNuevosCampos(){
        String[][] datos = Conexion.consultar("Nuevo_Campo ORDER BY id_campo");
        String[] campos = new String[datos.length];
        for (int i = 0; i < datos.length; i++) {
            campos[i] = datos[i][1];
        }
        return campos;
    }
    
    public DefaultTableModel cantidadDe(String item){
        ArrayList<String[]> datos = Conexion.cuentaCampos(item,"Trabajador",true);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn(item);
        modelo.addColumn("Cantidad");
        for (int i = 0; i < datos.size(); i++) {
            modelo.addRow(datos.get(i));
        }
        return modelo;
    }    
   
    public DefaultTableModel consultaCondicion(String item,String condicion){
        String sql = "Trabajador WHERE "+condicion;
        ArrayList<String[]> datos = Conexion.cuentaCampos(item, sql, true);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn(item);
        modelo.addColumn("Cantidad");
        for (int i = 0; i < datos.size(); i++) {
            modelo.addRow(datos.get(i));
        }
        return modelo;
    }
    
    public DefaultTableModel EstadisticaTransporte(){
        ArrayList<String[]> datos = Conexion.Transporte();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("TIPO DE TRANSPORTE");
        modelo.addColumn("TIEMPO DE DESPLAZAMIENTO");
        modelo.addColumn("CANTIDAD DE PERSONAS");
        for (int i = 0; i < datos.size(); i++) {
            modelo.addRow(datos.get(i));            
        }
        return modelo;
    }
    
    public void RetirarTrabajador(int cc,String motivo,String idIngreso){
       
        String sql ="INSERT INTO Retirado(Id_Retiro,CC,Fecha_Retiro,Motivo_Retiro,Id_Ingreso)"
                + " VALUES (null,"
                + cc+","
                + "'"+Fecha.fechaActual().toString()+"',"
                + "'"+motivo+"',"
                + idIngreso
                + ")";
        Conexion.insertar(sql);
    }
    
    public static void main(String[] args){
        
        Trabajador trabajador = new Trabajador();
        trabajador.setCC("1088300834");
        trabajador.setNombre("Alejandro");
        trabajador.setApellidos("Arroyave Perez");
        trabajador.setFecha_nacimiento(new ausentismo.Fecha());
        trabajador.setPersonas_a_Cargo("0");
        trabajador.setEstado_Civil("Soltero");
        trabajador.setSexo("M");
        trabajador.setEps("Coomeva");
        trabajador.setAfp("AFp_sita");        
        trabajador.setTipo_Desplazamiento("A Pie");
        trabajador.setTiempo_Desplazamiento(30);
        trabajador.setEducacion("Tecnico");
        trabajador.setCargo("Jefe Sistemas");
        trabajador.setArea("Sistemas");
        trabajador.setFecha_ingreso(new ausentismo.Fecha());
        Demografia_BD d = new Demografia_BD();
        Conexion.conectar();
        d.RegistrarTrabajador(trabajador);
        Conexion.CerrarConexion();
    
    }
    
    
    
}
