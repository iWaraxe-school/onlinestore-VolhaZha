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

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct (Product product) {
        productList.add(product);
    }

    @Override
    public String toString (){
        String products = "";
        for (Product product: productList){
            String productStr = "    " + product.toString() + "\n";
            products = products + productStr;
        }
        String categoryInfo = String.format("'%s' - Name: '%s', Product List: \n%s", getClass().getSimpleName(), name, products);
        return categoryInfo;
    }
}
