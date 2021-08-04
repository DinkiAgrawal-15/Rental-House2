package com.example.rentalhouse2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OwnerUploadImageActivity extends AppCompatActivity {
    private ImageSwitcher imagesIs;

    Button Prev_btn,Next_btn,Upload_btn,Show_btn,Pick_btn;
    EditText Housename_txt;
    private ArrayList<Uri> imageUris= new ArrayList<Uri>();
    List<String> savedImagesUri;
    CollectionReference reference;
    private int uploads = 0;
    FirebaseStorage storage;
    FirebaseFirestore firestore;
    ProgressDialog progressDialog;
    int position = 0;

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
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Processing Please wait");
        imagesIs=findViewById(R.id.imageSwitcher);
        Pick_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImage();
            }
        });
        imagesIs.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;
            }
        });
        Prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0) {
                    position--;
                    imagesIs.setImageURI(imageUris.get(position));
                } else {
                    Toast.makeText(OwnerUploadImageActivity.this, "No Image", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < imageUris.size() - 1) {
                    position++;
                    imagesIs.setImageURI(imageUris.get(position));
                } else {
                    Toast.makeText(OwnerUploadImageActivity.this, "No Image", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private void PickImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select Image(s)"), 2);

    }


    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                if (data.getClipData() != null) {
                    int cout = data.getClipData().getItemCount();
                    for (int i = 0; i < cout; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        imageUris.add(imageUri);
                        Toast.makeText(OwnerUploadImageActivity.this,"You Have Selected"+imageUris.size()+" Files",Toast.LENGTH_SHORT).show();

                    }
                    imagesIs.setImageURI(imageUris.get(0));
                    position = 0;

                } else {
                    Uri imageUri = data.getData();
                    imageUris.add(imageUri);
                    imagesIs.setImageURI(imageUris.get(0));
                    position = 0;

                }
            }
        }
    }
    public  void uploadFiles(View view){
        String house_name = Housename_txt.getText().toString().trim();

        if (TextUtils.isEmpty(house_name)) {
            Housename_txt.setError("Housename is Required");
            return;
        }else{
            progressDialog.show();
            Toast.makeText(OwnerUploadImageActivity.this,"if Takes time ,You can press again",Toast.LENGTH_SHORT).show();
            for (int j=0;j<imageUris.size();j++){
                Uri perfile=imageUris.get(j);
                StorageReference folder= FirebaseStorage.getInstance().getReference().child("Files"+house_name);
                StorageReference filename=folder.child("file"+ perfile.getLastPathSegment());
                filename.putFile(perfile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                               // DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("USER").child(house_name);
                                HashMap<String,String> hashMap=new HashMap<>();
                                hashMap.put("IMAGE",String.valueOf(uri));
                               // databaseReference.push().setValue(hashMap);
                                progressDialog.dismiss();
                                imageUris.clear();
                            }
                        });
                    }
                });
            }
        }
    }



}
