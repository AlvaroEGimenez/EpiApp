package com.example.epiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.HouseViewHolder> {

    private List<House> houseList;


    public HouseAdapter(List<House> houseList) {
        this.houseList = houseList;
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address,parent,false);
        return new HouseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, int position) {
        holder.textViewHouseDescription.setText(houseList.get(position).getDescription());
        holder.textViewHouseVisits.setText(String.valueOf(houseList.get(position).getVisits()));

    }

    @Override
    public int getItemCount() {
        return houseList.size();
    }

    class HouseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_houseDescription)
        TextView textViewHouseDescription;
        @BindView(R.id.textView_houseVisits)
        TextView textViewHouseVisits;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
