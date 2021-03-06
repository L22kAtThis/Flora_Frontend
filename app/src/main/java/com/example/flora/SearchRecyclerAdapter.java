package com.example.flora;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder> {

    private ArrayList<SearchItem> mSearchList;

    @NonNull
    @NotNull
    @Override
    public SearchRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mSearchList.get(position));
    }

    public void setSearchList(ArrayList<SearchItem> list){
        this.mSearchList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mSearchList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView flowerImage;
        ImageView profileImage;
        TextView titleTv;
        TextView contextTv;
        TextView priceTv;
        TextView discountTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            flowerImage = (ImageView) itemView.findViewById(R.id.flowerImage);
            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            contextTv = (TextView) itemView.findViewById(R.id.contextTv);
            priceTv = (TextView) itemView.findViewById(R.id.priceTv);
            discountTv = (TextView) itemView.findViewById(R.id.discountTv);
        }

        void onBind(SearchItem item){
            flowerImage.setImageResource(item.getResourceFlowerId());
            profileImage.setImageResource(item.getResourceProfileId());
            titleTv.setText(item.getTitle());
            contextTv.setText(item.getContext());
            priceTv.setText(item.getPrice());
            discountTv.setText(item.getDiscount());
        }
    }

}
