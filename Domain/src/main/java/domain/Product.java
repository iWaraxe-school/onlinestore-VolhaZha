package domain;

public class Product {
   private String name;
   private double rate;
   private double price;

   Product(ProductBuilder productBuilder) {
      this.name = productBuilder.getName();
      this.price = productBuilder.getPrice();
      this.rate = productBuilder.getRate();
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

   @Override
   public String toString (){
      String productInfo = String.format("Product - Name: '%s', Price: %s, Rate: %s", name, price, rate);
      return productInfo;
   }
}
