package com.project.muslim_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.muslim_app.Fragments.HomeFragment;
import com.project.muslim_app.Fragments.MosqueFragment;
import com.project.muslim_app.Fragments.QuranFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //load fragment
        loadFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId())
                {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.quran:
                        fragment = new QuranFragment();
                        break;

                    case R.id.mosque:
                        fragment = new MosqueFragment();
                        break;
                }
                return loadFragment(fragment);
            }
        });
    }
    // method load fragment
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
