package com.example.haithem_carmaintenancesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ViewServices extends AppCompatActivity {
    RecyclerView recview;
    ImageAdapterViewService adapter;

    private ProgressBar mProgressCircle;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_services);

        recview = (RecyclerView) findViewById(R.id.recycler_view1);
        recview.setLayoutManager(new LinearLayoutManager(this));

        firebaseAuth= FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle1);

        FirebaseRecyclerOptions<UserServices> options =
                new FirebaseRecyclerOptions.Builder<UserServices>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Services").child(user.getUid()).child("All"), UserServices.class)
                        .build();

        adapter = new ImageAdapterViewService(options);
        recview.setAdapter(adapter);
        mProgressCircle.setVisibility(View.INVISIBLE);


    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewServices.this, HomePage.class));
        finish();
    }
}