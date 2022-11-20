package domain;

public class ProductBuilder {
    private String name;
    private double rate;
    private double price;

    public ProductBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder rate(final double rate) {
        this.rate = rate;
        return this;
    }

    public ProductBuilder price(final double price) {
        this.price = price;
        return this;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }

    public Product build() {
        return new Product(this);
    }
}
