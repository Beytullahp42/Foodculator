package tr.beytp.foodculator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

public class AddYourOwnMenu extends AppCompatActivity {

    LinkedList<Food> completeList = new LinkedList<>();
    LinkedList<Food> generatedList = new LinkedList<>();
    ListView listView, listView2;
    TextView kcalTV, proteinTV, carbohydrateTV, fatTV, sugarTV;
    ImageButton deleteButton;
    SeeAddedFoodList seeAddedFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_your_own_menu);
        completeList.addAll(FoodLibrary.foodList);
        completeList.addAll(DefaultFoodLibrary.addDefaultFoods());
        listView = findViewById(R.id.listView);
        listView2 = findViewById(R.id.listView2);
        kcalTV = findViewById(R.id.kcalTV);
        proteinTV = findViewById(R.id.proteinTV);
        carbohydrateTV = findViewById(R.id.carbohydrateTV);
        fatTV = findViewById(R.id.fatTV);
        sugarTV = findViewById(R.id.sugarTV);
        seeAddedFoodList = new SeeAddedFoodList(generatedList, this);
        listView.setAdapter(seeAddedFoodList);
        listView.setEmptyView(findViewById(R.id.empty_message));
        AddToTheListView addToTheListView = new AddToTheListView(completeList, this);
        listView2.setAdapter(addToTheListView);
        listView2.setEmptyView(findViewById(R.id.TextView));
    }

    void sumOfTheListValues() {
        Food sumOfTheList = new Food("Sum", 0.0, 0.0, 0.0, 0.0, 0.0, null);
        for (Food food : generatedList) {
            sumOfTheList.Protein += food.getProtein();
            sumOfTheList.Carbohydrate += food.getCarbohydrate();
            sumOfTheList.Fat += food.getFat();
            sumOfTheList.Kcal += food.getKcal();
            sumOfTheList.Sugar += food.getSugar();
        }
        kcalTV.setText(String.format("Kcal\n%.1f", sumOfTheList.Kcal));
        proteinTV.setText(String.format("Protein\n%.1f", sumOfTheList.Protein));
        carbohydrateTV.setText(String.format("Carbs\n%.1f", sumOfTheList.Carbohydrate));
        fatTV.setText(String.format("Fat\n%.1f", sumOfTheList.Fat));
        sugarTV.setText(String.format("Sugar\n%.1f", sumOfTheList.Sugar));
    }

    private class AddToTheListView extends BaseAdapter {
        public LinkedList<Food> foodAdapter;
        Context context;

        public AddToTheListView(LinkedList<Food> foodAdapter, Context context) {
            this.foodAdapter = foodAdapter;
            this.context = context;
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater myInflater = getLayoutInflater();
                convertView = myInflater.inflate(R.layout.add_to_list, parent, false);
            }
            final Food s = foodAdapter.get(position);
            TextView name = convertView.findViewById(R.id.nameTV);
            name.setText(s.Name);
            TextView protein = convertView.findViewById(R.id.proteinTV);
            protein.setText(String.format("Protein\n%.1f", s.Protein));
            TextView carbohydrate = convertView.findViewById(R.id.carbohydrateTV);
            carbohydrate.setText(String.format("Carbs\n%.1f", s.Carbohydrate));
            TextView fat = convertView.findViewById(R.id.fatTV);
            fat.setText(String.format("Fat\n%.1f", s.Fat));
            TextView kcal = convertView.findViewById(R.id.kcalTV);
            kcal.setText(String.format("Kcal\n%.1f", s.Kcal));
            TextView sugar = convertView.findViewById(R.id.sugarTV);
            sugar.setText(String.format("Sugar\n%.1f", s.Sugar));
            ImageView imageView = convertView.findViewById(R.id.iconFood);
            if (s.ImageLink != null && !s.ImageLink.toString().equals("null")) {
                imageView.setImageURI(s.ImageLink);
                //      System.out.println(s.Name + " URI link: " + s.ImageLink);
            } else if (s.ImageDrawable != 0) {
                imageView.setImageResource(s.ImageDrawable);
                //          System.out.println(s.Name + " Drawable link: " + s.ImageDrawable);
            } else {
                imageView.setImageResource(R.drawable.placeholder);
                //         System.out.println("Both image sources were null: " + s.Name);
            }
            EditText editText = convertView.findViewById(R.id.editTextText2);
            ImageButton addButton = convertView.findViewById(R.id.addButton);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText.getText().toString().isEmpty()) {
                        Toast.makeText(context, "Please fill the blank.", Toast.LENGTH_LONG).show();
                    } else {
                        Food newFood = new Food(s.Name, s.Protein, s.Carbohydrate, s.Fat, s.Kcal, s.Sugar, s.ImageLink);
                        newFood.ImageDrawable = s.ImageDrawable;
                        System.out.println("ImageLink:" + s.ImageLink);
                        newFood.Grams = Double.parseDouble(editText.getText().toString());
                        generatedList.add(newFood);
                        seeAddedFoodList.notifyDataSetChanged();
                        sumOfTheListValues();
                    }
                }
            });
            return convertView;
        }
    }

    private class SeeAddedFoodList extends BaseAdapter {
        public LinkedList<Food> foodAdapter;

        Context context;

        public SeeAddedFoodList(LinkedList<Food> foodAdapter, Context context) {
            this.foodAdapter = foodAdapter;
            this.context = context;
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater myInflater = getLayoutInflater();
                convertView = myInflater.inflate(R.layout.ticket, parent, false);
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
            deleteButton = convertView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foodAdapter.remove(s);
                    sumOfTheListValues();
                    notifyDataSetChanged();
                    System.out.println("Deleted: " + s.Name);
                }
            });
            return convertView;
        }
    }

}