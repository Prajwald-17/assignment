package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        String name = getIntent().getStringExtra("restaurantName");
        int imageResId = getIntent().getIntExtra("imageResId", 0);

        TextView nameTextView = findViewById(R.id.restaurantNameTextView);
        ImageView imageView = findViewById(R.id.restaurantImageView);
        Button bookButton = findViewById(R.id.bookButton);
        Button returnHomeButton = findViewById(R.id.returnHomeButton);

        nameTextView.setText(name);
        imageView.setImageResource(imageResId);

        bookButton.setOnClickListener(v ->
                Toast.makeText(this, "Table booked at " + name + "!", Toast.LENGTH_SHORT).show()
        );


        returnHomeButton.setOnClickListener(v -> {

            Intent intent = new Intent(BookingActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Close this activity
        });
    }
}

