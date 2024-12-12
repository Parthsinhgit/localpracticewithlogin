package com.example.loginapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class MyAdapter extends  RecyclerView.Adapter<MyViewHolder>  {
    private Context context;
    private List<DataClass> datalist;

    public MyAdapter(Context context,List<DataClass> datalist){
    this.context=context;
    this.datalist= datalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items,parent,false);
        return  new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(datalist.get(position).getDataimage()).into(holder.recImage);
        holder.recName.setText(datalist.get(position).getDataname());
        holder.recDesc.setText(datalist.get(position).getDatades());
        holder.recPrice.setText(datalist.get(position).getDataprice());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Key=datalist.get(holder.getAdapterPosition()).getKey();
                Log.d("MyAdapter", "Key passed to DetailActivity: " + Key);
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("Image",datalist.get(holder.getAdapterPosition()).getDataimage());
                intent.putExtra("Description",datalist.get(holder.getAdapterPosition()).getDatades());
                intent.putExtra("Name",datalist.get(holder.getAdapterPosition()).getDataname());
                intent.putExtra("Key",Key);
                intent.putExtra("Price",datalist.get(holder.getAdapterPosition()).getDataprice());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
    public void searchDataList(ArrayList<DataClass> searchList){
        datalist=searchList;
        notifyDataSetChanged();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recName,recDesc,recPrice;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView){
    super(itemView);

    recImage =itemView.findViewById(R.id.recImage);
    recCard =itemView.findViewById(R.id.recCard);
    recDesc =itemView.findViewById(R.id.recDesc);
    recPrice=itemView.findViewById(R.id.recPrice);
    recName =itemView.findViewById(R.id.recName);
    }
}