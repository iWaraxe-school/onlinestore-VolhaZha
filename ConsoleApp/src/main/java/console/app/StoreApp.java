package console.app;

import domain.Category;
import domain.Product;
import fakers.RandomCategoryGenerator;
import fakers.RandomStorePopulator;
import store.Store;

public class StoreApp {
    public static void main(String[] args) {
        Store onlineStore = new Store ();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillStoreRandomly();
        onlineStore.printAllCategoriesAndProducts();
    }
}
