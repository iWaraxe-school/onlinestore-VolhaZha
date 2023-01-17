package store;

import domain.Category;
import domain.Product;
import domain.ProductBuilder;
import helpers.RandomProductGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Store {

    private static List<Product> orderList = new ArrayList<>();

    public void setOrderList(List<Product> orderList) {
        this.orderList = orderList;
    }

    public List<Product> getOrderList() {
        return orderList;
    }

    public static void addOrderToList (Product product) {
        System.out.println("Add product " + product + " to order list");
        orderList.add(product);
    }

    private static List<Category> categoryList = new ArrayList<>();

    public static List<Category> getCategoryList() {
        return categoryList;
    }

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
    public static Product getRProducts(){
        List<Product>listOfAllProducts = new ArrayList<>();
        for(Category category: categoryList){
            List<Product> productList = category.getProductList();
            listOfAllProducts.addAll(productList);
        }
        Random rand = new Random();
        Product randomElement = listOfAllProducts.get(rand.nextInt(listOfAllProducts.size()));
        return randomElement;
    }
    public List<String> getAllCategories() {
        return getCategoryList().stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    private Store() {
    }

    private static Store instance;

    private static class SingletonHolder {
        private final static Store instance = new Store ();
    }
    public static Store getInstance() {
        if (instance == null) {
            synchronized (Store.class) {
                if (instance == null) {
                    instance = new Store();
                }
            }
        }
        return instance;
    }
    public Product getRandomProductFromStore () {
        RandomProductGenerator products = new RandomProductGenerator();
        Product product = new ProductBuilder()
                .name(products.generateProductName("Book"))
                .price(products.generateProductPrice())
                .rate(products.generateProductRate())
                .build();
        return product;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        getCategoryList().forEach(category -> stringBuilder.append('\n' + "Category: ")
                .append(category.getName())
                .append('\n'));
        return stringBuilder.toString();
    }

}
