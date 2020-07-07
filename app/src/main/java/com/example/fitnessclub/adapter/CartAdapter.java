package com.example.fitnessclub.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessclub.R;
import com.example.fitnessclub.api.UserApi;
import com.example.fitnessclub.model.HomeContentModel;
import com.example.fitnessclub.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartContentHolder>{

    Context context;
    List<HomeContentModel> homeContentModelList;


    public CartAdapter(Context context, List<HomeContentModel> homeContentModelList) {
        this.context = context;
        this.homeContentModelList = homeContentModelList;
    }

    @NonNull
    @Override
    public CartContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlayout,
                parent,false);
        return new CartContentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartContentHolder holder, int position) {
        final HomeContentModel homeContentModel = homeContentModelList.get(position);
        Picasso.get().load(Url.imagePath + homeContentModel.getProductimg())
                .centerCrop()
                .resize(1024, 768)
                .into(holder.ibImage);
        holder.tvTitle.setText(homeContentModel.getProductname());
        holder.tvDesc.setText(homeContentModel.getProductdesc());

        holder.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Order Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeContentModelList.size();
    }


    public class CartContentHolder extends RecyclerView.ViewHolder {
        private ImageButton ibImage;
        private TextView tvTitle, tvDesc;
        private Button btnOrder;

        public CartContentHolder(@NonNull View itemView) {
            super(itemView);
            ibImage = itemView.findViewById(R.id.ibCHome);
            tvTitle = itemView.findViewById(R.id.tvCTitle);
            tvDesc = itemView.findViewById(R.id.tvCDesc);
            btnOrder = itemView.findViewById(R.id.btnOrder);
        }
    }
}
