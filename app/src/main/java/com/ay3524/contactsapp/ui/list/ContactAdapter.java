package com.ay3524.contactsapp.ui.list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ay3524.contactsapp.Contact;
import com.ay3524.contactsapp.R;
import com.ay3524.contactsapp.ui.detail.ContactDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private List<Contact> contactList = new ArrayList<>();

    private Context context;
    public static final String CONTACT_PASS_KEY = "contact";

    public ContactAdapter() {

    }

    public void updateContactList(List<Contact> contactList) {
        if (!contactList.isEmpty()) {
            this.contactList.clear();
            this.contactList.addAll(contactList);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_contact, viewGroup, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        final Contact contact = contactList.get(i);
        String contactName = contact.getName();

        if (!contactName.isEmpty()) {
            contactViewHolder.contactName.setText(contact.getName());
        }

        contactViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContactDetailActivity.class);
                intent.putExtra(CONTACT_PASS_KEY, contact);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
