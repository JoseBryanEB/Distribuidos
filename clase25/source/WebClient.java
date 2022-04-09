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
// paquete networking 
package networking;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Base64;
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
    public CompletableFuture<SimpleEntry<String,byte[]>> sendTask(String url, byte[] requestPayload) {
      //crea una solicitud http 
      //con metodo POST
      HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofByteArray(requestPayload))
                .uri(URI.create(url))
                .header("X-Debug","true")
                .build();
      // crea request asincrono y lo retorna en la función
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray())
                .thenApply(HttpResponse->{
                  return new SimpleEntry<String,byte[]>(
                      toStringHeaders(HttpResponse.headers())+HttpResponse.version().toString()+HttpResponse.uri().toString(),
                      HttpResponse.body()
                      );});
    }
    public  String toStringHeaders(HttpHeaders headers ){
      String result="";
      for ( Map.Entry<String,List<String>> entry: headers.map().entrySet()) {
        result+=entry.getKey()+" : "+entry.getValue()+"\n";
      }
      return result;
    }
    /*
    public String toStringResponse(String body){
      try {
      //System.out.println(body);
      Demo object = (Demo)SerializationUtils.deserialize(Base64.getDecoder().decode( body));
        String result = ""; 
        result = "VALOR 1: "+object.a+"\nVALOR 2:"+object.b+"\n";
        return result;
      }
      catch (Exception e ){e.printStackTrace();}
      return "";
    }*/
}
