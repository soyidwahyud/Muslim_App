package com.project.muslim_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.project.muslim_app.Activities.MainActivity;
//import com.project.muslim_app.Login.LoginActivity;

public class Splash_Screen_Activity extends AppCompatActivity {

    private final String TAG = "SPLASH_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen_);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_Screen_Activity.this, MainActivity.class));
            }
        }, 4000);
    }
}
