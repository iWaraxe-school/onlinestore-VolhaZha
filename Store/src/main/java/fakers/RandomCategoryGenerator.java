package fakers;

import com.github.javafaker.Faker;
import domain.Categories.BikeCategory;
import domain.Categories.MilkCategory;
import domain.Categories.PhoneCategory;
import domain.Category;
import domain.Product;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;

public class RandomCategoryGenerator {
    private final Faker faker = new Faker();
    private RandomProductGenerator productGenerator = new RandomProductGenerator();

    private List<Class<?>> categoryTypes;

    public RandomCategoryGenerator() {
        Reflections reflections = new Reflections("domain");
        Set<Class<?>> subTypes =
                reflections.get(SubTypes.of(Category.class).asClass());
        categoryTypes = new ArrayList<>(subTypes);
    }

    public Category createRandomEmptyCategory() {
        String fakeName = faker.country().capital();
        int categoryTypeIndex = faker.number().numberBetween(0, categoryTypes.size());
        Class<?> categoryType = categoryTypes.get(categoryTypeIndex);
        try {
           // categoryType.getConstructor(String.class);
            Constructor<?> categoryConstructor = categoryType.getConstructor(String.class);
           // categoryConstructor.newInstance(fakeName);
            Category category = (Category) categoryConstructor.newInstance(fakeName);
            return category;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Category createRandomCategory() {
        Category category = createRandomEmptyCategory();
        int productQuantity = faker.number().numberBetween(1, 51);
        for (int i = 1; i <= productQuantity; i++){
            Product product = productGenerator.createRandomProduct();
            category.addProduct(product);
        }
        return category;
    }
}
