/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graficos;

/**
 *
 * @author usuario
 */
public class validaciones {
    
    public static boolean isInt(String num){
        try{
            Integer.parseInt(num);
            return true;
        }catch(Exception e){
            return false;
        }
        
    }
    
    
    
}
