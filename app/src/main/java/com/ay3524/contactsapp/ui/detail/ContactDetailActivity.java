package com.ay3524.contactsapp.ui.detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ay3524.contactsapp.Contact;
import com.ay3524.contactsapp.R;
import com.ay3524.contactsapp.ui.list.ContactAdapter;

import java.util.Objects;

public class ContactDetailActivity extends AppCompatActivity {

    ImageView contactImageView;
    TextView nameTextView, numberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        contactImageView = findViewById(R.id.image_view);
        nameTextView = findViewById(R.id.name);
        numberTextView = findViewById(R.id.number);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        receiveData();
    }

    private void receiveData() {
        Intent intent = getIntent();
        if (intent.hasExtra(ContactAdapter.CONTACT_PASS_KEY)) {
            Contact contact = intent.getParcelableExtra(ContactAdapter.CONTACT_PASS_KEY);
            if (contact != null) {
                Bitmap bitmap = contact.getPhoto();
                if (bitmap != null) {
                    contactImageView.setImageBitmap(bitmap);
                }
                String contactName = contact.getName();
                if (contactName != null && !contactName.isEmpty()) {
                    nameTextView.setText(contactName);
                }

                String contactNumber = contact.getMobileNumber();
                if (contactNumber != null && !contactNumber.isEmpty()) {
                    numberTextView.setText(contactNumber);
                }
            }
        }
    }
}
