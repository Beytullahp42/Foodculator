package tr.beytp.foodculator;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FoodLibrary extends AppCompatActivity {

    static ArrayList<Food> foodList = new ArrayList<>();
    public static MyCustomAdapter myCustomAdapter;
    ListView listView;
    ImageButton deleteButton;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_library);
        loadData(this);
        listView = findViewById(R.id.listView);
        checkBox = findViewById(R.id.checkBox);
        myCustomAdapter = new MyCustomAdapter(foodList, this);
        listView.setAdapter(myCustomAdapter);
        listView.setEmptyView(findViewById(R.id.empty_message));
    }

    static void saveData(Context context) {
        String data;
        context.deleteFile("FoodLibrary.txt");
        for (Food food : FoodLibrary.foodList) {
            data = food.Name + "#" + food.Protein + "#" + food.Carbohydrate + "#" + food.Fat + "#" + food.Kcal + "#" + food.Sugar + "#" + food.ImageLink + "\n";
            try (FileOutputStream fos = context.openFileOutput("FoodLibrary.txt", Context.MODE_APPEND)) {
                fos.write(data.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            myCustomAdapter.notifyDataSetChanged();
        } catch (Exception ignored) {}
        /*The try-catch block here is there to prevent a NullPointerException from occurring in the case that the AddFood activity is started before the myCustomAdapter adapter of the FoodLibrary is created, which can happen when the application is first opened.*/
    }

    static void loadData(Context context) {
        foodList.clear();
        FileInputStream fis;
        try {
            fis = context.openFileInput("FoodLibrary.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    String[] parts = line.split("#");
                    foodList.add(new Food(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Uri.parse(parts[6])));
                    line = reader.readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException ignored) {}
    }

    public void add(View view) {
        Intent myIntent = new Intent(this, AddFood.class);
        startActivity(myIntent);
    }

    public void deleteFoods(View view) {
        myCustomAdapter.notifyDataSetChanged();
    }

    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<Food> foodAdapter;

        Context context;

        public MyCustomAdapter(ArrayList<Food> foodAdapter, Context context) {
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
                System.out.println("It was null");
            } else {
                System.out.println("It was NOT null");
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
                    Toast.makeText(context,  s.Name + " Deleted.", Toast.LENGTH_SHORT).show();
                    foodAdapter.remove(s);
                    saveData(context);
                    myCustomAdapter.notifyDataSetChanged();
                }
            });
            if (checkBox.isChecked()) {
                deleteButton.setClickable(true);
                deleteButton.setVisibility(View.VISIBLE);
            } else {
                deleteButton.setVisibility(View.GONE);
                deleteButton.setClickable(false);
            }
            myCustomAdapter.notifyDataSetChanged();
            return convertView;
        }
    }
}