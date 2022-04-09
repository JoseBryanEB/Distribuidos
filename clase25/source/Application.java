/*
 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

import java.util.Arrays;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class Application {
   //cadenas que definen los end points de los servidores a los que se va a conectar
    private static final String WORKER_ADDRESS_1 = "http://localhost:8081/task";
    private static final String WORKER_ADDRESS_2 = "http://localhost:8082/task";

    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator();
        //cadenas de los factores de cada multiplicaciones en cada servidor
        int max =100;
        int min = -100;
        Random ran = new Random();
        PoligonoIrreg task1 = new PoligonoIrreg();
        PoligonoIrreg task2 = new PoligonoIrreg();
        List<PoligonoIrreg> list = Arrays.asList(task1,task2);
        for (PoligonoIrreg pol:list){
          for (int i = 0; i < 3; i++) {
              pol.anadeVertice(new Coordenada(
              min+(max-min)*ran.nextDouble()
              ,min+(max-min)*ran.nextDouble()));
          }
        }
        //se encarga de mandar la tareas a los trabajadores enn un arreglo para los trabajadores y en otro para las tareas
        while(true){
          List<SimpleEntry<String,byte[]>> results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1, WORKER_ADDRESS_2),
                  list);
          //imprime los resultados de cada uno de los resultados
          System.out.println("Respuestas");
          list = new ArrayList<PoligonoIrreg>();
          for (Map.Entry result : results) {
             System.out.println(result.getKey());
             PoligonoIrreg aux = (PoligonoIrreg) SerializationUtils.deserialize((byte[])result.getValue());
             System.out.println(aux.toString());
              aux.anadeVertice(new Coordenada(
              min+(max-min)*ran.nextDouble()
              ,min+(max-min)*ran.nextDouble()));

             list.add(aux);
          }
        }
    }
}
