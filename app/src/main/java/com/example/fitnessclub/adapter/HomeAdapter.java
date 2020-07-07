package com.example.fitnessclub.adapter;

import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessclub.CreateChannel.Channel;
import com.example.fitnessclub.R;
import com.example.fitnessclub.StrictModeClass.StrictMode;
import com.example.fitnessclub.activities.ProductsActivity;
import com.example.fitnessclub.api.UserApi;
import com.example.fitnessclub.model.HomeContentModel;
import com.example.fitnessclub.model.Product;
import com.example.fitnessclub.ui.home.HomeFragment;
import com.example.fitnessclub.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeContentHolder> {
    Context context;
    ProductsActivity context2;
    List<HomeContentModel> homeContentModelList;
    final UserApi userApi = Url.getInstance().create(UserApi.class);
    private NotificationManagerCompat notificationManagerCompat;


    public HomeAdapter(Context context, List<HomeContentModel> homeContentModelList) {
        this.context = context;
        this.homeContentModelList = homeContentModelList;
    }

    @NonNull
    @Override
    public HomeContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homecontent,
                parent, false);
        notificationManagerCompat= NotificationManagerCompat.from(context);
        Channel channel=new Channel(context);
        channel.createChannel();
        return new HomeContentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeContentHolder holder, int position) {
        final HomeContentModel homeContentHolder = homeContentModelList.get(position);
        holder.tvTitle.setText(homeContentHolder.getProductname());
        holder.tvProductDesc.setText(homeContentHolder.getProductdesc());

        Picasso.get().load(Url.imagePath + homeContentHolder.getProductimg())
                .centerCrop()
                .resize(1024, 768)
                .into(holder.ibImage);
        holder.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                notifyaddcart();

                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();

                final Call<HomeContentModel> cartProduct = userApi.addCart(homeContentHolder.getId(), Url.token);
//                StrictMode.StrictMode();
//
//                try{
//                    Response<HomeContentModel> homeContentModelResponse = userApi.addCart(homeContentHolder.getId(), Url.token).execute();
//                    if (!homeContentModelResponse.isSuccessful()){
//                        Toast.makeText(context, "Product not added", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        Toast.makeText(context, "Product added to cart", Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                cartProduct.enqueue(new Callback<HomeContentModel>() {
                    @Override
                    public void onResponse(Call<HomeContentModel> call, Response<HomeContentModel> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(context, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<HomeContentModel> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeContentModelList.size();
    }

    public class HomeContentHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvProductDesc;
        ImageButton ibImage;
        Button btnAddCart;

        public HomeContentHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvHomeTitle);
            ibImage = itemView.findViewById(R.id.ibHome);
            tvProductDesc = itemView.findViewById(R.id.tvHomeDesc);
            btnAddCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }

    private void notifyaddcart() {
        Notification notification=new NotificationCompat.Builder(context, Channel.CHANNEL_1)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_disabled)
                .setContentTitle("Fitness Club")
                .setContentText( "Product added to cart")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(3,notification);
    }
}
