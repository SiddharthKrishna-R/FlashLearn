package com.example.flashlearn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash); // Use the splash screen layout here

        // Navigate to HomeScreenActivity after 2 seconds
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
            startActivity(intent);
            finish(); // Close MainActivity to prevent returning to splash screen
        }, 2000); // 2-second delay
    }
}
