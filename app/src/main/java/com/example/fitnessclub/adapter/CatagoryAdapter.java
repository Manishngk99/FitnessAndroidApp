package com.example.fitnessclub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessclub.R;
import com.example.fitnessclub.model.CatagoryModel;
import com.example.fitnessclub.url.Url;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.CatagoryViewHolder>{

    private String type;

    Context context;
    List<CatagoryModel> CategoryList;
    public FragmentManager fragmentManager;

    public CatagoryAdapter(String type, Context context, List<CatagoryModel> categoryList, FragmentManager fragmentManager) {
        this.type = type;
        this.context = context;
        CategoryList = categoryList;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public CatagoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catagory_layout, parent, false);

        return new CatagoryAdapter.CatagoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatagoryViewHolder holder, int position) {
        final CatagoryModel catagoryModel = CategoryList.get(position);
        holder.tvCTitle.setText(catagoryModel.getProductname());
        holder.tvCDesc.setText(catagoryModel.getProductdesc());
        holder.tvCType.setText(catagoryModel.getType());

        Picasso.get().load(Url.imagePath + catagoryModel.getProductimg())
                .centerCrop()
                .resize(1024,768)
                .into(holder.ibCHome);

    }

    @Override
    public int getItemCount() {
        return CategoryList.size();
    }

    public class CatagoryViewHolder extends RecyclerView.ViewHolder {
        ImageButton ibCHome;
        TextView tvCTitle, tvCDesc, tvCType;


        public CatagoryViewHolder(@NonNull View itemView) {
            super(itemView);

           ibCHome = itemView.findViewById(R.id.ibCHome);
           tvCTitle = itemView.findViewById(R.id.tvCTitle);
           tvCDesc = itemView.findViewById(R.id.tvCDesc);
           tvCType = itemView.findViewById(R.id.tvCType);
        }
    }

}
