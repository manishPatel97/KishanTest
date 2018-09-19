package com.example.dell.kishantest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.kishantest.Interface.ItemClickListener;
import com.example.dell.kishantest.Model.MessageModel;
import com.example.dell.kishantest.ViewHolder.MessageViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class TabMessage extends Fragment {
    View rootView;

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;


    FirebaseDatabase database;
    DatabaseReference request;
    FirebaseRecyclerAdapter<MessageModel,MessageViewHolder> adapter;
    FragmentActivity c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_message, container, false);
        // TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        database = FirebaseDatabase.getInstance();
        request = database.getReference("Messages");
        recyclerView =rootView.findViewById(R.id.message_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(c);



        recyclerView.setLayoutManager(layoutManager);

        loadMessage();
        return rootView;
    }

    private void loadMessage() {
        Query query = FirebaseDatabase.getInstance().getReference().child("Messages").orderByChild("timestamp");
        FirebaseRecyclerOptions<MessageModel> options = new FirebaseRecyclerOptions.Builder<MessageModel>().setQuery(query,MessageModel.class).build();
        adapter = new FirebaseRecyclerAdapter<MessageModel, MessageViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull MessageModel model) {
                holder.to_Name.setText(model.getFirst_Name()+ " "+model.getLast_Name());
                holder.to_date.setText(model.getDate());
                holder.to_Msg.setText(model.getMessage());
            }
            @NonNull
            @Override
            public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout,parent,false);
                final MessageViewHolder cHolder = new MessageViewHolder(view);
                cHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        System.out.println("inside menu");
                        Toast.makeText(c,"touch succesfful",Toast.LENGTH_SHORT).show();
                    }
                });
                return new MessageViewHolder(view);
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

