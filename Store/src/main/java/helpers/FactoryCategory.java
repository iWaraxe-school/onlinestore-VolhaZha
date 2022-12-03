package helpers;

import domain.Categories.BookCategory;
import domain.Categories.FoodCategory;
import domain.Categories.PetsCategory;
import domain.Category;

import java.util.Objects;

public class FactoryCategory {

    public Category getCategory (String name){

        if (Objects.equals(name,"BookCategory")) {
            return new BookCategory();
        } else if (Objects.equals(name, "FoodCategory")) {
            return new FoodCategory();
        } else if (Objects.equals(name, "PetsCategory")) {
            return new PetsCategory();
        } else {
            return null;
        }
    }
}
