package com.example.healdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Homepage extends AppCompatActivity {
    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;

    private HomeFragment homeFragment;
    private GamesFragment gamesFragment;
    private ExerciseFragment exerciseFragment;
    private GalleryFragment galleryFragment;
    private MoreFragment moreFragment;


    String u_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);



        mainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);

        homeFragment = new HomeFragment();
        gamesFragment = new GamesFragment();
        exerciseFragment = new ExerciseFragment();
        galleryFragment = new GalleryFragment();
        moreFragment = new MoreFragment();
        setFragment(homeFragment);

        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_games:
                        setFragment(gamesFragment);
                        return true;
                    case R.id.nav_exercise:
                        setFragment(exerciseFragment);
                        return true;
                    case R.id.nav_gallery:
                        setFragment(galleryFragment);
                        return true;
                    case R.id.nav_more:
                        setFragment(moreFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }





    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
