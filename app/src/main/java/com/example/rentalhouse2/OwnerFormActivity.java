    package com.example.rentalhouse2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class OwnerFormActivity extends AppCompatActivity {
    EditText owner_whatsappnumber,owner_phone,owner_address,owner_pincode,owner_city,owner_district,owner_landmark,owner_rent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_form);
        owner_whatsappnumber=findViewById(R.id.whatsapp_number_txt);
        owner_phone=findViewById(R.id.phone_number_txt);
        owner_address=findViewById(R.id.address_txt);
        owner_pincode=findViewById(R.id.pincode_txt);
        owner_city=findViewById(R.id.city_txt);
        owner_district=findViewById(R.id.district_txt);
        owner_landmark=findViewById(R.id.landmark_txt);
        owner_rent=findViewById(R.id.rent_txt);

    }



    public void snapsactivity(View view){
        Intent i = new Intent(this,OwnerUploadImageActivity.class);
        startActivity(i);
    }
}