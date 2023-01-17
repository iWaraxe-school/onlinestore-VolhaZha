package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import domain.Product;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

public class Post implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String value = null;
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            value = br.readLine();
            try {
                JSONObject obj = (JSONObject) new JSONParser().parse(value);
                String productName = obj.get("name").toString();
                List<Product> allProducts = Store.getInstance().getAllProducts();
                for (Product product : allProducts) {
                    if (product.getName().equals(productName)) {
                        Store.addOrderToList(product);
                    }
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            handleResponse(httpExchange, "Product: " + value);
    }

        public static void handleResponse (HttpExchange httpExchange, String response) throws IOException {
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
}
