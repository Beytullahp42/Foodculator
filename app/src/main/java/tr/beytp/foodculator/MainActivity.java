package tr.beytp.foodculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FoodLibrary.loadData(this);
    }

    public void generate(View view) {
        Intent myIntent = new Intent(this, DietPlan.class);
        startActivity(myIntent);
    }

    public void addYourMenu(View view) {
        Intent myIntent = new Intent(this, AddYourOwnMenu.class);
        startActivity(myIntent);
    }
    public void showList(View view) {
        Intent myIntent = new Intent(this, FoodLibrary.class);
        startActivity(myIntent);
    }
    public void addFood(View view) {
        Intent myIntent = new Intent(this, AddFood.class);
        startActivity(myIntent);
    }
}