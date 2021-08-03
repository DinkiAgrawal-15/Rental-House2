package com.example.rentalhouse2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;

public class OwnerUploadImageActivity extends AppCompatActivity {
    Button Prev_btn,Next_btn,Upload_btn,Show_btn,Pick_btn;
    EditText Housename_txt;
    ImageSwitcher imageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_upload_image);
        Prev_btn =findViewById(R.id.prev_button);
        Next_btn =findViewById(R.id.next_but);
        Upload_btn =findViewById(R.id.upload_but);
        Show_btn =findViewById(R.id.showall_but);
        Pick_btn =findViewById(R.id.pickimage_but);
        Housename_txt =findViewById(R.id.housename_txt);
        imageSwitcher=findViewById(R.id.imageSwitcher);


    }
}