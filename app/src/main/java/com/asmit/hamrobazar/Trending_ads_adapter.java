package com.asmit.hamrobazar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Trending_ads_adapter extends RecyclerView.Adapter<Trending_ads_adapter.Trending_adsViewHolder> {

    Context mContext;
    List<Trending_ads> trending_adsList;

    public Trending_ads_adapter(Context mContext, List<Trending_ads> trending_adsList) {
        this.mContext = mContext;
        this.trending_adsList = trending_adsList;
    }

    @NonNull
    @Override
    public Trending_adsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_trendingads,parent,false);
        return new Trending_adsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Trending_adsViewHolder holder, int position) {
        Trending_ads treandingAds=trending_adsList.get(position);
        holder.imageProduct.setImageResource(treandingAds.getImageId());
        holder.tvType.setText(treandingAds.getType());
        holder.tvProductName.setText(treandingAds.getName());
        holder.tvPrice.setText(treandingAds.getPrice());
    }

    @Override
    public int getItemCount() {
        return trending_adsList.size();
    }

    public class Trending_adsViewHolder extends RecyclerView.ViewHolder{

        ImageView imageProduct;
        TextView tvProductName,tvPrice,tvType;
        public Trending_adsViewHolder(@NonNull View itemView) {
            super(itemView);

            imageProduct=itemView.findViewById(R.id.imageProduct);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvProductName=itemView.findViewById(R.id.tvProductName);
            tvType=itemView.findViewById(R.id.tvType);
        }
    }

}
