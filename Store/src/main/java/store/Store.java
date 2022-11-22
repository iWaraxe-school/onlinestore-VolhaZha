package store;

import domain.Category;
import domain.Product;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private static List<Category> categoryList = new ArrayList<>();

    public void fillStore(Category category) {
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

    private Store() {
    }
    private static class SingletonHolder {
        private final static Store instance = new Store ();
    }
    public static Store getInstance() {
        return SingletonHolder.instance;
    }
}
