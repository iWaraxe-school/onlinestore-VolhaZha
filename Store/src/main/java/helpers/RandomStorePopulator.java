package helpers;

import domain.Category;
import domain.Product;
import domain.ProductBuilder;
import org.reflections.Reflections;
import store.Store;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;

public class RandomStorePopulator {
    Store store;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public void fillStoreRandomly() {
        Reflections reflections = new Reflections("domain.Categories");
        Set<Class<?>> subTypes =
                reflections.get(SubTypes.of(Category.class).asClass());

        Set<Category> subCategorySet = new HashSet<>();
        FactoryCategory factoryCategory = new FactoryCategory();
        RandomProductGenerator products = new RandomProductGenerator();

        for (Class<?> type : subTypes) {
                   String simpleName = type.getSimpleName();
                   Category categoryToAdd = factoryCategory.getCategory(simpleName);
                   store.fillStore(categoryToAdd);
                   subCategorySet.add(categoryToAdd);

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
            }
        }
    }
}
