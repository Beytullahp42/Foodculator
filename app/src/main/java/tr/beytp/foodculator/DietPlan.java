package tr.beytp.foodculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.LinkedList;

public class DietPlan extends AppCompatActivity {

    LinkedList<Food> completeList = new LinkedList<>();
    LinkedList<Food> generatedList = new LinkedList<>();
    Double maxKcalValue, maxProteinValue, maxFatValue, maxCarbValue, maxSugarValue;
    EditText minKcal, minProtein, minFat, minCarb, minSugar;
    TextView kcalTV, proteinTV, fatTV, carbTV, sugarTV;
    MyCustomAdapter myCustomAdapter;
    ListView listView;

    Food sumOfTheList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_plan);

        listView = findViewById(R.id.listView);
        myCustomAdapter = new MyCustomAdapter(generatedList);

        minKcal = findViewById(R.id.desiredKcal);
        minProtein = findViewById(R.id.desiredProtein);
        minFat = findViewById(R.id.desiredFat);
        minCarb = findViewById(R.id.desiredCarbs);
        minSugar = findViewById(R.id.desiredSugar);
        kcalTV = findViewById(R.id.kcalTV);
        proteinTV = findViewById(R.id.proteinTV);
        fatTV = findViewById(R.id.fatTV);
        carbTV = findViewById(R.id.carbohydrateTV);
        sugarTV = findViewById(R.id.sugarTV);
        listView.setAdapter(myCustomAdapter);
        listView.setEmptyView(findViewById(R.id.empty_message));

    }

    Generator Loops() {
        Generator bestGenerated = null;
        Double closeness = Double.MAX_VALUE;
        for (int i = 0; i < completeList.size(); i++) {
            Food food1 = new Food(completeList.get(i).Name, completeList.get(i).Protein, completeList.get(i).Carbohydrate, completeList.get(i).Fat, completeList.get(i).Kcal, completeList.get(i).Sugar, completeList.get(i).ImageLink);
            food1.ImageDrawable = completeList.get(i).ImageDrawable;
            Generator generated = new Generator(food1, new Food("Desired Values", maxProteinValue, maxCarbValue, maxFatValue, maxKcalValue, maxSugarValue, null));
            if (generated.closeness < closeness) {
                closeness = generated.closeness;
                bestGenerated = generated;
            }
            if (closeness < 0.0001) {
                System.out.println("early break");
                return bestGenerated;
            }
        }

        for (int m1 = 1; m1 <= 5; m1++) {
            for (int m2 = 1; m2 <= 5; m2++) {
                for (int m3 = 1; m3 <= 5; m3++) {
                    for (int i = 0; i < completeList.size(); i++) {
                        for (int j = 0; j < completeList.size(); j++) {
                            for (int k = 0; k < completeList.size(); k++) {
                                if (i != j && i != k && j != k) {
                                    Food food1 = new Food(completeList.get(i).Name, completeList.get(i).Protein, completeList.get(i).Carbohydrate, completeList.get(i).Fat, completeList.get(i).Kcal, completeList.get(i).Sugar, completeList.get(i).ImageLink);
                                    food1.ImageDrawable = completeList.get(i).ImageDrawable;
                                    Food food2 = new Food(completeList.get(j).Name, completeList.get(j).Protein, completeList.get(j).Carbohydrate, completeList.get(j).Fat, completeList.get(j).Kcal, completeList.get(j).Sugar, completeList.get(j).ImageLink);
                                    food2.ImageDrawable = completeList.get(j).ImageDrawable;
                                    Food food3 = new Food(completeList.get(k).Name, completeList.get(k).Protein, completeList.get(k).Carbohydrate, completeList.get(k).Fat, completeList.get(k).Kcal, completeList.get(k).Sugar, completeList.get(k).ImageLink);
                                    food3.ImageDrawable = completeList.get(k).ImageDrawable;
                                    Generator generated = new Generator(m1, food1, m2, food2, m3, food3, new Food("Desired Values", maxProteinValue, maxCarbValue, maxFatValue, maxKcalValue, maxSugarValue, null));
                                    if (generated.closeness < closeness) {
                                        closeness = generated.closeness;
                                        bestGenerated = generated;
                                    }
                                    if (closeness < 0.0001) {
                                        System.out.println("early break");
                                        return bestGenerated;
                                    }
                                }
                            }
                        }
                    }
                }

            }

        }
        for (int m1 = 1; m1 <= 5; m1++) {
            for (int m2 = 1; m2 <= 5; m2++) {
                for (int i = 0; i < completeList.size(); i++) {
                    for (int j = 0; j < completeList.size(); j++) {
                        if (i != j) {
                            Food food1 = new Food(completeList.get(i).Name, completeList.get(i).Protein, completeList.get(i).Carbohydrate, completeList.get(i).Fat, completeList.get(i).Kcal, completeList.get(i).Sugar, completeList.get(i).ImageLink);
                            food1.ImageDrawable = completeList.get(i).ImageDrawable;
                            Food food2 = new Food(completeList.get(j).Name, completeList.get(j).Protein, completeList.get(j).Carbohydrate, completeList.get(j).Fat, completeList.get(j).Kcal, completeList.get(j).Sugar, completeList.get(j).ImageLink);
                            food2.ImageDrawable = completeList.get(j).ImageDrawable;
                            Generator generated = new Generator(m1, food1, m2, food2, new Food("Desired Values", maxProteinValue, maxCarbValue, maxFatValue, maxKcalValue, maxSugarValue, null));
                            if (generated.closeness < closeness) {
                                closeness = generated.closeness;
                                bestGenerated = generated;
                            }
                            if (closeness < 0.0001) {
                                System.out.println("early break");
                                return bestGenerated;
                            }
                        }
                    }
                }
            }
        }

        return bestGenerated;
    }

    LinkedList<Food> generateList() {
        completeList.clear();
        completeList.addAll(FoodLibrary.foodList);
        completeList.addAll(DefaultFoodLibrary.addDefaultFoods());

        maxProteinValue = Double.parseDouble(minProtein.getText().toString());
        maxFatValue = Double.parseDouble(minFat.getText().toString());
        maxCarbValue = Double.parseDouble(minCarb.getText().toString());
        maxSugarValue = Double.parseDouble(minSugar.getText().toString());
        maxKcalValue = Double.parseDouble(minKcal.getText().toString());
        System.out.println("MaxProtein: " + maxProteinValue + " MaxFat: " + maxFatValue + " MaxCarb: " + maxCarbValue + " MaxSugar: " + maxSugarValue + " MaxKcal: " + maxKcalValue);
        Generator bestGenerated = Loops();
        sumOfTheList = new Food("Sum of the list", 0.0, 0.0, 0.0, 0.0, 0.0, null);
        for (int i = 0; i < bestGenerated.list.size(); i++) {
            sumOfTheList.Fat += bestGenerated.list.get(i).getFat();
            sumOfTheList.Carbohydrate += bestGenerated.list.get(i).getCarbohydrate();
            sumOfTheList.Protein += bestGenerated.list.get(i).getProtein();
            sumOfTheList.Kcal += bestGenerated.list.get(i).getKcal();
            sumOfTheList.Sugar += bestGenerated.list.get(i).getSugar();
        }
        System.out.println(bestGenerated.mode);
        return bestGenerated.list;
    }

    @SuppressLint("DefaultLocale")
    public void calculate(View view) {
        int emptyBlanks = 0;
        if (minKcal.getText().toString().isEmpty()) {
            emptyBlanks++;
        }
        if (minProtein.getText().toString().isEmpty()) {
            emptyBlanks++;
        }
        if (minFat.getText().toString().isEmpty()) {
            emptyBlanks++;
        }
        if (minCarb.getText().toString().isEmpty()) {
            emptyBlanks++;
        }
        if (minSugar.getText().toString().isEmpty()) {
            emptyBlanks++;
        }

        if (emptyBlanks == 0) {
            generatedList.clear();
            generatedList.addAll(generateList());
            myCustomAdapter.notifyDataSetChanged();
            kcalTV.setText(String.format("Kcal: %.1f", sumOfTheList.Kcal));
            proteinTV.setText(String.format("Protein: %.1f", sumOfTheList.Protein));
            fatTV.setText(String.format("Fat: %.1f", sumOfTheList.Fat));
            carbTV.setText(String.format("Carbs: %.1f", sumOfTheList.Carbohydrate));
            sugarTV.setText(String.format("Sugar: %.1f", sumOfTheList.Sugar));
            System.out.println("Kcal: " + sumOfTheList.Kcal + " Protein: " + sumOfTheList.Protein + " Fat: " + sumOfTheList.Fat + " Carbs: " + sumOfTheList.Carbohydrate + " Sugar: " + sumOfTheList.Sugar);
        } else if (emptyBlanks == 4) {
            maxProteinValue = (minProtein.getText().toString().isEmpty()) ? Double.MAX_VALUE : Double.parseDouble(minProtein.getText().toString());
            maxFatValue = (minFat.getText().toString().isEmpty()) ? Double.MAX_VALUE : Double.parseDouble(minFat.getText().toString());
            maxCarbValue = (minCarb.getText().toString().isEmpty()) ? Double.MAX_VALUE : Double.parseDouble(minCarb.getText().toString());
            maxSugarValue = (minSugar.getText().toString().isEmpty()) ? Double.MAX_VALUE : Double.parseDouble(minSugar.getText().toString());
            maxKcalValue = (minKcal.getText().toString().isEmpty()) ? Double.MAX_VALUE : Double.parseDouble(minKcal.getText().toString());
            generatedList.clear();
            generatedList.addAll(basedOnOneFact());
            myCustomAdapter.notifyDataSetChanged();
            kcalTV.setText(String.format("Kcal: %.1f", sumOfTheList.Kcal));
            proteinTV.setText(String.format("Protein: %.1f", sumOfTheList.Protein));
            fatTV.setText(String.format("Fat: %.1f", sumOfTheList.Fat));
            carbTV.setText(String.format("Carbs: %.1f", sumOfTheList.Carbohydrate));
            sugarTV.setText(String.format("Sugar: %.1f", sumOfTheList.Sugar));
        } else {
            Toast.makeText(this, "Please fill all the blanks or fill just one blank", Toast.LENGTH_SHORT).show();
        }
    }

    LinkedList<Food> basedOnOneFact() {
        completeList.clear();
        completeList.addAll(FoodLibrary.foodList);
        completeList.addAll(DefaultFoodLibrary.addDefaultFoods());
        LinkedList<Food> list = new LinkedList<>();
        sumOfTheList = new Food("Sum of the list", 0.0, 0.0, 0.0, 0.0, 0.0, null);
        Collections.shuffle(completeList);
        for (Food food : completeList) {
            food.Grams = 0.0;
            while (food.getFat() + 0.0001 < maxFatValue - sumOfTheList.Fat
                    && food.getCarbohydrate() + 0.0001 < maxCarbValue - sumOfTheList.Carbohydrate
                    && food.getProtein() < maxProteinValue - sumOfTheList.Protein
                    && food.getKcal() + 0.0001 < maxKcalValue - sumOfTheList.Kcal
                    && food.getSugar() + 0.0001 < maxSugarValue - sumOfTheList.Sugar) {
                food.Grams += 0.1;
            }
            sumOfTheList.Fat += food.getFat();
            sumOfTheList.Carbohydrate += food.getCarbohydrate();
            sumOfTheList.Protein += food.getProtein();
            sumOfTheList.Kcal += food.getKcal();
            sumOfTheList.Sugar += food.getSugar();
            if (food.Grams > 0.0) {
                list.add(food);
            }

        }
        return list;
    }

    private class MyCustomAdapter extends BaseAdapter {
        public LinkedList<Food> foodAdapter;

        public MyCustomAdapter(LinkedList<Food> foodAdapter) {
            this.foodAdapter = foodAdapter;
        }

        @Override
        public int getCount() {
            return foodAdapter.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("DefaultLocale")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater myInflater = getLayoutInflater();
                convertView = myInflater.inflate(R.layout.result, parent, false);
                System.out.println("It was null");
            } else {
                System.out.println("It was NOT null");
            }
            final Food s = foodAdapter.get(position);
            TextView name = convertView.findViewById(R.id.nameTV);
            name.setText(s.Name);
            TextView protein = convertView.findViewById(R.id.proteinTV);
            protein.setText(String.format("Protein\n%.1f", s.getProtein()));
            TextView carbohydrate = convertView.findViewById(R.id.carbohydrateTV);
            carbohydrate.setText(String.format("Carbs\n%.1f", s.getCarbohydrate()));
            TextView fat = convertView.findViewById(R.id.fatTV);
            fat.setText(String.format("Fat\n%.1f", s.getFat()));
            TextView kcal = convertView.findViewById(R.id.kcalTV);
            kcal.setText(String.format("Kcal\n%.1f", s.getKcal()));
            TextView sugar = convertView.findViewById(R.id.sugarTV);
            sugar.setText(String.format("Sugar\n%.1f", s.getSugar()));
            ImageView imageView = convertView.findViewById(R.id.iconFood);
            if (s.ImageLink != null && !s.ImageLink.toString().equals("null")) {
                imageView.setImageURI(s.ImageLink);
                System.out.println(s.Name + " URI link: " + s.ImageLink);
            } else if (s.ImageDrawable != 0) {
                imageView.setImageResource(s.ImageDrawable);
                System.out.println(s.Name + " Drawable link: " + s.ImageDrawable);
            } else {
                imageView.setImageResource(R.drawable.placeholder);
                System.out.println("Both image sources were null: " + s.Name);
            }
            TextView grams = convertView.findViewById(R.id.Grams);
            grams.setText(String.format("Grams\n%.1f", s.Grams));
            return convertView;
        }
    }
}