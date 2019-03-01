package com.ay3524.contactsapp.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ay3524.contactsapp.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    TextView contactName;
    View itemView;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        contactName = itemView.findViewById(R.id.item_contact_name);
    }
}
