package fakers;

import com.github.javafaker.Faker;
import domain.Category;
import store.Store;

public class RandomStorePopulator {
    private String name;
    Store store = new Store();
    private final Faker faker = new Faker();

    private RandomCategoryGenerator categoryGenerator = new RandomCategoryGenerator();

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public Store fillStoreRandomly() {
        int categoryQuantity = faker.number().numberBetween(1, 51);
        for (int i = 1; i <= categoryQuantity; i++){
            Category category = categoryGenerator.createRandomCategory();
            store.fillStore(category);
        }
        return store;
    }
}
