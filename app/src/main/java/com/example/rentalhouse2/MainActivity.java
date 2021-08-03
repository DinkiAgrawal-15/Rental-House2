package com.example.rentalhouse2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView name;
    ImageView profileImage;
    Button  btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_logout=findViewById(R.id.btn_logout);
        name=findViewById(R.id.t1);
        profileImage= findViewById(R.id.profileImage);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            name.setText(String.format("Welcome %s", signInAccount.getDisplayName().toUpperCase()));
            try{
                Glide.with(this).load(signInAccount.getPhotoUrl()).into(profileImage);
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
}