package com.ay3524.contactsapp.ui.list;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ay3524.contactsapp.Contact;
import com.ay3524.contactsapp.R;

import java.util.List;

public class ContactListActivity extends AppCompatActivity implements ContactContract {

    private static final int CONTACT_PERM = 100;
    private static final String CONTACT_PERM_TEXT = "Contact Permission Required";

    RecyclerView recyclerView;
    TextView textView;
    ProgressBar progressBar;
    ContactListPresenter contactListPresenter;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents() {
        textView = findViewById(R.id.empty_text);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_circular);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        contactAdapter = new ContactAdapter();
        recyclerView.setAdapter(contactAdapter);

        contactListPresenter = new ContactListPresenter(this);

        requestPermissionPrompt(new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_PERM);
    }

    private void requestPermissionPrompt(String[] permissionArray, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionArray, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CONTACT_PERM) {
            if (!(grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(ContactListActivity.this, "Permission needed for Reading Contacts", Toast.LENGTH_SHORT).show();
                onContactPermissionNotGranted();
            } else {
                onContactPermissionGranted();
            }
        }
    }

    private void onContactPermissionNotGranted() {
        recyclerView.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);

        textView.setText(CONTACT_PERM_TEXT);
    }

    private void onContactPermissionGranted() {
        textView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        //TODO initiate fetch contact
        contactListPresenter.fetchContacts();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateContactAdapter(List<Contact> contactList) {
        progressBar.setVisibility(View.GONE);

        contactAdapter.updateContactList(contactList);
    }

    @Override
    public void showError(String message) {
        progressBar.setVisibility(View.GONE);

        recyclerView.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);

        textView.setText(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactListPresenter.presenterCleanUps();
    }
}
