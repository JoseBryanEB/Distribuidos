/*
 * Proyecto 3
 * Jose Bryan Estrada Bernal
 * 4CM13
 */

import  java.util.*;
import java.util.Arrays;
import java.util.List;

public class Application {
   //cadenas que definen los end points de los servidores a los que se va a conectar
    private static  String WORKER_ADDRESS_1 = "http://localhost:8081/task";
    private static  String WORKER_ADDRESS_2 = "http://localhost:8082/task";
    private static  String WORKER_ADDRESS_3 = "http://localhost:8083/task";

    public static void main(String[] args) {
        List<String> palabras = new ArrayList<String>();
        if (args.length>0){
        for (int i = 0; i < args.length; i++) {
          if (i==0){
            WORKER_ADDRESS_1=args[i]; 
          }else if (i==1){
            WORKER_ADDRESS_2=args[i];
          }else if (i==2){
            WORKER_ADDRESS_3=args[i];
          }else{
            palabras.add(args[i]);
          }
        }}else{
          //for demos
          palabras=Arrays.asList("dios cristo vino ho".split(" "));
        }
        System.out.println(palabras);
        System.out.println(WORKER_ADDRESS_2);
        Aggregator aggregator = new Aggregator();
        //cadenas de los factores de cada multiplicaciones en cada servidor
        //String task1 = "10,200";
        //String task2 = "123456789,100000000000000,700000002342343";
        //se encarga de mandar la tareas a los trabajadores enn un arreglo para los trabajadores y en otro para las tareas
        List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1, WORKER_ADDRESS_2,WORKER_ADDRESS_3),
                palabras);
        //imprime los resultados de cada uno de los resultados
        for (String result : results) {
            System.out.println(result);
        }
    }
}
