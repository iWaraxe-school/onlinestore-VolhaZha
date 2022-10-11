package console.app;

import fakers.RandomStorePopulator;
import store.Store;
import xml.XMLparser;

import java.util.Map;

public class StoreApp {
    public static void main(String[] args) {
        Store onlineStore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillStoreRandomly();
        onlineStore.printAllCategoriesAndProducts();

        XMLparser parser = new XMLparser();
        Map<String, String> map = parser.parse();
        System.out.println(map);
    }
}