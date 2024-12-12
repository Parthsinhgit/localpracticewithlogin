package com.example.loginapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<MyViewHolder1> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter1(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items, parent, false);
        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataimage()).into(holder.recImage);
        holder.recName.setText(dataList.get(position).getDataname());
        holder.recDesc.setText(dataList.get(position).getDatades());
        holder.recPrice.setText(dataList.get(position).getDataprice());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity1.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataimage());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDatades());
                intent.putExtra("Name", dataList.get(holder.getAdapterPosition()).getDataname());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("Price", dataList.get(holder.getAdapterPosition()).getDataprice());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder1 extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recName, recDesc, recPrice;
    CardView recCard;

    public MyViewHolder1(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recPrice = itemView.findViewById(R.id.recPrice);
        recName = itemView.findViewById(R.id.recName);
    }
}
