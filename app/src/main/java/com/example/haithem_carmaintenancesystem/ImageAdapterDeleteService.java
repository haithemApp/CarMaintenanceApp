package com.example.haithem_carmaintenancesystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ImageAdapterDeleteService extends FirebaseRecyclerAdapter<UserServices, ImageAdapterDeleteService.ImageViewHolder> {
    private OnItemClickListener mListener;

    public ImageAdapterDeleteService(@NonNull FirebaseRecyclerOptions<UserServices> options) {
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
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_delete_service, parent, false);
        return new ImageViewHolder(v);
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textViewName;
        public TextView textViewPrice;
        public TextView textViewDetail;
        public TextView textViewTime;

        public Button btn_delete;



        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name3);
            textViewPrice= itemView.findViewById(R.id.image_view_price3);
            textViewTime= itemView.findViewById(R.id.image_view_time3);
            textViewDetail= itemView.findViewById(R.id.image_view_detail3);
            btn_delete= itemView.findViewById(R.id.delete_ser);

            btn_delete.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }

    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener= listener;
    }
}
