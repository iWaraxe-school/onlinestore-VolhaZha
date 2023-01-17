package http;

import domain.Product;
import store.Store;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class HttpClient {
    public static void clientOrder() {
        RestAssured.baseURI = "http://localhost:8080/";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        Product RProduct = Store.getRProducts();
        String RName = RProduct.getName();
        requestParams.put("name", RName);
        request.header("Content-Type", "application/json");
        request.auth().basic("user", "password");
        request.body(requestParams.toJSONString());
        Response response = request.post("/post");
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Response body: " + response.getBody().asString());
    }
}


