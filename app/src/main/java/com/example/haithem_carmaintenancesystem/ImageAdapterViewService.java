package com.example.haithem_carmaintenancesystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ImageAdapterViewService extends FirebaseRecyclerAdapter<UserServices, ImageAdapterViewService.ImageViewHolder> {

    public ImageAdapterViewService(@NonNull FirebaseRecyclerOptions<UserServices> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull ImageViewHolder holder, int position, @NonNull UserServices model) {
        holder.textViewName.setText("Service Name: "+model.getSerName());
        holder.textViewPrice.setText("Service Price: "+model.getSerPrice()+" OMR");
        holder.textViewTime.setText("Service Time:  "+model.getSerTime());
        holder.textViewDetail.setText("Service Details: "+model.getSerDetail());

    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_view_service, parent, false);
        return new ImageViewHolder(v);
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewPrice;
        public TextView textViewDetail;
        public TextView textViewTime;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name1);
            textViewPrice= itemView.findViewById(R.id.image_view_price1);
            textViewTime= itemView.findViewById(R.id.image_view_time1);
            textViewDetail= itemView.findViewById(R.id.image_view_detail1);
        }
    }
}
