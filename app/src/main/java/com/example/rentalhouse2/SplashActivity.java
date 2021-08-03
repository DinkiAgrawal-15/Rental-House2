package com.example.rentalhouse2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Intent i;
                if (user != null) {
                    i = new Intent(SplashActivity.this, MainActivity.class);
                }else{
                    i = new Intent(SplashActivity.this, GoogleSignInActivity.class);
                }
                startActivity(i);
                finish();

            }
        }, 2 * 1000);


    }
}