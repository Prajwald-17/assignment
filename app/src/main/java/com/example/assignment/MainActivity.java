package com.example.assignment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RestaurantAdapter.OnItemClickListener {

    private EditText nameInput, dateInput, timeInput;
    private RecyclerView recyclerView;
    private String selectedRestaurant = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        dateInput = findViewById(R.id.dateInput);
        timeInput = findViewById(R.id.timeInput);
        Button bookButton = findViewById(R.id.bookButton);
        recyclerView = findViewById(R.id.recyclerView);

        Calendar calendar = Calendar.getInstance();
        dateInput.setOnClickListener(view -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        dateInput.setText(date);
                    }, year, month, day);
            datePickerDialog.show();
        });

        // Setup Time Picker
        timeInput.setOnClickListener(view -> {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view12, selectedHour, selectedMinute) -> {
                        String time = String.format(Locale.US, "%d:%02d", selectedHour, selectedMinute);
                        timeInput.setText(time);
                    }, hour, minute, true);
            timePickerDialog.show();
        });
        setupRestaurantList();
        bookButton.setOnClickListener(view -> {
            String name = nameInput.getText().toString();
            String date = dateInput.getText().toString();
            String time = timeInput.getText().toString();

            if (name.isEmpty() || date.isEmpty() || time.isEmpty() || selectedRestaurant == null) {
                Toast.makeText(MainActivity.this, "Please fill all fields and select a restaurant", Toast.LENGTH_SHORT).show();
            } else {
                // Pass data to BookingActivity (if you have one)
                Intent intent = new Intent(MainActivity.this, BookingActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("restaurant", selectedRestaurant);
                startActivity(intent);
            }
        });
    }

    private void setupRestaurantList() {
        // Create and populate the restaurant list
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant("Spice Garden", R.drawable.image1));
        restaurantList.add(new Restaurant("Ocean Delight", R.drawable.image2));
        restaurantList.add(new Restaurant("Mountain View", R.drawable.image3));

        RestaurantAdapter adapter = new RestaurantAdapter(this, restaurantList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(Restaurant restaurant) {
        selectedRestaurant = restaurant.getName();
        Toast.makeText(this, "Selected: " + selectedRestaurant, Toast.LENGTH_SHORT).show();
    }
}

