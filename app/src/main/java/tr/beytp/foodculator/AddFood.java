package tr.beytp.foodculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class AddFood extends AppCompatActivity {
    Button button, button2;
    EditText name, protein, carbohydrate, fat, kcal, sugar;
    ImageView imageView;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food);

        button = findViewById(R.id.buttonForImage);
        button2 = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(AddFood.this)
                        .crop(1f, 1f)
                        .start();
            }
        });
        name = findViewById(R.id.editTextText);
        protein = findViewById(R.id.protein);
        carbohydrate = findViewById(R.id.carbohydrate);
        fat = findViewById(R.id.fat);
        kcal = findViewById(R.id.kcal);
        sugar = findViewById(R.id.sugar);
        imageView = findViewById(R.id.imageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        imageView.setImageURI(uri);
    }

    public void send(View view) {
        if (name.getText().toString().trim().isEmpty()
                || protein.getText().toString().isEmpty()
                || carbohydrate.getText().toString().isEmpty()
                || fat.getText().toString().isEmpty()
                || kcal.getText().toString().isEmpty()
                || sugar.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill all the blanks", Toast.LENGTH_LONG).show();
        } else {
            FoodLibrary.foodList.add(new Food(name.getText().toString().trim(),
                    Double.parseDouble(protein.getText().toString()),
                    Double.parseDouble(carbohydrate.getText().toString()),
                    Double.parseDouble(fat.getText().toString()),
                    Double.parseDouble(kcal.getText().toString()),
                    Double.parseDouble(sugar.getText().toString()),
                    uri));
            FoodLibrary.saveData(this);
            Toast.makeText(this, "Food added.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

}