package fakers;

import com.github.javafaker.Faker;
import domain.Product;

public class Fakers {
    private final Faker faker = new Faker();

    public Product createRandomProduct() {
        String fakeName = faker.name().name();
        double fakeRate = faker.number().randomDouble(2,0,50);
        double fakePrice = faker.number().randomDouble(2, 0,50);
        Product product = new Product(fakeName, fakeRate, fakePrice);
        return product;
    }
}
