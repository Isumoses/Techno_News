package com.example.techno_news;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.MotionEvent;

public class ArticleDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        // Get data from intent (if you want to pass data)
        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String date = getIntent().getStringExtra("date");
        String caption = getIntent().getStringExtra("caption");
        String body = getIntent().getStringExtra("body");
        int imageRes = getIntent().getIntExtra("imageRes", R.drawable.featured_news);

        // Set data to views
        ((TextView) findViewById(R.id.article_title)).setText(title);
        ((TextView) findViewById(R.id.article_author)).setText(author);
        ((TextView) findViewById(R.id.article_date)).setText(date);
        ((TextView) findViewById(R.id.article_caption)).setText(caption);
        ((TextView) findViewById(R.id.article_body)).setText(body);
        ((ImageView) findViewById(R.id.article_image)).setImageResource(imageRes);

        // Back button
        findViewById(R.id.back_icon).setOnClickListener(v -> finish());

        // Set swipe listener on the root view
        View root = findViewById(android.R.id.content);
        root.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                finish(); // Go back to dashboard
            }
        });
    }
}
