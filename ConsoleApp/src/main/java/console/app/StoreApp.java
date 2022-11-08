package console.app;

import fakers.RandomStorePopulator;
import store.Store;
import xml.ProductComparator;
import interaction.Interaction;

public class StoreApp {
    public static void main(String[] args) {
        Store onlineStore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillStoreRandomly();
        onlineStore.printAllCategoriesAndProducts();

        ProductComparator productComparator = new ProductComparator(onlineStore);
        productComparator.sortProducts();
        productComparator.top5();

        Interaction interaction = new Interaction();
        interaction.Interactive();
    }
}
