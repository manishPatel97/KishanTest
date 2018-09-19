package com.example.dell.kishantest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.kishantest.Interface.ItemClickListener;
import com.example.dell.kishantest.Model.Contact;
import com.example.dell.kishantest.Model.MessageModel;
import com.example.dell.kishantest.ViewHolder.ContactViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class TabContacts extends Fragment{
    private EditText add_First_Name,add_Last_Name,add_Phone;

    public TabContacts(){}

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FloatingActionButton fab;
    String first_name,last_name,contact_phone;
    View rootView;
    FirebaseDatabase database;
    DatabaseReference request;
    FirebaseRecyclerAdapter<Contact,ContactViewHolder> adapter;
    FragmentActivity c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.tab_contacts, container, false);
         c = getActivity();
        database = FirebaseDatabase.getInstance();
        request = database.getReference("Contact");
        recyclerView =rootView.findViewById(R.id.contact_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        fab = rootView.findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   // showAddfoodDialog();
                Intent intent = new Intent(c,Add_New_Contact.class);
                startActivity(intent);
            }
        });

        loadContact(rootView);

        return rootView;
    }


    public void loadContact(View view){
        Query query = FirebaseDatabase.getInstance().getReference().child("Contact");
        //System.out.println("Category query "+ query);
        FirebaseRecyclerOptions<Contact> options = new FirebaseRecyclerOptions.Builder<Contact>().setQuery(query,Contact.class).build();
        adapter = new FirebaseRecyclerAdapter<Contact, ContactViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ContactViewHolder holder, int position, @NonNull Contact model) {
                holder.contact_Name.setText(model.getFirstname()+" " +model.getLastname());
                model.setPhone(adapter.getRef(position).getKey());
                holder.contact_phone.setText(model.getPhone());
                final Contact clickitem = model;
                holder.info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Intent intent = new Intent(c,contact_info.class);
                       intent.putExtra("First_Name",clickitem.getFirstname());
                       intent.putExtra("Last_name",clickitem.getLastname());
                       intent.putExtra("phone",clickitem.getPhone());
                       startActivity(intent);
                    }
                });

               /*holder.setItemClickListener( new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        System.out.println("inside menu");
                        Toast.makeText(c,"touch succesfful",Toast.LENGTH_SHORT).show();
                        //Intent foodintent = new Intent(Home.this,FoodList.class);
                       // foodintent.putExtra("CategoryID",adapter.getRef(position).getKey());
                       // startActivity(foodintent);

                    }

                });*/

            }

            @NonNull
            @Override
            public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout,parent,false);
                final ContactViewHolder cHolder = new ContactViewHolder(view);
                cHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        System.out.println("inside menu");
                        Toast.makeText(c,"touch succesfful",Toast.LENGTH_SHORT).show();
                    }
                });
                return new ContactViewHolder(view);
            }
        };
        adapter.notifyDataSetChanged();//refresh layout if data changed
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
