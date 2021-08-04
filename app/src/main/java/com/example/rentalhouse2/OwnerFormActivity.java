    package com.example.rentalhouse2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

    public class OwnerFormActivity extends AppCompatActivity {
    EditText owner_whatsappnumber,owner_phone,owner_address,owner_pincode,owner_city,owner_landmark,owner_rent;
    TextView register_txt;
    RadioButton r_1bhk;
    RadioButton r_sing;
    RadioButton r_2bhk;
    RadioButton r_3bhk;
    RadioButton r_family;
    RadioButton r_bach;
    CheckBox c_none,c_drink,c_smoke;

    FirebaseFirestore firebaseFireStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_form);
        owner_whatsappnumber=findViewById(R.id.whatsapp_number_txt);
        owner_phone=findViewById(R.id.phone_number_txt);
        owner_address=findViewById(R.id.address_txt);
        owner_pincode=findViewById(R.id.pincode_txt);
        owner_city=findViewById(R.id.city_txt);
        owner_landmark=findViewById(R.id.landmark_txt);
        owner_rent=findViewById(R.id.rent_txt);
        register_txt=findViewById(R.id.ownersnap);
        r_sing=findViewById(R.id.single);
        r_1bhk=findViewById(R.id.bhk_1);
        r_2bhk=findViewById(R.id.bhk_2);
        r_3bhk=findViewById(R.id.bhk_3);
        r_family=findViewById(R.id.family);
        r_bach=findViewById(R.id.bach);
        c_none=findViewById(R.id.c1);
        c_drink=findViewById(R.id.c2);
        c_smoke=findViewById(R.id.c3);

        register_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datapush();

            }
        });

    }


    private void datapush() {
        String whp_no=owner_whatsappnumber.getText().toString().trim();
        String w_phone=owner_phone.getText().toString().trim();
        String w_address=owner_address.getText().toString().trim();
        String w_pincode=owner_pincode.getText().toString().trim();
        String w_city=owner_city.getText().toString().trim();
        String w_landmark=owner_landmark.getText().toString().trim();
        String w_rent=owner_rent.getText().toString().trim();

        if (TextUtils.isEmpty(whp_no)){
            owner_whatsappnumber.setError("Enter WhatsApp number");
            return;
        }
        if (TextUtils.isEmpty(w_phone)){
            owner_phone.setError("Enter Phone number");
            return;
        }
        if (TextUtils.isEmpty(w_address)){
            owner_address.setError("Enter Address");
            return;
        }
        if (TextUtils.isEmpty(w_pincode)){
            owner_pincode.setError("Enter Pincode");
            return;
        }
        if (TextUtils.isEmpty(w_city)){
            owner_city.setError("Enter City");
            return;

        }if (TextUtils.isEmpty(w_landmark)){
            owner_landmark.setError("Enter Landmark");
            return;
        }
        if (TextUtils.isEmpty(w_rent)){
            owner_rent.setError("Enter Rent");
            return;

        }


        Map<String, Object> items = new HashMap<>();
        items.put("whp_no",whp_no);
        items.put("phone",w_phone);
        items.put("address",w_address);
        items.put("pincode",w_pincode);
        items.put("city",w_city);
        items.put("landmark",w_landmark);
        items.put("rent",w_rent);





        /*if(r_sing.isChecked()){
            return;
        }else if(r_1bhk.isChecked()){
            r_1bhk.setText("BHK1");
        }else if(r_2bhk.isChecked()){
            r_2bhk.setText("BHK2");
        }else {
            r_3bhk.setText("BHK3");
        }
        if(r_family.isChecked()){
            r_family.setText("Family");
        }else{
            r_bach.setText("Bachlore");
        }
        if(c_none.isChecked()|| c_smoke.isChecked()|| c_drink.isChecked()){
            c_none.setText("None");
            c_drink.setText("Drink");
            c_smoke.setText("Smoking");
        }
        items.put("pref",radpref.getTag().toString().trim());*/


        firebaseFireStore.collection("USERS").document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                .set(items)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        owner_whatsappnumber.setText("");
                        owner_phone.setText("");
                        owner_address.setText("");
                        owner_pincode.setText("");
                        owner_city.setText("");
                        owner_landmark.setText("");
                        owner_rent.setText("");
                        /*r_sing.setText("");
                        r_1bhk.setText("");
                        r_2bhk.setText("");
                        r_3bhk.setText("");
                        r_family.setText("");
                        r_bach.setText("");
                        c_none.setText("");
                        c_drink.setText("");
                        c_smoke.setText("");*/

                        Toast.makeText(getApplicationContext(), "Successfully done", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), OwnerUploadImageActivity.class);
                        startActivity(i);
                    }})
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Log.w("Error", e);
                        }
                    });
        }
    }
