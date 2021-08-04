package com.example.rentalhouse2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rentalhouse2.R;
import com.example.rentalhouse2.model.ExampleModel;

import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.viewHolder> {

    private List<ExampleModel> exampleModelList;

    public ExampleAdapter(List<ExampleModel> exampleModelList) {
        this.exampleModelList = exampleModelList;
    }

    @NonNull
    @Override
    public ExampleAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleAdapter.viewHolder holder, int position) {
        holder.setData(exampleModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return exampleModelList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView text;
        private TextView text2;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.ex_image);
            text = itemView.findViewById(R.id.ex_text);
            text2=itemView.findViewById(R.id.ex_text2);

        }

        private void setData(ExampleModel exampleModel) {
           //Glide.with(itemView.getContext()).load(exampleModel.getImage()).into(image);
            text.setText(exampleModel.getText());
            text2.setText(exampleModel.getText1());
        }

    }

}
