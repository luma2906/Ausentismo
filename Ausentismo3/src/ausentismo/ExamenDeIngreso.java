

package ausentismo;


public class ExamenDeIngreso {
    private String ID_Examen;
    private String Recomendaciones_Medicas;
    private String Id_Diagnostico;
    private Fecha Fecha_Registro;
    private Fecha Fecha_Limite;
    private String Responsable;
    private String CC;

    public String getID_Examen() {
        return ID_Examen;
    }

    public void setID_Examen(String ID_Examen) {
        this.ID_Examen = ID_Examen;
    }
    
    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }
    
    

    public String getRecomendaciones_Medicas() {
        return Recomendaciones_Medicas;
    }

    public void setRecomendaciones_Medicas(String Recomendaciones_Medicas) {
        this.Recomendaciones_Medicas = Recomendaciones_Medicas;
    }

    public String getId_Diagnostico() {
        return Id_Diagnostico;
    }

    public void setId_Diagnostico(String Id_Diagnostico) {
        this.Id_Diagnostico = Id_Diagnostico;
    }

    public Fecha getFecha_Registro() {
        return Fecha_Registro;
    }

    public void setFecha_Registro(Fecha Fecha_Registro) {
        this.Fecha_Registro = Fecha_Registro;
    }

    public Fecha getFecha_Limite() {
        return Fecha_Limite;
    }

    public void setFecha_Limite(Fecha Fecha_Limite) {
        this.Fecha_Limite = Fecha_Limite;
    }

    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String Responsable) {
        this.Responsable = Responsable;
    }
    
    /*****************************************************************************************/
    /*Los métodos y atributos puestos de aqui en adelante son para facilidad
      para almacenar y mostrar pero no pertenecen a la tabla de Examenes de ingreso */
    
    private String nombre;
    private String Diagnostico;
    private String Apellido;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDiagnostico() {
        return Diagnostico;
    }

    public void setDiagnostico(String Diagnostico) {
        this.Diagnostico = Diagnostico;
    }   

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }
    
    /*****************************************************************************************/
}
