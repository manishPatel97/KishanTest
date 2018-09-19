package com.example.dell.kishantest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.kishantest.Common.currentValue;
import com.example.dell.kishantest.Model.Contact;
import com.example.dell.kishantest.Model.MessageModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Add_New_Contact extends AppCompatActivity {

    MaterialEditText add_first_name,add_last_name,add_contact_phone;
    Button btn_add_contact;
    FirebaseDatabase database;
    DatabaseReference new_contact;
    private String FIRST_NAME,LAST_NAME,PHONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new__contact);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        new_contact = database.getReference("Contact");
        add_first_name = findViewById(R.id.Add_FirstName);
        add_last_name= findViewById(R.id.Add_LastName);
        add_contact_phone = findViewById(R.id.Add_phone);
        btn_add_contact = findViewById(R.id.add_Contact_Phone);
        btn_add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FIRST_NAME = add_first_name.getText().toString();
                LAST_NAME = add_last_name.getText().toString();
                PHONE = add_contact_phone.getText().toString();
                Contact request = new Contact(FIRST_NAME, LAST_NAME);
                new_contact.child("+91"+PHONE).setValue(request);
                Intent intent = new Intent(Add_New_Contact.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
