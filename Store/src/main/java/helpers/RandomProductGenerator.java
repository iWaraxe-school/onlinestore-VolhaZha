package helpers;

import com.github.javafaker.Faker;

public class RandomProductGenerator {
    private final Faker faker = new Faker();

    public String generateProductName(String categoryName) {
        switch (categoryName) {
            case "Book":
                return faker.book().title();
            case "Pets":
                return faker.animal().name();
            case "Food":
                return faker.food().dish();
        }
        return categoryName;
    }

    public double generateProductPrice() {
        return faker.number().randomDouble(2, 0,50);
    }

    public double generateProductRate() {
        return faker.number().randomDouble(2,0,50);
    }
}
