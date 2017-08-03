/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CIE10;

/**
 *
 * @author usuario
 */
public class CIE10 {
    private String Codigo;
    private String Diagnostico;
    private String Grupo;

    public CIE10(String Codigo, String Diagnostico, String Grupo) {
        this.Codigo = Codigo;
        this.Diagnostico = Diagnostico;
        this.Grupo = Grupo;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDiagnostico() {
        return Diagnostico;
    }

    public void setDiagnostico(String Diagnostico) {
        this.Diagnostico = Diagnostico;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String Grupo) {
        this.Grupo = Grupo;
    }
    
}
