/*
 * Proyecto 4
 * Jose Bryan Estrada Bernal
 * 4CM13
 */

//importacion de las librerias necesarias para crear el servidor
import networking.WebClient; 
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.util.concurrent.CompletableFuture;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.Executors;
import javax.naming.ldap.ExtendedRequest;

public class WebServer {
    
    //declaración de las cadenas que se usan como END POINTS
    private static final String TASK_ENDPOINT = "/task";
    //variables privadas para crear el webServer
    private final int port;
    private HttpServer server;
    private static WebClient webClient;
    private static String serverAddress[];
    private static String portString;

    public static void main(String[] args) {
      // por default el webServer trabaja en el puerto 8080
        int serverPort = 8080;
        serverAddress = new String[4];
        webClient = new WebClient();
        // cuando se pasa el como argumento en la consola se usa el puerto definido por el usario
        for (int i =0 ; i<args.length; i++){
            if (i == 0) {
                serverPort = Integer.parseInt(args[0]);
            }else 
            serverAddress[i-1]=args[i];
        }
        //se crea una instancia de la clase para despues inicializarlo
        WebServer webServer = new WebServer(serverPort);
        webServer.startServer();
        //cuando el servidor arranca se imprime en consola el siguiente mensaje
        System.out.println("Servidor escuchando en el puerto " + serverPort);
        //mandar el primer token
         portString = "" + serverPort;
        if (portString.charAt(portString.length()-1) == '0'){
            webClient.sendTask("http://"+serverAddress[1]+"/task" , 
            SerializationUtils.serialize(new Token(System.currentTimeMillis(),serverAddress[0]))
            ).join()    ;
        }
    }
    //inicializa la configuracion del servidor
    public WebServer(int port) {
        this.port = port;
    }
    public void startServer() {
        try {
            // crea una intancia de tcp con el puerto y la ip que se designan
            // tambien se pone en 0 para la lista de clientes pendientes
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        //se crean los contextos de HTTP haciendo uso de los ENS POINTS y de sus rutas relativas
        HttpContext taskContext = server.createContext(TASK_ENDPOINT);

        //metodos que se vehinculan s los END POINTS de tal manera que cuando se tenga una 
        //peticion se ejecunte el HANDLE del CONTEXT 
        taskContext.setHandler(this::handleTaskRequest);
        //Se crea el THREAD de hilos usando POOL de tamaño 8 y se inicializa
        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }
    //es el metodo que ejecuta las acciones del END POINT TASK
    private void handleTaskRequest(HttpExchange exchange) throws IOException {
        //se recupera el metodo para asegurarse de que es metodo post en otro caso terminara 
        //la ejecucion del metodo
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }
        // se recuperan todos los headers para recuprar el X-TEST y si su valor es true se genera la respuesta "123"
        Headers headers = exchange.getRequestHeaders();
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            String dummyResponse = "123\n";
            sendResponse(dummyResponse.getBytes(), exchange);
            return;
        }
        // si entre los headers se encutnra el header X-DEBUG y ademas su valor es true entoncers la variable isDebugMode es establece en true
        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }
        //las siguietes lineas son informacion de depuracion
        long startTime = System.nanoTime();
        //recupera la informacion del request especificamente del body 
        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        // se calcula la multiplicacion con los datos tomandolos como int
        byte[] responseBytes = calculateResponse(requestBytes);
        //se calgula el tiempo final 
        long finishTime = System.nanoTime();
        //se agrega la info de depuracion en un header de respuesta
        if (isDebugMode) {
            String debugMessage = String.format("La operación tomó %d nanosegundos", finishTime - startTime);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }
        //se envia la respuesta 
        sendResponse(responseBytes, exchange);
        // esperar dos segundos y enviar al siguiente
        Token token = (Token)SerializationUtils.deserialize(requestBytes);
        try{
            Thread.sleep(2000);
            token.calcularPromedio();
            System.out.println(token);
            char aux = portString.charAt(portString.length()-1);
            switch (aux) {
                case '0':
                    token.ip1=serverAddress[0];
                    token.Time1 = new Timestamp(System.currentTimeMillis());
                    break;
                case '1':
                token.ip2=serverAddress[0];
                token.Time2 = new Timestamp(System.currentTimeMillis());
                break;
                case '2':
                token.ip3=serverAddress[0];
                token.Time3 = new Timestamp(System.currentTimeMillis());
                break;
                case '3':
                token.ip4=serverAddress[0];
                token.Time4 = new Timestamp(System.currentTimeMillis());
                break;
                default:
                    break;
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean flag = true; 
        int server=1;
        while (flag){
            try{
                webClient.sendTask("http://"+serverAddress[server]+"/task" , 
            SerializationUtils.serialize(token)
            ).join();
            
                flag=false;
            }catch(Exception e){//e.printStackTrace();
                switch (server) {
                    case 1:
                        token.Time1 = new Timestamp(0);
                        break;
                    case 2:
                    token.Time2 = new Timestamp(0);
                    break;
                    case 3:
                    token.Time3 = new Timestamp(0);
                    break;
                    case 4:
                    token.Time4 = new Timestamp(0);
                    break;
                    default:
                        break;
                }
                server++;}
        }

    }
    //calculate response
    private byte[] calculateResponse(byte[] requestBytes) {

        return "OK".getBytes();
    }



    
    //funcion de respuesta en al que se añade el status code, la longitud de la respuesta, los headers y el cuerpo de la respuesta
    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }
}
