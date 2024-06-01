package tr.beytp.foodculator;

import android.net.Uri;

public class Food {
    String Name;
    Double Grams = 0.0;
    Double Protein, Carbohydrate, Fat, Kcal, Sugar;
    Uri ImageLink;
    int ImageDrawable;

    public Food(String name, Double protein, Double carbohydrate, Double fat, Double kcal, Double Sugar, Uri imageLink) {
        Name = name;
        Protein = protein;
        Carbohydrate = carbohydrate;
        Fat = fat;
        Kcal = kcal;
        this.Sugar = Sugar;
        ImageLink = imageLink;
    }

    public Food(String name, Double protein, Double carbohydrate, Double fat, Double kcal, Double Sugar, int imageLink) {
        Name = name;
        Protein = protein;
        Carbohydrate = carbohydrate;
        Fat = fat;
        Kcal = kcal;
        this.Sugar = Sugar;
        ImageDrawable = imageLink;
    }

    public Double getCarbohydrate() {
        return Carbohydrate * Grams / 100;
    }

    public Double getFat() {
        return Fat * Grams / 100;
    }

    public Double getKcal() {
        return Kcal * Grams / 100;
    }

    public Double getSugar() {
        return Sugar * Grams / 100;
    }

    public Double getProtein() {
        return Protein * Grams / 100;
    }

}

