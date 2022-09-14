package console.app;

import domain.Product;
import fakers.Fakers;

public class StoreApp {
    private static void outputProduct(Product product) {
        System.out.println("Product Name: " + product.getName());
        System.out.println("Product Rate: " + product.getRate());
        System.out.println("Product Price:" + product.getPrice());
    }

    public static void main(String[] args) {
        Fakers fakeCreation = new Fakers();
        Product product1 = fakeCreation.createRandomProduct();
        outputProduct(product1);
        Product product2 = fakeCreation.createRandomProduct();
        outputProduct(product2);
    }
}
