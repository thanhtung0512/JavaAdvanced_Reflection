package WebSeverr;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpServer;
import init.ServerConfiguration;

public class WebSever {

    public WebSever() {}

    public void startSever() throws IOException {
        HttpServer httpServer = HttpServer.create(ServerConfiguration.getInstance().getSeverAddress(), 0);
        httpServer.createContext("/tung").setHandler(exchange -> {
            String responseMessage = ServerConfiguration.getInstance().getGreetingMessage();

            exchange.sendResponseHeaders(900, responseMessage.length());

            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(responseMessage.getBytes());
            responseBody.flush();
            responseBody.close();
        });

        System.out.println(String.format("Server start on address %s : %d",
                ServerConfiguration.getInstance().getSeverAddress().getHostName(),
                ServerConfiguration.getInstance().getSeverAddress().getPort()));
        
        httpServer.start();
    }
}
