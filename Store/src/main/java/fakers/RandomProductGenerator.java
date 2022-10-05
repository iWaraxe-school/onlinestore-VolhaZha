package fakers;

import com.github.javafaker.Faker;
import domain.Product;

public class RandomProductGenerator {
    private final Faker faker = new Faker();

    public Product generateProduct (String categoryName){
        return new Product(
                generateProductName(categoryName),
                generateProductPrice(),
                generateProductRate()
        );
    }

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
