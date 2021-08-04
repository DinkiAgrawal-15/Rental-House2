package com.example.rentalhouse2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    TextView name;
    ImageView profileImage;
    Button  btn_logout;

    private Dialog noInternetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_logout=findViewById(R.id.btn_logout);
        name=findViewById(R.id.t1);
        profileImage= findViewById(R.id.profileImage);


        //........................... no internet connection layout start ............................//
        noInternetDialog = new Dialog(this);
        noInternetDialog.setContentView(R.layout.dialog_no_internet_connection);
        noInternetDialog.setCancelable(false);
        noInternetDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
       // noInternetDialog.getWindow().setBackgroundDrawableResource(R.drawable.solid_1);
        ImageView noInternetImage = noInternetDialog.findViewById(R.id.no_internet_image);
      //  Glide.with(getApplicationContext()).load(R.drawable.meter).into(noInternetImage);
        checkInternetConnection(1000);
        //........................... no internet connection layout end ............................//


        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            name.setText(String.format("Welcome %s", signInAccount.getDisplayName().toUpperCase()));
            try{
               // Glide.with(this).load(signInAccount.getPhotoUrl()).into(profileImage);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
            }
        }
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(),"Loggedout Successfully",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),GoogleSignInActivity.class);
                startActivity(intent);
            }
        });

    }

    public void owner(View view) {
        Intent i = new Intent(this,OwnerFormActivity.class);
        startActivity(i);
    }

    public void tanent(View view) {
        Intent i = new Intent(this,TanentFormActivity.class);
        startActivity(i);
    }

    private void checkInternetConnection(int delay) {


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (networkInfo != null && networkInfo.isConnected()) {
                    noInternetDialog.dismiss();
                } else {
                    noInternetDialog.show();
                }
                checkInternetConnection(1000);
            }
        }, delay);
    }
}