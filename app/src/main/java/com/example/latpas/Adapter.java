package com.example.latpas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;

    ArrayList<Model> model;


    public Adapter(Context context, ArrayList<Model> model){
        this.context = context;
        this.model = model;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView gambar;
        TextView nama;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            gambar = itemView.findViewById(R.id.imgTim);
            nama = itemView.findViewById(R.id.tvNamaTim);

        }





    }
    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        Model items = this.model.get(position);

        holder.nama.setText(model.get(position).getNama());


        Glide.with(holder.itemView.getContext())
                .load(items.getGambar())
                .into(holder.gambar);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }
}
