/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CIE10;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import com.csvreader.CsvReader;

/**
 *
 * @author usuario
 */
public class CargarCVS {
    

      public static void main(String[] args) {
     
        try {
         
        List<CIE10> cie10 = new ArrayList<>();
         
        CsvReader cie10_import = new CsvReader("src/CIE10/cie10.csv");
        cie10_import.readHeaders();
         
        while (cie10_import.readRecord())
        {
            String codigo = cie10_import.get("CODIGO");
            String diagnostico = cie10_import.get("DIAGNOSTICO");
            String apellidos = cie10_import.get("GRUPO");
            
             
            cie10.add(new CIE10(codigo, diagnostico, apellidos));    
        }
         
        cie10_import.close();
         
        for(CIE10 c : cie10){
            System.out.println("Codigo : "+c.getCodigo()+" Diagnostico: "+c.getDiagnostico()+" Grupo: "+c.getGrupo());
        }
         
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
