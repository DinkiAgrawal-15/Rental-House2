package com.example.rentalhouse2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TanentFormActivity<Public> extends AppCompatActivity {

    EditText tanent_name, tanet_phone, tanent_occup, tanent_address, tanent_city, tanent_pincode;
    Button but_reg;
    FirebaseFirestore firebaseFireStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanent_form);

        tanent_name = findViewById(R.id.tanent_name);
        tanet_phone = findViewById(R.id.tanent_phone);
        tanent_occup = findViewById(R.id.tanent_occupation);
        tanent_address = findViewById(R.id.tanent_address);
        tanent_city = findViewById(R.id.tanent_city);
        tanent_pincode = findViewById(R.id.pincode_txt);

        but_reg = findViewById(R.id.btn_register);


        but_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertdata();
            }
        });
    }

    public void insertdata() {
        Map<String, Object> items = new HashMap<>();
        items.put("Name", tanent_name.getText().toString().trim());
        items.put("Phonenumber", tanet_phone.getText().toString());
        items.put("Occupation", tanent_occup.getText().toString().trim());
        items.put("Address", tanent_address.getText().toString().trim());
        items.put("City", tanent_city.getText().toString().trim());
        items.put("Pincode", tanent_pincode.getText().toString().trim());


        firebaseFireStore.collection("Tanent").document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                //.orderBy("Phonenumber")
                .set(items)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        tanent_name.setText("");
                        tanet_phone.setText("");
                        tanent_occup.setText("");
                        tanent_address.setText("");
                        tanent_city.setText("");
                        tanent_pincode.setText("");

                        Toast.makeText(getApplicationContext(), "Succesfully done", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), HouseListActivity.class);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Log.w("Error", e);
                    }
                });
    }

   /* public void check(){
        if (!TextUtils.isEmpty(tanent_name.getText())){
            if (!TextUtils.isEmpty(tanet_phone.getText())){
                if (!TextUtils.isEmpty(tanent_occup.getText())){
                    if (!TextUtils.isEmpty(tanent_address.getText())){
                        if (!TextUtils.isEmpty(tanent_city.getText())){
                            if (!TextUtils.isEmpty(tanent_pincode.getText())){
                                but_reg.setEnabled(true);
                                but_reg.setTextColor(Color.rgb(255, 255, 255));
                            }else {
                                but_reg.setEnabled(false);
                                but_reg.setTextColor(Color.argb(50, 255, 255, 255)); }
                        }else {
                            but_reg.setEnabled(false);
                            but_reg.setTextColor(Color.argb(50, 255, 255, 255));
                        }
                    }else {
                        but_reg.setEnabled(false);
                        but_reg.setTextColor(Color.argb(50, 255, 255, 255));
                    }
                }else{
                    but_reg.setEnabled(false);
                    but_reg.setTextColor(Color.argb(50, 255, 255, 255));
                }
            }else{
                but_reg.setEnabled(false);
                but_reg.setTextColor(Color.argb(50, 255, 255, 255));
            }
        }else{
            but_reg.setEnabled(false);
            but_reg.setTextColor(Color.argb(50, 255, 255, 255));
        }
    }*/


}