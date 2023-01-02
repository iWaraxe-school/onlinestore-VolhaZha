package helpers;
import domain.Category;
import domain.Product;
import domain.ProductBuilder;
import org.reflections.Reflections;
import store.Store;

import java.sql.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;

public class DbHandler extends DbConfigs {
    static ResultSet RESULTSET = null;
    Store store;
    Connection dbConnection;

    public DbHandler (Store store) { this.store = store;};

    public Connection getDbConnection ()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbConnection;
    }

    public void clearDb () throws SQLException, ClassNotFoundException {
        String query1 = "DROP TABLE IF EXISTS CATEGORY;";
        String query2 = "DROP TABLE IF EXISTS PRODUCTS;";
            PreparedStatement preparedStatementQuery1 = getDbConnection()
                    .prepareStatement(query1);
            PreparedStatement preparedStatementQuery2 = getDbConnection()
                .prepareStatement(query2);
            preparedStatementQuery2.executeUpdate();
            preparedStatementQuery1.executeUpdate();
    }

    public void createCategoryTable () throws SQLException, ClassNotFoundException {
        String createCategoryTable = String.format("CREATE TABLE IF NOT EXISTS CATEGORY (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "NAME VARCHAR(255) NOT NULL);");
        PreparedStatement preparedStatementCreateCategoryTable = getDbConnection().prepareStatement(createCategoryTable);
        preparedStatementCreateCategoryTable.executeUpdate();
        System.out.println ("-----------------------------");
        System.out.println ("Category Table created");
    }

    public void createProductsTable () throws SQLException, ClassNotFoundException {
        String createProductsTable = String.format("CREATE TABLE IF NOT EXISTS PRODUCTS (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "CATEGORY_ID INT NOT NULL, " +
                "NAME VARCHAR(45) NOT NULL, " +
                "PRICE DOUBLE NOT NULL, " +
                "RATE DOUBLE NOT NULL, " +
                "FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY(ID));");
        PreparedStatement preparedStatementCreateProductsTable = getDbConnection().prepareStatement(createProductsTable);
        preparedStatementCreateProductsTable.executeUpdate();
        System.out.println ("-----------------------------");
        System.out.println ("Products Table created");
    }

    public void addCategoryToDb (int id, String name) throws SQLException
            , ClassNotFoundException {
        String insert = ("INSERT INTO CATEGORY (ID, NAME ) VALUES (?,?);");
        PreparedStatement preparedStatement = getDbConnection()
                .prepareStatement(insert);
        preparedStatement.setInt (1, id);
        preparedStatement.setString(2,name);
        preparedStatement.executeUpdate();
        System.out.println ("-----------------------------");
        System.out.println ("Category Table updated");
    }

    public void addProductToDb (int id, int categoryId, String name, int price, int rate) throws SQLException
            , ClassNotFoundException {
        String insert = ("INSERT INTO PRODUCTS (ID, CATEGORY_ID, NAME, PRICE, RATE ) VALUES (?,?,?,?,?);");
        PreparedStatement preparedStatement = getDbConnection()
                .prepareStatement(insert);
        preparedStatement.setInt (1, id);
        preparedStatement.setInt (2, categoryId);
        preparedStatement.setString(3,name);
        preparedStatement.setInt (4, price);
        preparedStatement.setInt (5, rate);
        preparedStatement.executeUpdate();
        System.out.println ("-----------------------------");
        System.out.println ("Products Table updated");
    }

    public void fillStoreRandomly () throws SQLException
        , ClassNotFoundException {
    RandomProductGenerator products = new RandomProductGenerator();
    Set<Category> subCategorySet = new HashSet<>();
    Reflections reflections = new Reflections("domain.Categories");
    Set<Class<?>> subTypes =
            reflections.get(SubTypes.of(Category.class).asClass());
    FactoryCategory factoryCategory = new FactoryCategory();

    for (Class<?> type : subTypes) {
        String simpleName = type.getSimpleName();
        Category categoryToAdd = factoryCategory.getCategory(simpleName);
        store.fillStore(categoryToAdd);
        subCategorySet.add(categoryToAdd);

        String insertC = ("INSERT INTO CATEGORY (NAME ) VALUES (?);");
        PreparedStatement insertCategories = getDbConnection()
                .prepareStatement(insertC);
        insertCategories.setString(1, categoryToAdd.getName());
        insertCategories.executeUpdate();
        System.out.println("-----------------------------");
        System.out.println("Category Table updated with " + categoryToAdd.getName());

        String find = ("SELECT ID FROM CATEGORY WHERE NAME = (?);");
        PreparedStatement findCategoryID = getDbConnection()
                .prepareStatement(find);
        findCategoryID.setString(1, categoryToAdd.getName());
        RESULTSET = findCategoryID.executeQuery();

        int id = 0;
        while (RESULTSET.next()){
            id = RESULTSET.getInt("ID");
        }

        for (Category subCategory : subCategorySet) {
            Random random = new Random();
            for (int i = 0; i < random.nextInt(10); i++) {
                Product product = new ProductBuilder()
                        .name(products.generateProductName(subCategory.getName()))
                        .price(products.generateProductPrice())
                        .rate(products.generateProductRate())
                        .build();
                subCategory.addProduct(product);

                String insertP = ("INSERT INTO PRODUCTS (CATEGORY_ID, NAME, PRICE, RATE ) VALUES (?,?,?,?)");
                PreparedStatement insertProduct = getDbConnection()
                        .prepareStatement(insertP);
                insertProduct.setInt (1, id);
                insertProduct.setString(2, products.generateProductName(categoryToAdd.getName()));
                insertProduct.setDouble (3, products.generateProductPrice());
                insertProduct.setDouble (4, products.generateProductRate());
                insertProduct.execute();
            }
        }
    }
    }
    public void printFilledStore () throws SQLException
            , ClassNotFoundException {

        String printCat = ("SELECT * FROM CATEGORY");
        PreparedStatement preparedStatement = getDbConnection()
                .prepareStatement(printCat);
        ResultSet setOfCategories = preparedStatement.executeQuery();
        while (setOfCategories.next()) {
            String nameOfCategory = setOfCategories.getString(2);
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Category: " + nameOfCategory);

            String printProd = String.format("SELECT NAME, PRICE, RATE FROM PRODUCTS WHERE CATEGORY_ID = %s", setOfCategories.getString(1));
            PreparedStatement preparedStatement1 = getDbConnection()
                    .prepareStatement(printProd);
            ResultSet setOfProducts = preparedStatement1.executeQuery();
            while (setOfProducts.next()) {
                String nameOfProduct = setOfProducts.getString(1);
                String priceOfProduct = String.valueOf(setOfProducts.getDouble(2));
                String rateOfProduct = String.valueOf(setOfProducts.getDouble(3));
                System.out.println(nameOfProduct + " " + priceOfProduct + " " + rateOfProduct);
            }
        }
    }
}







