package tr.beytp.foodculator;

import java.util.LinkedList;

public class Generator {
    LinkedList<Food> list = new LinkedList<>();
    Double ProteinRatio, FatRatio, CarbRatio, SugarRatio, desiredProteinRatio, desiredFatRatio, desiredCarbRatio, desiredSugarRatio;
    Double closeness;
    String mode;

    void setCloseness(Food desiredValues) {
        desiredProteinRatio = desiredValues.Protein / desiredValues.Kcal;
        desiredFatRatio = desiredValues.Fat / desiredValues.Kcal;
        desiredCarbRatio = desiredValues.Carbohydrate / desiredValues.Kcal;
        desiredSugarRatio = desiredValues.Sugar / desiredValues.Kcal;
        closeness = Math.sqrt(Math.pow(ProteinRatio - desiredProteinRatio, 2) + Math.pow(FatRatio - desiredFatRatio, 2) + Math.pow(CarbRatio - desiredCarbRatio, 2) + Math.pow(SugarRatio - desiredSugarRatio, 2));
    }

    public Generator(Food food, Food desiredValues) {
        mode = "single";

        list.add(food);
        CarbRatio = food.Carbohydrate / food.Kcal;
        FatRatio = food.Fat / food.Kcal;
        SugarRatio = food.Sugar / food.Kcal;
        ProteinRatio = food.Protein / food.Kcal;

        setCloseness(desiredValues);
        list.get(0).Grams = 100 * desiredValues.Kcal / list.get(0).Kcal;
        mode = ("desiredValues.Kcal: " + desiredValues.Kcal + " list.get(0).Kcal: " + list.get(0).Kcal + " list.get(0).Grams: " + list.get(0).Grams);
    }

    public Generator(int m1, Food food, int m2, Food food2, int m3, Food food3, Food desiredValues) {
        mode = m1 + "-" + m2 + "-" + m3;
        int sum = m1 + m2 + m3;

        list.add(food);
        list.add(food2);
        list.add(food3);
        CarbRatio = ((m1 * food.Carbohydrate / food.Kcal) + (m2 * food2.Carbohydrate / food2.Kcal) + (m3 * food3.Carbohydrate / food3.Kcal)) / sum;
        FatRatio = ((m1 * food.Fat / food.Kcal) + (m2 * food2.Fat / food2.Kcal) + (m3 * food3.Fat / food3.Kcal)) / sum;
        SugarRatio = ((m1 * food.Sugar / food.Kcal) + (m2 * food2.Sugar / food2.Kcal) + (m3 * food3.Sugar / food3.Kcal)) / sum;
        ProteinRatio = ((m1 * food.Protein / food.Kcal) + (m2 * food2.Protein / food2.Kcal) + (m3 * food3.Protein / food3.Kcal)) / sum;

        setCloseness(desiredValues);
        list.get(0).Grams = (100 * m1 * desiredValues.Kcal) / (list.get(0).Kcal * sum);
        list.get(1).Grams = (100 * m2 * desiredValues.Kcal) / (list.get(1).Kcal * sum);
        list.get(2).Grams = (100 * m3 * desiredValues.Kcal) / (list.get(2).Kcal * sum);
    }

    public Generator(int m1, Food food, int m2, Food food2, Food desiredValues) {
        mode = m1 + "-" + m2;
        int sum = m1 + m2;
        list.add(food);
        list.add(food2);
        CarbRatio = ((m1 * food.Carbohydrate / food.Kcal) + (m2 * food2.Carbohydrate / food2.Kcal)) / sum;
        FatRatio = ((m1 * food.Fat / food.Kcal) + (m2 * food2.Fat / food2.Kcal)) / sum;
        SugarRatio = ((m1 * food.Sugar / food.Kcal) + (m2 * food2.Sugar / food2.Kcal)) / sum;
        ProteinRatio = ((m1 * food.Protein / food.Kcal) + (m2 * food2.Protein / food2.Kcal)) / sum;

        setCloseness(desiredValues);
        list.get(0).Grams = ((100 * m1 * desiredValues.Kcal) / (list.get(0).Kcal)) / sum;
        list.get(1).Grams = ((100 * m2 * desiredValues.Kcal) / (list.get(1).Kcal)) / sum;
        mode = ("desiredValues.Kcal: " + desiredValues.Kcal + " list.get(0).Kcal: " + list.get(0).getKcal() + " list.get(0).Grams: " + list.get(0).Grams + " list.get(1).Grams: " + list.get(1).Grams);
    }

}
