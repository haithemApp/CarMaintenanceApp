package com.example.haithem_carmaintenancesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateService extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageAdapterUpdateService mAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private ProgressBar mProgressCircle;
    String key;
    String im_name, im_price, im_time, im_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_service);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle2);

        FirebaseRecyclerOptions<UserServices> options =
                new FirebaseRecyclerOptions.Builder<UserServices>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Services").child(user.getUid()).child("All"), UserServices.class)
                        .build();

        mAdapter = new ImageAdapterUpdateService(options);
        mRecyclerView.setAdapter(mAdapter);
        mProgressCircle.setVisibility(View.INVISIBLE);

        mAdapter.setOnItemClickListener(new ImageAdapterUpdateService.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                key= mAdapter.getRef(position).getKey();
                UserServices selectedItem= options.getSnapshots().get(position);
                im_name = selectedItem.getSerName();
                im_price = selectedItem.getSerPrice();
                im_time= selectedItem.getSerTime();
                im_detail= selectedItem.getSerDetail();

                Intent intent = new Intent(UpdateService.this, UpdateOpen.class);
                intent.putExtra("Service_name", im_name);
                intent.putExtra("Service_price", im_price);
                intent.putExtra("Service_time", im_time);
                intent.putExtra("Service_detail", im_detail);
                intent.putExtra("key", key);
                startActivity(intent);
                finish();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(UpdateService.this, HomePage.class));
        finish();


    }
}