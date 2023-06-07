package com.example.mymovie;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    MovieFragment movieFragment = new MovieFragment();
    AboutFragment aboutFragment = new AboutFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.framefrag, new MovieFragment()).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.movies:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framefrag,movieFragment).commit();
                        return true;
//                    case R.id.tvshows:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.framefrag,tvFragment).commit();
//                        return true;
                    case R.id.about:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framefrag,aboutFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }


}