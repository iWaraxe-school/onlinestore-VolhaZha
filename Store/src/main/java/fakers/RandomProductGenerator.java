package fakers;

import com.github.javafaker.Faker;
import domain.Product;

public class RandomProductGenerator {
    private final Faker faker = new Faker();

    public Product createRandomProduct() {
        String fakeName = faker.pokemon().name();
        double fakeRate = faker.number().randomDouble(2,0,50);
        double fakePrice = faker.number().randomDouble(2, 0,50);
        Product product = new Product(fakeName, fakeRate, fakePrice);
        return product;
    }
}
