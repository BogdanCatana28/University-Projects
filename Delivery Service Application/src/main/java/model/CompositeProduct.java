package model;

import java.io.Serializable;

public class CompositeProduct  extends  MenuItem implements Serializable {
    public CompositeProduct(String title, Double rating, Double calories, Double proteins, Double fats, Double sodium, Double price) {
        super(title, rating, calories, proteins, fats, sodium, price);

    }
}
