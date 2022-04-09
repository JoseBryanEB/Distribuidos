import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException; 
public class Cliente extends Thread {
 String[] commands = {"curl", "-v", "--header", "X-Debug:true", "--data", "1757600,IPN", "localhost:8080/searchipn"}; 
  public void run (){
    try {
      Runtime rt = Runtime.getRuntime();
  Process proc = rt.exec(commands);

 BufferedReader stdInput = new BufferedReader(new 
     InputStreamReader(proc.getInputStream()));

 BufferedReader stdError = new BufferedReader(new 
     InputStreamReader(proc.getErrorStream()));

// Read the output from the command
 System.out.println("Here is the standard output of the command:\n");
 String s = null;
 while ((s = stdInput.readLine()) != null) {
     System.out.println(s);
 }

 // Read any errors from the attempted command
 System.out.println("Here is the standard error of the command (if any):\n");
 while ((s = stdError.readLine()) != null) {
     System.out.println(s);
 }
    } catch (Exception e) {
      //TODO: handle exception
    }
  }
  public static void main(String[] args) {
     Cliente hilo[] = new Cliente[5];
     for (Cliente cli : hilo){
       cli = new Cliente();
       cli.run();       
     }
  }

}
