package console.app;

import helpers.DbHandler;
import helpers.RandomStorePopulator;
import multithreading.ClearOrders;
import store.Store;
import xml.ProductComparator;
import interaction.Interaction;

import java.sql.SQLException;

public class StoreApp {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Store onlineStore = Store.getInstance();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillStoreRandomly();
        onlineStore.printAllCategoriesAndProducts();

        ProductComparator productComparator = new ProductComparator(onlineStore);
        productComparator.sortProducts();
        productComparator.top5();

        DbHandler dbHandler = new DbHandler(onlineStore);
        dbHandler.getDbConnection();
        dbHandler.clearDb();
        dbHandler.createCategoryTable();
        dbHandler.createProductsTable();
        dbHandler.fillStoreRandomly();
        dbHandler.printFilledStore();

        new ClearOrders().start();

        Interaction interaction = new Interaction();
        interaction.Interactive();
    }
}
