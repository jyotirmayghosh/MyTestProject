package com.jyotirmayg.mytestproject.feature;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jyotirmayg.mytestproject.R;
import com.jyotirmayg.mytestproject.databinding.ActivityFirstTimeBinding;
import com.jyotirmayg.mytestproject.feature.login.LoginActivity;

public class FirstTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityFirstTimeBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_first_time);
        mBinding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}