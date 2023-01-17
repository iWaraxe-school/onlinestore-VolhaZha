package http;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    static final int port=8080;
    public static final String STORE = "Category and Product";
    public static void serverCreate() {

        try
        {
            HttpServer server=HttpServer.create(new InetSocketAddress(port), 0);
            HttpContext categoryManager = server.createContext("/category", new CategoryManager());
            HttpContext productManager = server.createContext("/product", new ProductManager());
            HttpContext post = server.createContext("/post", new Post());
            HttpContext order = server.createContext("/order", new Order());


            categoryManager.setAuthenticator(new Authenticator (STORE));
            productManager.setAuthenticator(new Authenticator (STORE));
            order.setAuthenticator(new Authenticator (STORE));
            server.setExecutor(null);
            server.start();
            System.out.println("-----------------------------");
            System.out.println("Server started");

        } catch (IOException e) {
            throw new RuntimeException("Error to build server", e);
        }
    }
}



