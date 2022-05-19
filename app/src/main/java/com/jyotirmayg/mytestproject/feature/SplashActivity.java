package com.jyotirmayg.mytestproject.feature;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.jyotirmayg.mytestproject.R;
import com.jyotirmayg.mytestproject.data.preference.SharePref;
import com.jyotirmayg.mytestproject.feature.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private SharePref sharePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharePref = SharePref.getInstance(this);
        Boolean isFirstTime = sharePref.isFirstTime();

        Timer timer = new Timer("Splash");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                route(isFirstTime);
            }
        }, 3000);

    }

    private void route(Boolean isFirstTime) {
        if (isFirstTime) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            sharePref.saveFirstTime(true);
            startActivity(new Intent(this, FirstTimeActivity.class));
        }
        finish();
    }
}