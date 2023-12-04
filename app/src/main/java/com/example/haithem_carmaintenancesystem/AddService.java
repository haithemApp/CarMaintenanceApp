package com.example.haithem_carmaintenancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddService extends AppCompatActivity {
    private Button upload;
    private EditText price, name, time, detail;
    private TextView show;
    private DatabaseReference databaseReference;
    private String u_price, u_name, u_detail, u_time, u_shop;
    private FirebaseAuth firebaseAuth;
    String rating= "no review";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        upload=(Button) findViewById(R.id.upload);

        price= (EditText) findViewById(R.id.price);
        name= (EditText) findViewById(R.id.name);
        time= (EditText) findViewById(R.id.time_taken);
        detail= (EditText) findViewById(R.id.detail);

        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Services").child(user.getUid()).child("All");

                    UserServices userService = new UserServices(u_name, u_price, u_detail, u_time);
                    ref.child(ref.push().getKey()).setValue(userService);


                    Toast.makeText(AddService.this, "Service Successfully Added to Database", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddService.this, HomePage.class));
                    finish();
                }
            }
        });

    }

    private Boolean validate(){
        boolean result= false;

        u_price= price.getText().toString();
        u_detail= detail.getText().toString();
        u_name= name.getText().toString();
        u_time= time.getText().toString();

        if(u_price.isEmpty() || u_detail.isEmpty() || u_name.isEmpty() || u_time.isEmpty() ){
            Toast.makeText(AddService.this, "First add all details of Service", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddService.this, HomePage.class));
        finish();
    }
}