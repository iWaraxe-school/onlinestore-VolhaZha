package multithreading;

import domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductStorage {
    private final List<Product> purchasedProductList = new ArrayList<>();

    private ProductStorage() {
    }

    private static class SingletoneHelper {
        private static final ProductStorage PRODUCT_STORAGE = new ProductStorage();
    }

    public static ProductStorage getInstance() {
        return SingletoneHelper.PRODUCT_STORAGE;
    }

    public synchronized void addPurchasedProduct (Product product) {
        purchasedProductList.add(product);
    }

    public synchronized void clearPurchasedProductList () {
        purchasedProductList.clear();
    }

    public void printPurchasedProducts() {
        System.out.println("-----------------------------");
        System.out.println("Cart refreshed");
        int index = 0;
        for (Product product : purchasedProductList) {
            index ++;
            System.out.println(index + ": " + product);
        }
        System.out.println("-----------------------------");
        System.out.println(index + " products are in the Cart");
        System.out.println("-----------------------------");
    }
}
