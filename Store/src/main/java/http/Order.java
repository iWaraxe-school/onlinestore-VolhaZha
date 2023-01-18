package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import store.Store;

import java.io.IOException;
import java.io.OutputStream;

public class Order implements HttpHandler{

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        handleResponse(httpExchange, "Ordered product: \n\n" +
                Store.getInstance().getOrderList().toString());
    }

    public static void handleResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}