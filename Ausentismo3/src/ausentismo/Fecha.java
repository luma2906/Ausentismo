

package ausentismo;

import java.util.Calendar;
import java.util.StringTokenizer;


public class Fecha {
    
    private int day;
    private int month;
    private int year;

    public Fecha(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public Fecha(String fecha){
        StringTokenizer tokens = new StringTokenizer(fecha,"-");
        this.year = Integer.parseInt(tokens.nextToken());
        this.month = Integer.parseInt(tokens.nextToken());
        this.day = Integer.parseInt(tokens.nextToken());
    }
    public Fecha(){
        this.day = 1;
        this.month = 1;
        this.year = 1913;
    }
    
    public String toSqLiteDate(){
        String fecha = this.year+"-"+this.month+"-"+this.day;
        return fecha;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
    /** 
     * Funcion para calcular los años transcurridos entre esta fecha y la fecha f
     * @param f esta fecha debe ser mayor.
     */
    public int tiempoTranscurrido(Fecha f){
        int dif = 0;
        if(f.getMonth() < this.month){
            dif = f.getYear() - this.year -1;
        }
        else if(f.getMonth() == this.month){
            if(f.getDay() < this.day){
                dif = f.getYear() - this.year -1;
            }
            else{
                dif = f.getYear() - this.year;
            }
         
        }
        else{
            dif = f.getYear() - this.year;
        }
        return dif;
    }
    public static Fecha fechaActual(){
        java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int anio = cal.get(Calendar.YEAR);
        Fecha actual = new Fecha(dia,mes,anio);
        return actual;
    }
    @Override
    public String toString() {
        return this.year+"-"+this.month+"-"+this.day;
    }
    
    
    
}
