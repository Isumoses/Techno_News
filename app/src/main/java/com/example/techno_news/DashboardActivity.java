package com.example.techno_news;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.os.Looper;
import java.util.Arrays;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Handler sliderHandler = new Handler(Looper.getMainLooper());
    private Runnable sliderRunnable;
    private ViewPager2 viewPager;
    private int NUM_PAGES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);

        ImageView menuIcon = findViewById(R.id.menu_icon);
        menuIcon.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        // Set scrim color for dimming effect when drawer is open
        drawerLayout.setScrimColor(0x66000000);

        //Set up Drawer Toggle (hamburger icon)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView profileImage = findViewById(R.id.profile_image);
        profileImage.setOnClickListener(v -> {
            startActivity(new Intent(this, UserDetailsActivity.class));
        });

        List<NewsItem> sportsNews = Arrays.asList(
                new NewsItem(R.drawable.featured_news4, "University of Colombo Triumphs at Sabra Super 7’s Rugby 2025", "Isuru Jayasinghe","In a thrilling display of skill and teamwork, the University of Colombo rugby team clinched the championship title at the Sabra Super 7’s Season II 2025, held on March 8th at Sabaragamuwa University of Sri Lanka. The tournament brought together top university teams from across the country, including University of Peradeniya, University of Moratuwa, and University of Kelaniya.\\n\\nThe Colombo team, led by captain Isuru Jayasinghe, delivered an outstanding performance throughout the tournament, overcoming fierce competition in both the group and knockout stages. The final match saw Colombo edge out University of Moratuwa with a score of 21-17, securing their place at the top.\\n\\nThe victory was celebrated by students, staff, and alumni, highlighting the university’s continued excellence in sports. The event also fostered camaraderie and sportsmanship among all participating universities.","April 1, 2025","University of Colombo rugby team celebrates their victory at Sabra Super 7’s 2025, Sabaragamuwa University."),
                new NewsItem(R.drawable.featured_news, "Sports News 2", "Author B","body","date","caption"),
                new NewsItem(R.drawable.featured_news, "Sports News 3", "Author C","body","date","caption"),
                new NewsItem(R.drawable.featured_news, "Sports News 4", "Author D","body","date","caption")
        );

        List<NewsItem> academicNews = Arrays.asList(
                new NewsItem(R.drawable.featured_news1, "Academic News 1", "Author A","body","date","caption"),
                new NewsItem(R.drawable.featured_news1, "Academic News 2", "Author B","body","date","caption"),
                new NewsItem(R.drawable.featured_news1, "Academic News 3", "Author C","body","date","caption")
        );

        List<NewsItem> eventsNews = Arrays.asList(
                new NewsItem(R.drawable.featured_news2, "Event News 1", "Author A","body","date","caption"),
                new NewsItem(R.drawable.featured_news2, "Event News 2", "Author B","body","date","caption"),
                new NewsItem(R.drawable.featured_news2, "Event News 3", "Author C","body","date","caption"),
                new NewsItem(R.drawable.featured_news2, "Event News 4", "Author D","body","date","caption"),
                new NewsItem(R.drawable.featured_news2, "Event News 5", "Author E","body","date","caption"),
                new NewsItem(R.drawable.featured_news2, "Event News 6", "Author F","body","date","caption")
        );

        RecyclerView newsRecyclerView = findViewById(R.id.newsRecyclerView);
        NewsAdapter newsAdapter = new NewsAdapter(this, new ArrayList<>(sportsNews));
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setAdapter(newsAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        newsAdapter.updateNews(sportsNews);
                        break;
                    case 1:
                        newsAdapter.updateNews(academicNews);
                        break;
                    case 2:
                        newsAdapter.updateNews(eventsNews);
                        break;
                }
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });


        // Set up Drawer and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        // --- Set up the image slider (ViewPager2) and dots indicator ---
        ViewPager2 viewPager = findViewById(R.id.featuredNewsPager);

        // Prepare your news list
        List<FeaturedNews> newsList = new ArrayList<>();
        newsList.add(new FeaturedNews(R.drawable.featured_news1, "Purawara Wasanthaya Aurudu Uthsawaya 2025 Celebrated with Cultural Splendor", "Hiruka Thathsara"));
        newsList.add(new FeaturedNews(R.drawable.featured_news2, "International Dance Day – A Global Tribute to the Art of Movement", "Samoda Perera"));
        newsList.add(new FeaturedNews(R.drawable.featured_news3, "Nana Yathra 1st Edition – Igniting Young Minds Through Arduino & Robotics", "Nithma Peiris"));

        FeaturedNewsPagerAdapter adapter = new FeaturedNewsPagerAdapter(newsList);
        viewPager.setAdapter(adapter);

        // --- Add margin between tab items for pill effect ---
        tabLayout.post(() -> {
            int marginDp = 8;
            float density = tabLayout.getContext().getResources().getDisplayMetrics().density;
            int left = (int) (density * marginDp);
            int right = (int) (density * marginDp);

            ViewGroup tabStrip = (ViewGroup) tabLayout.getChildAt(0);
            for (int i = 0; i < tabStrip.getChildCount(); i++) {
                View tabView = tabStrip.getChildAt(i);
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
                params.setMargins(left, 0, right, 0);
                tabView.setLayoutParams(params);
            }
        });

        // --- Auto swap logic ---
        NUM_PAGES = newsList.size();
        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                int nextItem = (viewPager.getCurrentItem() + 1) % NUM_PAGES;
                viewPager.setCurrentItem(nextItem, true);
                sliderHandler.postDelayed(this, 4000);
            }
        };
        sliderHandler.postDelayed(sliderRunnable, 4000);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 4000);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here
        int id = item.getItemId();
        if (id == R.id.nav_user_details) {
            startActivity(new Intent(this, UserDetailsActivity.class));
        } else if (id == R.id.nav_developer_details) {
            startActivity(new Intent(this, DeveloperDetailsActivity.class));
        //} else if (id == R.id.nav_settings) {
            //Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialog);
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_sign_out, null);
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);

            // Optional: Set slide-down animation for the dialog window
            if (dialog.getWindow() != null) {
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogSlideDownAnimation;
            }

            Button btnOk = dialogView.findViewById(R.id.btn_ok);
            Button btnCancel = dialogView.findViewById(R.id.btn_cancel);

            btnOk.setOnClickListener(view -> {
                // Clear session or user data here if needed

                Intent intent = new Intent(this, Splash2Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

                dialog.dismiss();
            });

            btnCancel.setOnClickListener(view -> dialog.dismiss());

            dialog.show();

            if (dialog.getWindow() != null) {
                int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.9);
                dialog.getWindow().setLayout(width, android.view.WindowManager.LayoutParams.WRAP_CONTENT);}
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
