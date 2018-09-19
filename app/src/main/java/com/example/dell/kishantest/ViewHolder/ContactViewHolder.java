package com.example.dell.kishantest.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.kishantest.Interface.ItemClickListener;
import com.example.dell.kishantest.R;

public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    public TextView contact_Name,contact_phone;
    public ImageView info;
    private ItemClickListener itemClickListener;
    public ContactViewHolder(View itemView) {
        super(itemView);
        contact_Name = itemView.findViewById(R.id.contact_name);
        contact_phone = itemView.findViewById(R.id.contact_number);
        info = itemView.findViewById(R.id.detail_img);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;

    }



    @Override
    public void onClick(View v) {

    }
}
