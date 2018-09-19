package com.example.dell.kishantest.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dell.kishantest.Interface.ItemClickListener;
import com.example.dell.kishantest.R;

public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView to_Name,to_date,to_Msg;
    private ItemClickListener itemClickListener;

    public MessageViewHolder(View itemView) {
        super(itemView);
       to_Name = itemView.findViewById(R.id.to_name);
        to_date = itemView.findViewById(R.id.to_date);
        to_Msg = itemView.findViewById(R.id.to_msg);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;

    }




    @Override
    public void onClick(View v) {

    }
}
