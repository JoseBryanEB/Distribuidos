/*
 * Proyecto 4
 * Jose Bryan Estrada Bernal
 * 4CM13
 */
import java.io.Serializable;
import java.sql.Timestamp;
public class Token implements Serializable{
  public Timestamp Time1; 
  public Timestamp Time2; 
  public Timestamp Time3;
  public Timestamp Time4;
  public String ip1;
  public String ip2; 
  public String ip3; 
  public String ip4;
  public Timestamp Global;
  Token (long time0,String ip0) {
    Time1 = new Timestamp(time0);
    Time2 = new Timestamp(0);
    Time3 = new Timestamp(0);
    Time4 = new Timestamp(0);
    Global = new Timestamp(0);  
    ip1 = ip0; 
    ip2 = "";
    ip3 = "";
    ip4 = "";
  
  } 
  public void calcularPromedio(){
    int contador=0;
    long promedio = 0;
    if (Time1.getTime() >0){
      promedio+=Time1.getTime();
      contador++;
    }
    if (Time2.getTime() >0){
      promedio+=Time2.getTime();
      contador++;
    }
    if (Time3.getTime() >0){
      promedio+=Time3.getTime();
      contador++;
    }
    if (Time4.getTime() >0){
      promedio+=Time4.getTime();
      contador++;
    }
    
    Global = new Timestamp(promedio/contador);
  }
  @Override
  public String toString(){
    return String.format("Direcciones ip\nip1:%s Tiempo1:%s\nip2:%s Tiempo2:%s\nip3:%s Tiempo3:%s\nip4:%s Tiempo4:%s\npromedio:%s", 
    ip1,Time1,ip2,Time2,ip3,Time3,ip4,Time4,Global);
  }
  
}
