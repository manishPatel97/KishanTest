package com.example.dell.kishantest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.dell.kishantest.Common.currentValue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class contact_info extends AppCompatActivity {

    TextView first_name,last_name,phone_number;
    String FIRST_NAME,LAST_NAME,PHONE,Order;
    int ordernum;

    Button msg_sent;
    FirebaseDatabase database;
    DatabaseReference request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        first_name = findViewById(R.id.contact_detail_first_name);
        last_name = findViewById(R.id.contact_detail_last_name);
        phone_number= findViewById(R.id.contact_detail_phone);
        msg_sent =findViewById(R.id.message_send_btn);
        database = FirebaseDatabase.getInstance();
        request = database.getReference("Contact");
        if(getIntent()!=null){
            FIRST_NAME = getIntent().getStringExtra("First_Name");
            LAST_NAME = getIntent().getStringExtra("Last_name");
            PHONE = getIntent().getStringExtra("phone");


        }
        first_name.setText(FIRST_NAME);
        last_name.setText(LAST_NAME);
        phone_number.setText(PHONE);
        msg_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query =  FirebaseDatabase.getInstance().getReference().child("Messages").orderByChild("timestamp").limitToFirst(1);

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                                Order = postsnapshot.child("timestamp").getValue().toString();
                                currentValue.Order = Integer.valueOf(Order);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent intent =new Intent(contact_info.this,compose_Message.class);
                intent.putExtra("First_Name",FIRST_NAME);
                intent.putExtra("Last_name",LAST_NAME);
                intent.putExtra("phone",PHONE);
                startActivity(intent);

            }
        });
    }
}
