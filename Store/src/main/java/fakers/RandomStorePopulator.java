package fakers;

import domain.Categories.BookCategory;
import domain.Categories.FoodCategory;
import domain.Categories.PetsCategory;
import domain.Category;
import domain.Product;
import domain.ProductBuilder;
import store.Store;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class RandomStorePopulator {
    Store store;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public Category fillStoreRandomly() {

        Set<Category> subCategorySet = new HashSet<>();
        RandomProductGenerator products = new RandomProductGenerator();

        final String BOOK_NAME = "Book";
        final String FOOD_NAME = "Food";
        final String PETS_NAME = "Pets";

        String simpleName = null;

        for (Category type : subCategorySet){
            Category category = null;
                if (Objects.equals(simpleName, BOOK_NAME)) {
                    return new BookCategory();
                } else if (Objects.equals(simpleName, FOOD_NAME)) {
                    return new FoodCategory();
                } else if (Objects.equals(simpleName, PETS_NAME)) {
                    return new PetsCategory();
                } else {
                    return null;
                }
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
            }
            store.fillStore(subCategory);
        }
        return null;
    }
}
