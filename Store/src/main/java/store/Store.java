package store;

import domain.Category;
import domain.Product;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private static List<Category> categoryList = new ArrayList<>();

    public static void fillStore(Category category) {
        categoryList.add(category);
    }

    public void printAllCategoriesAndProducts () {
        for (Category category: categoryList) {
            category.printProductsFromList();
        }
    }

    public List<Product> getAllProducts(){
        List<Product>listOfAllProducts = new ArrayList<>();
        for(Category category: categoryList){
            List<Product> productList = category.getProductList();
            listOfAllProducts.addAll(productList);
        }
        return listOfAllProducts;
    }
}
