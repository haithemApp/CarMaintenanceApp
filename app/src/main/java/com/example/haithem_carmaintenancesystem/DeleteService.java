package com.example.haithem_carmaintenancesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteService extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageAdapterDeleteService mAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private ProgressBar mProgressCircle;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view3);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle3);

        FirebaseRecyclerOptions<UserServices> options =
                new FirebaseRecyclerOptions.Builder<UserServices>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Services").child(user.getUid()).child("All"), UserServices.class)
                        .build();

        mAdapter = new ImageAdapterDeleteService(options);
        mRecyclerView.setAdapter(mAdapter);
        mProgressCircle.setVisibility(View.INVISIBLE);


        mAdapter.setOnItemClickListener(new ImageAdapterDeleteService.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                key= mAdapter.getRef(position).getKey();
                deleteAssignmentFunction();

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

    public void deleteAssignmentFunction() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                DeleteService.this);
        // Setting Dialog Title
        alertDialog.setTitle("Delete Assignment");
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to delete this Assignment?");
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.logo);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Services").child(user.getUid()).child("All").child(key);
                        ref.getRef().removeValue();
                        startActivity(new Intent(DeleteService.this, DeleteService.class));
                        Toast.makeText(DeleteService.this, "Service is Deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DeleteService.this, HomePage.class));
        finish();
    }
}