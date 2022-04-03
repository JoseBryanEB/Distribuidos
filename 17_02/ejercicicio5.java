import java.util.Random;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
public class ejercicicio5 {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    byte IPN[] = {'I','P','N'}; 
    int min = 65;
    int max = 90; 
    byte []cadenota= new byte [n*4];
    int contador =0;
    
    for (int i =0; i <n; i++) {
//     Random random = new Random();
     byte r1 = (byte)((new Random()).nextInt(max-min+1)+min);
     byte r2 = (byte)((new Random()).nextInt(max-min+1)+min);
     byte r3 = (byte)((new Random()).nextInt(max-min+1)+min);
     byte espacio =(byte) ' ';
     cadenota[contador++]=r1;
     cadenota[contador++]=r2; 
     cadenota[contador++]=r3;
     cadenota[contador++]=espacio;
//     String a = new String(cadenota, StandardCharsets.UTF_8);
  //    System.out.printf("resultado: %s",a);
    }
    ArrayList<Integer> Posiciones = new  ArrayList<Integer>();
    int concurrencias=0;
    //encontrar la palabra IPN en la cadenota
    for (int i = 0; i < cadenota.length; i+=4) {
      if (cadenota[i]==IPN[0] && cadenota[i+1]==IPN[1] && cadenota[i+2]==IPN[2]){
        Posiciones.add(i);
        concurrencias++;
      }
    }
    System.out.println(Posiciones);
    System.out.println(concurrencias);
//    String a = new String(cadenota, StandardCharsets.UTF_8);
  //  System.out.println(a);
        
  }
}

