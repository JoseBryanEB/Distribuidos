/*
 * Proyecto 4
 * Jose Bryan Estrada Bernal
 * 4CM13
 */
// paquete networking 
package networking;
import java.util.List;
import java.util.Map;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.util.concurrent.CompletableFuture;
// clase WebClient para instanciar lcientes de http
public class WebClient {
    private HttpClient client;

    public WebClient() {
      // crea un cliente de http1 usando las librerias de java 
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }
// metodo sendTask que resive la direccion url del servidor y los bytes de la tarea 
    public CompletableFuture<String> sendTask(String url, byte[] requestPayload) {
      //crea una solicitud http 
      //con metodo POST
      HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofByteArray(requestPayload))
                .uri(URI.create(url))
                .header("X-Debug","true")
                .build();
      // crea request asincrono y lo retorna en la función
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse->{return HttpResponse.body().toString();});
    }
    public  String toStringHeaders(HttpHeaders headers ){
      String result="";
      for ( Map.Entry<String,List<String>> entry: headers.map().entrySet()) {
        result+=entry.getKey()+" : "+entry.getValue()+"\n";
      }
      return result;
    }
}
