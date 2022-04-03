import java.util.Random;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
public class ejercicicio5 {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]); 
    int min = 65;
    int max = 90; 
    StringBuilder cadenota= new StringBuilder();
    int contador =0;
    
    for (int i =0; i <n; i++) {
//     Random random = new Random();
     cadenota.append(String.valueOf((char)((new Random()).nextInt(max-min+1)+min)));
     cadenota.append(String.valueOf((char)((new Random()).nextInt(max-min+1)+min)));
     cadenota.append(String.valueOf((char)((new Random()).nextInt(max-min+1)+min)));
     cadenota.append(" ");
     //char r2 = (char)((new Random()).nextInt(max-min+1)+min);
     //char r3 = (char)((new Random()).nextInt(max-min+1)+min);
     //char espacio =' ';
     
    }
    ArrayList<Integer> Posiciones = new  ArrayList<Integer>();
    int ocurrencias=0;
    int endIndex=0;
    boolean flag=true;
    //encontrar la palabra IPN en la cadenota
      while (flag){
        int i=cadenota.indexOf("IPN",endIndex);
        if (i!=-1){
          Posiciones.add(i);
          ocurrencias++;
          endIndex=i+1;   
        }else flag=false;
      }
    System.out.println(Posiciones);
    System.out.println(ocurrencias);
//    String a = new String(cadenota, StandardCharsets.UTF_8);
//    System.out.println(cadenota);
        
  }
}

