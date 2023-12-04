package com.example.haithem_carmaintenancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateOpen extends AppCompatActivity {

    private static final String TAG = "ImageOpen";
    EditText p_name, p_price, p_time, p_detail;
    ImageView image;
    Button update_service;
    String u_s_name, u_s_price, u_s_time, u_s_detail;
    String imageUrl, imageLoc, imagePrice, imageOwner, imageDetail, key, rating;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    String key_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_open);

        getIncomingIntent();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        update_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateServiceFunction();
            }
        });

    }

    private void UpdateServiceFunction() {
        if (validate()) {
            if (user != null) {
                //update values
                final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Services").child(user.getUid()).child("All").child(key);
                UserServices userService = new UserServices(u_s_name, u_s_price, u_s_detail, u_s_time);
                ref1.setValue(userService);
                startActivity(new Intent(UpdateOpen.this, HomePage.class));
                Toast.makeText(UpdateOpen.this, "Service Details are Updated", Toast.LENGTH_SHORT).show();
                finish();

            }
        }
    }

    private Boolean validate(){
        boolean result= false;

        u_s_name = p_name.getText().toString();
        u_s_price = p_price.getText().toString();
        u_s_time= p_time.getText().toString();
        u_s_detail = p_detail.getText().toString();
        if(u_s_name.isEmpty() || u_s_price.isEmpty() || u_s_time.isEmpty() || u_s_detail.isEmpty()){
            Toast.makeText(this, "Fill every required information", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }
    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if (getIntent().hasExtra("Service_name") && getIntent().hasExtra("Service_price")
                && getIntent().hasExtra("Service_time") && getIntent().hasExtra("Service_detail")
                && getIntent().hasExtra("key")) {

            imageLoc = getIntent().getStringExtra("Service_name");
            imagePrice = getIntent().getStringExtra("Service_price");
            imageOwner = getIntent().getStringExtra("Service_time");
            imageDetail = getIntent().getStringExtra("Service_detail");
            key= getIntent().getStringExtra("key");

            setImage(imageLoc, imagePrice, imageOwner, imageDetail);
            //finish();
        }
    }

    private void setImage(String imageName, String imagePrice, String imageTime, String imageDetail) {
        Log.d(TAG, "setImage: setting te image and name to widgets.");

        p_name = findViewById(R.id.pic_name);
        p_name.setText(imageName);
        p_name.setPaintFlags(p_name.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        p_price = findViewById(R.id.pic_Price);
        p_price.setText(imagePrice);
        p_price.setPaintFlags(p_price.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        p_time = findViewById(R.id.pic_time);
        p_time.setText(imageTime);
        p_time.setPaintFlags(p_time.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        p_detail = findViewById(R.id.pic_detail);
        p_detail.setText(imageDetail);
        p_detail.setPaintFlags(p_detail.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);



        update_service= findViewById(R.id.update_service);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(UpdateOpen.this, HomePage.class));
        finish();
    }
}