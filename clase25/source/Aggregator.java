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

import networking.WebClient;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//cañse Aggregator
public class Aggregator {
  // un objeto webClient
    private WebClient webClient;
  
    public Aggregator() {
        this.webClient = new WebClient();
    }
   // metodo de la clase agregator sendTasksToWorkers que recibe una lista de los trabajdores y una lista de las tareas que deben realizar 
    public List<SimpleEntry<String,byte[]>> sendTasksToWorkers(List<String> workersAddresses, List<PoligonoIrreg> tasks) {
      //para el manejo de la comunicacion asinncrona se hace uso de la clase CompletableFuture la cual fue añadida en la version 8 de java para almacenar las respuestas futuras de los servidores
        CompletableFuture<SimpleEntry<String,byte[]>>[] futures = new CompletableFuture[workersAddresses.size()];
        //se itera sobre los elementos de los trabajadores 
        System.out.println("Objetos que se envian:");
        for (int i = 0; i < workersAddresses.size(); i++) {
          //se obtienenn las direcciones de 
            String workerAddress = workersAddresses.get(i);
            PoligonoIrreg task = tasks.get(i);
            System.out.println(task.toString());
          // se cargan las tareas como bytes
            byte[] requestPayload = SerializationUtils.serialize(task) ; 

          //se envian las tareas asinncronas
            futures[i] = webClient.sendTask(workerAddress, requestPayload);
        }
        // se crea una lsita de resultados:while esta lista se retorna como al final 
        List<SimpleEntry<String,byte[]>> results = Stream.of(futures).map(CompletableFuture::join).collect(Collectors.toList());
        return results;
    }
}
