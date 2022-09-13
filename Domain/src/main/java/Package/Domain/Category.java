package Package.Domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Product> ProductList = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}
