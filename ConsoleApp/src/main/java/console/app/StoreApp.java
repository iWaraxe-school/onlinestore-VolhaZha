package console.app;

import helpers.RandomStorePopulator;
import multithreading.ClearOrders;
import store.Store;
import xml.ProductComparator;
import interaction.Interaction;

public class StoreApp {
    public static void main(String[] args) {
        Store onlineStore = Store.getInstance();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillStoreRandomly();
        onlineStore.printAllCategoriesAndProducts();

        ProductComparator productComparator = new ProductComparator(onlineStore);
        productComparator.sortProducts();
        productComparator.top5();

        new ClearOrders().start();

        Interaction interaction = new Interaction();
        interaction.Interactive();
    }
}
