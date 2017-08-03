package BaseDatos;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

 
public class Conexion {
    private static  Connection conexion;   
        public static void conectar(){
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
             String dburl = "jdbc:sqlite:Ausentismo.db";
            try {
               conexion = DriverManager.getConnection(dburl);
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"NO SE PUDO REALIZAR LA CONECCION CON LA BASE DE DATOS");
            }
           
        }
        public static boolean insertar(String sentencia) {                
        Statement Consulta = null;
        try {
            Consulta = conexion.createStatement();
            Consulta.executeUpdate(sentencia);
            Consulta.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("____________________________________");
            System.out.println("ERROR SQL EN LA FUNCION INSERTAR");
            System.out.println(ex);
            System.out.println("_____________________________________");
            return false;
        }catch(Exception e){
            System.out.println("ERROR GENERAL EN LA FUNCION INSERTAR");
            return false;
        }

        }
        public static String[][] consultar(String Nombre_Tabla) {
            ResultSet rs = null;
            Statement stmt = null;
            Statement Consulta = null;
            ResultSet cuenta = null;
            int campos = 0;
            int j =0;
            String Tabla[][]=null;
            try {
                stmt = conexion.createStatement();
                Consulta = conexion.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }        
            try {
                rs = Consulta.executeQuery("SELECT * FROM "+Nombre_Tabla);
                cuenta = stmt.executeQuery("SELECT COUNT(*) FROM "+Nombre_Tabla);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"NO SE PUDO REALIZAR LA CONSULTA " + ex);            
            }
            try {
                campos = rs.getMetaData().getColumnCount(); 
                Tabla = new String[cuenta.getInt(1)][campos];
                while(rs.next()){
                    for (int i = 0; i < campos; i++) {
                       
                       Tabla[j][i] = rs.getString(i+1);

                    }
                    j++;
                }
                rs.close();

            } catch (SQLException ex) {
                System.out.println("error "+ex);
            }
           
            return Tabla;
        }
        
        
        public static ArrayList<String[]> cuentaCampos(String campo,String tabla,boolean group){
            ResultSet rs = null;
            Statement Consulta = null;
            ArrayList<String[]> datos = new ArrayList<>();
            try {            
                Consulta = conexion.createStatement();
                String sql = "SELECT "+campo+",COUNT(*) FROM "+tabla;
                if(group){
                  sql = sql+" GROUP BY "+campo;
                }
                
                rs = Consulta.executeQuery(sql);
                
                
                while(rs.next()){
                    String[] fila = new String[2];
                    fila[0]=rs.getString(1);
                    fila[1]=rs.getString(2);
                    datos.add(fila);
                }
  
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al consultar la base de datos.\n"+ex);
            }
            
            return datos;
        }
        
        public static ArrayList<String[]>  Transporte (){
            ResultSet rs = null;
            Statement Consulta = null;
            ArrayList<String[]> datos = new ArrayList<>();
            try {            
                Consulta = conexion.createStatement();
                String sql = "SELECT Tipo_De_Transporte,Tiempo_De_Desplazamiento,COUNT(*) FROM Trabajador GROUP BY Tipo_De_Transporte,Tiempo_De_Desplazamiento";
                
                //JOptionPane.showMessageDialog(null, "SQL \n"+sql);
                rs = Consulta.executeQuery(sql);
                
                
                while(rs.next()){
                    String[] fila = new String[3];
                    fila[0]=rs.getString(1);
                    fila[1]=rs.getString(2);
                    fila[2]=rs.getString(3);
                    datos.add(fila);
                }
  
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al consultar la base de datos.\n"+ex);
            }
            
            return datos;
        }
        public static ArrayList<String[]> Query(String query){
            ResultSet rs = null;
            Statement Consulta = null;
            ArrayList<String[]> datos = new ArrayList<>();
            int columnas =0;
            try {            
                Consulta = conexion.createStatement();
                rs = Consulta.executeQuery(query);
                
                
                columnas = rs.getMetaData().getColumnCount();
                while(rs.next()){
                    String[] fila = new String[columnas];                    
                    for (int i = 0; i < columnas; i++) {
                        fila[i] = rs.getString(i+1);
                        //AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
                        System.out.print(rs.getMetaData().getColumnLabel(i+1));
                        
                        System.out.print(" | ");
                        
                    }
                  System.out.println("--------------------------------------");
                    datos.add(fila);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al consultar la base de datos.\n"+ex,"Error!!!",JOptionPane.ERROR_MESSAGE);
            }
            return datos;
        }
        
        public static String[][] relizarConsulta (String Sentencia){
            ArrayList<String[]> datos = Query(Sentencia);
            String[][] tabla = new String[datos.size()][datos.get(0).length];
            for (int i = 0; i < tabla.length; i++) {
                for (int j = 0; j < tabla[i].length; j++) {
                    tabla[i][j] = datos.get(i)[j];
                    
                }
                
            }
            return tabla;
        }
        
        public static void CerrarConexion(){
            try {
                conexion.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se pudo cerrar la conexión con la Base de Datos");
            }
        }
         
	 public static void main(String[] args)throws Exception {
             ArrayList<String[]> matrix;
             String [][] tabla = null;
             String sql = "Trabajador NATURAL JOIN (SELECT * FROM Ingreso_Reingreso WHERE CC=123 and Id not in (SELECT Id_Ingreso FROM Retirado) ) natural join Areas";
             conectar();
             //insertar("insert INTO Observaciones VALUES (null,1,'2014-05-23','Esta es una observacion importante')");
             
            // matrix=Query(sql);
             tabla = consultar(sql);
        /*for (String[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                System.out.print(matrix1[j] + " | ");
            }
            System.out.println(" ");
        }*/
             for (int i = 0; i < tabla.length; i++) {
                 for (int j = 0; j < tabla[0].length; j++) {
                     System.out.print(tabla[i][j] + " | ");
                     
                 }
                 System.out.println("");
             }
             CerrarConexion();
        }
}