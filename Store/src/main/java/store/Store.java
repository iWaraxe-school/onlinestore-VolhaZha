package store;

import domain.Category;
import domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private String name;
    private List<Category> categoryList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void fillStore (Category category) {
        categoryList.add(category);
    }

    public void printAllCategoriesAndProducts () {
        for (Category category: categoryList) {
            System.out.println(category);
        }
    }
}
