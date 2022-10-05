package domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Product> productList = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addProduct (Product product) {
        productList.add(product);
    }

    public void printProductsFromList() {
        System.out.println("Category " + name);

        for (Product product : productList) {
            System.out.println(product);
        }
    }
}
