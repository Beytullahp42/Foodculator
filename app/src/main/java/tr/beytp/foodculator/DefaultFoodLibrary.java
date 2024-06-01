package tr.beytp.foodculator;


import java.util.LinkedList;

public class DefaultFoodLibrary {

    public static LinkedList<Food> addDefaultFoods() {
        LinkedList<Food> list = new LinkedList<>();
        //(name, protein, carbohydrate, fat, kcal, Sugar, Uri imageLink, boolean isDeleted)
        list.add(new Food("Fries", 3.4, 41.0, 15.0, 311.0, 0.3, R.drawable.fries));
        list.add(new Food("Rice", 2.7, 28.0, 0.3, 130.0, 0.1, R.drawable.rice));
        list.add(new Food("Pasta", 5.1, 25.0, 1.1, 131.0, 0.1, R.drawable.pasta));
        list.add(new Food("Bread", 9.0, 49.0, 3.2, 265.0, 5.0, R.drawable.bread));
        list.add(new Food("Boiled Egg", 13.0, 1.1, 11.0, 155.0, 1.1, R.drawable.egg));
        list.add(new Food("Chicken Breast", 31.0, 0.0, 3.6, 165.0, 0.0, R.drawable.chicken));
        list.add(new Food("Beef", 26.0, 0.0, 15.0, 250.0, 0.0, R.drawable.beef));
        list.add(new Food("Milk 2% fat", 3.3, 4.8, 2.0, 50.0, 5.0, R.drawable.milk));
        list.add(new Food("Yogurt", 10.0, 3.6, 0.4, 59.0, 3.2, R.drawable.yogurt));
        list.add(new Food("Salmon", 22.0, 0.0, 12.0, 206.0, 0.0, R.drawable.salmon));
        list.add(new Food("Banana", 1.1, 22.8, 0.3, 89.0, 12.2, R.drawable.banana));
        list.add(new Food("Apple", 0.3, 13.8, 0.2, 52.0, 10.4, R.drawable.apple));
        list.add(new Food("Orange", 0.9, 12.0, 0.1, 47.0, 9.0, R.drawable.orange));
        list.add(new Food("Chickpeas", 19.0, 61.0, 6.0, 364.0, 11.0, R.drawable.chickpeas));
        list.add(new Food("Oatmeal", 2.4, 12.0, 1.4, 68.0, 0.5, R.drawable.oatmeal));
        return list;
    }
}

