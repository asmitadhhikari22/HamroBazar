package com.asmit.hamrobazar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asmit.hamrobazar.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Listed_ads_adapter extends RecyclerView.Adapter<Listed_ads_adapter.ListedAdsViewHolder>  {


    Context mContext;
    List<ListedAds> listedAdsList;

    public Listed_ads_adapter(Context mContext,List<ListedAds> listedAdsList){
        this.mContext=mContext;
        this.listedAdsList=listedAdsList;
    }

    @NonNull
    @Override
    public ListedAdsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_listedads,parent,false);
        return new ListedAdsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListedAdsViewHolder holder, int position) {
        ListedAds listedAds=listedAdsList.get(position);
        holder.tvProductType.setText(listedAds.getProducttype());
        holder.tvAdName.setText(listedAds.getAdName());
        holder.tvAdPrice.setText(listedAds.getAdprice());

        String image=listedAds.getImage();
        String imgPath = Url.imagePath + image;
        Picasso.get().load(imgPath).into(holder.imageAd);

    }

    @Override
    public int getItemCount() {
        return listedAdsList.size();
    }

    public class ListedAdsViewHolder extends RecyclerView.ViewHolder{
        ImageView imageAd;
        TextView tvAdName,tvAdPrice,tvProductType;

        public ListedAdsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAd=itemView.findViewById(R.id.imageAd);
            tvAdName=itemView.findViewById(R.id.tvAdName);
            tvAdPrice=itemView.findViewById(R.id.tvAdPrice);
            tvProductType=itemView.findViewById(R.id.tvProductType);
        }
    }

}
