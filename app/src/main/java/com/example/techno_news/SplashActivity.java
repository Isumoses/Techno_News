package com.example.techno_news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Show splash for 2 seconds, then go to Splash2
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, Splash2Activity.class));
            finish();
        }, 2000);
    }
}
