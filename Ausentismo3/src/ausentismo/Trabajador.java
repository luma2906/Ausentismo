
package ausentismo;

import java.util.ArrayList;




public class Trabajador {

    private Fecha fecha_ingreso;
    private Fecha fecha_nacimiento;
    private String CC;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String Eps;
    private String Afp;
    private String Personas_a_Cargo;
    private String Estado_Civil;
    private String Tipo_Desplazamiento;
    private int Tiempo_Desplazamiento;
    private String Educacion;
    private String Cargo;
    private String Area;
    private String[] atributos;
 
    //------------CONSTRUCTOR------------------------------

    public Trabajador() {
        this.fecha_ingreso = new Fecha();
        this.fecha_nacimiento = new Fecha();
        
        //Se guardan los campos adicionales en un array de string
        //debido a que deben quedar en el mismo orden que queda en la
        //tabla
       atributos = null;
        
    }
    
    //---------------------------SETTERS--------------------------------------
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void setFecha_ingreso(Fecha fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public void setFecha_nacimiento(Fecha fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEps(String Eps) {
        this.Eps = Eps;
    }

    public void setAfp(String Afp) {
        this.Afp = Afp;
    }

    public void setPersonas_a_Cargo(String Personas_a_Cargo) {
        this.Personas_a_Cargo = Personas_a_Cargo;
    }

    public void setEstado_Civil(String Estado_Civil) {
        this.Estado_Civil = Estado_Civil;
    }

    public void setTipo_Desplazamiento(String Tipo_Desplazamiento) {
        this.Tipo_Desplazamiento = Tipo_Desplazamiento;
    }

    public void setTiempo_Desplazamiento(int Tiempo_Desplazamiento) {
        this.Tiempo_Desplazamiento = Tiempo_Desplazamiento;
    }

    public void setEducacion(String Educacion) {
        this.Educacion = Educacion;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    //--------------------------------GETTERS----------------------------------
     public String getApellidos() {
        return apellidos;
    }
    public Fecha getFecha_ingreso() {
        return fecha_ingreso;
    }

    public Fecha getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getCC() {
        return CC;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEps() {
        return Eps;
    }

    public String getAfp() {
        return Afp;
    }

    public String getPersonas_a_Cargo() {
        return Personas_a_Cargo;
    }

    public String getEstado_Civil() {
        return Estado_Civil;
    }

    public String getTipo_Desplazamiento() {
        return Tipo_Desplazamiento;
    }

    public int getTiempo_Desplazamiento() {
        return Tiempo_Desplazamiento;
    }

    public String getEducacion() {
        return Educacion;
    }

    public String getCargo() {
        return Cargo;
    }

    public String getArea() {
        return Area;
    }
    public int getEdad(){      
        return this.fecha_nacimiento.tiempoTranscurrido(Fecha.fechaActual());
    }
    

    //-------------------------------------------------------------------------
    public String[] getAtributos() {
        return atributos;
    }

    public void setAtributos(String[] atributos) {
        this.atributos = atributos;
    }
    
    //-------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Nombre: "+this.getNombre() + " CC" + this.getCC();
    }
    
    
}
