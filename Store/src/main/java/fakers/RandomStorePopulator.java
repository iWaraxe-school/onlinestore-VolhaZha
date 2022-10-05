package fakers;

import domain.Category;
import domain.Product;
import org.reflections.Reflections;
import store.Store;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
        RandomProductGenerator products = new RandomProductGenerator();
        for (Class<?> type : subTypes){
            Category category = null;
            try {
                category = (Category) type.getConstructor().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            subCategorySet.add(category);
        }
        for (Category subCategory : subCategorySet) {
            for (int i = 0; i < new Random().nextInt(10); i++) {
                Product product = products.generateProduct(subCategory.getName());
                subCategory.addProduct(product);
            }
            Store.fillStore(subCategory);
        }
    }
}
