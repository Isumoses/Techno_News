package com.example.techno_news;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.MotionEvent;

public class DeveloperDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_details);

        // Back button
        findViewById(R.id.back_icon).setOnClickListener(v -> finish());

        // Back button
        findViewById(R.id.exit_button).setOnClickListener(v -> finish());

        // Set swipe listener on the root view
        View root = findViewById(android.R.id.content);
        root.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                finish(); // Go back to dashboard
            }
        });

        // Set developer info (replace with real data as needed)
        ((TextView) findViewById(R.id.dev_name)).setText("I W Moses");
        ((TextView) findViewById(R.id.dev_student_no)).setText("2020T00892");
        ((TextView) findViewById(R.id.dev_statement)).setText("I am a motivated young individual with a passion for innovation, striving to develop my skills and achieve meaningful goals that contribute positively to the future.");
        ((TextView) findViewById(R.id.dev_version)).setText("Release Version 1.0");
    }
}
