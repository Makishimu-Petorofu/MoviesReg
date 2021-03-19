package com.example.registration.ui.fav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.registration.Flower;
import com.example.registration.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavAdapter {
    TextView nameTextView;
    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";
    private List<Flower> mFlowers;
    private Context mContext;

    FavAdapter(List<Flower> flowers) {
        this.mFlowers = flowers;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(ViewHolder holder, int position) {

        Flower flower = mFlowers.get(position);


        Picasso.with(mContext)
                .load(PHOTO_URL + flower.getPoster())
                .into(holder.flowerImageView);
        holder.nameTextView.setText(flower.getName());
        holder.Description.setText(flower.getDescription());
    }

    public int getItemCount() {
        if (mFlowers == null) {
            return 0;
        }
        return mFlowers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, Description;
        ImageView flowerImageView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            flowerImageView = (ImageView) itemView.findViewById(R.id.itemImageView);
            Description = (TextView) itemView.findViewById(R.id.tvDescription);
        }
    }
}
