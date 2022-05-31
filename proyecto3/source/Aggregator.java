/*
 * Proyecto 3
 * Jose Bryan Estrada Bernal
 * 4CM13
 */

import networking.WebClient;

import java.util.List;
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
    public List<String> sendTasksToWorkers(List<String> workersAddresses, List<String> tasks) {
      //para el manejo de la comunicacion asinncrona se hace uso de la clase CompletableFuture la cual fue añadida en la version 8 de java para almacenar las respuestas futuras de los servidores
        CompletableFuture<String>[] futures = new CompletableFuture[tasks.size()];
        //se itera sobre los elementos de los trabajadores 
        int s=0;
        for (int i = 0; i < tasks.size(); i++) {
          //se obtienenn las direcciones de 
            String workerAddress = workersAddresses.get(s++);
            if (s>=workersAddresses.size())
              s=0;
            String task = tasks.get(i);
          // se cargan las tareas como bytes
            byte[] requestPayload = task.getBytes(); 
            System.out.println(String.format("Palabra enviada \"%s\" al servidor %s",task,workerAddress));
          //se envian las tareas asinncronas
            futures[i] = webClient.sendTask(workerAddress, requestPayload);
        }
        // se crea una lsita de resultados esta lista se retorna como al final 
        List<String> results = Stream.of(futures).map(CompletableFuture::join).collect(Collectors.toList());
        return results;
    }
}
