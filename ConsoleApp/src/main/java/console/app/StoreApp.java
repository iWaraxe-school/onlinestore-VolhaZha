package console.app;

import helpers.RandomStorePopulator;
import http.HttpClient;
import http.Server;
import multithreading.ClearOrders;
import store.Store;
import xml.ProductComparator;
import interaction.Interaction;

import java.io.IOException;
import java.sql.SQLException;

public class StoreApp {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Store onlineStore = Store.getInstance();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillStoreRandomly();
        onlineStore.printAllCategoriesAndProducts();

        ProductComparator productComparator = new ProductComparator(onlineStore);
        productComparator.sortProducts();
        productComparator.top5();

        Server.serverCreate();
        HttpClient.clientOrder();

        new ClearOrders().start();

        Interaction interaction = new Interaction();
        interaction.Interactive();
    }
}
