package com.example.rentalhouse2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rentalhouse2.adapter.ExampleAdapter;
import com.example.rentalhouse2.model.ExampleModel;

import java.util.ArrayList;
import java.util.List;

public class HouseListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_list);

        recyclerView = findViewById(R.id.recycle_view);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);

        List<ExampleModel> exampleModelList = new ArrayList<>();

        exampleModelList.add(new ExampleModel("https://firebasestorage.googleapis.com/v0/b/rentalhouse-f5ecc.appspot.com/o/Filesggh%2Ffileimage%3A164911?alt=media&token=e548764a-1dfb-423f-a730-75e7a0ea085e","text 1"));
        exampleModelList.add(new ExampleModel("https://firebasestorage.googleapis.com/v0/b/rentalhouse-f5ecc.appspot.com/o/Filesggh%2Ffileimage%3A164911?alt=media&token=e548764a-1dfb-423f-a730-75e7a0ea085e","text 2"));
        exampleModelList.add(new ExampleModel("https://firebasestorage.googleapis.com/v0/b/rentalhouse-f5ecc.appspot.com/o/Filesggh%2Ffileimage%3A164911?alt=media&token=e548764a-1dfb-423f-a730-75e7a0ea085e","text 3"));
        exampleModelList.add(new ExampleModel("https://firebasestorage.googleapis.com/v0/b/rentalhouse-f5ecc.appspot.com/o/Filesggh%2Ffileimage%3A164911?alt=media&token=e548764a-1dfb-423f-a730-75e7a0ea085e","text 4"));
        exampleModelList.add(new ExampleModel("https://firebasestorage.googleapis.com/v0/b/rentalhouse-f5ecc.appspot.com/o/Filesggh%2Ffileimage%3A164911?alt=media&token=e548764a-1dfb-423f-a730-75e7a0ea085e","text 5"));

        ExampleAdapter adapter = new ExampleAdapter(exampleModelList);
        recyclerView.setAdapter(adapter);

    }
}