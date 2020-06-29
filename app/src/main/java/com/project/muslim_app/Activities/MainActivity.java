package com.project.muslim_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.muslim_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
//                        Intent h = new Intent(MainActivity.this, MainActivity.class);
//                        startActivity(h);
                        break;

                    case R.id.mosque:
                        Intent m = new Intent(MainActivity.this, MosqueActivity.class);
                        startActivity(m);
                        break;

                    case R.id.about:
                        Intent a = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(a);
                        break;
                }
                return false;
            }
        });
    }

}
