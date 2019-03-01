package com.ay3524.contactsapp.helper;

import android.os.AsyncTask;

import com.ay3524.contactsapp.Contact;
import com.ay3524.contactsapp.ContactApplication;

import java.util.List;

public class ContactTask extends AsyncTask<Void, Void, List<Contact>> {

    private IContactFetch contactFetch;

    public ContactTask(IContactFetch contactFetch) {
        this.contactFetch = contactFetch;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Contact> doInBackground(Void... voids) {
        return ContactHelper.getContactList(ContactApplication.getAppContext());
    }

    @Override
    protected void onPostExecute(List<Contact> contactList) {
        super.onPostExecute(contactList);
        if (contactList != null && !contactList.isEmpty()) {
            contactFetch.onContactFetchSuccess(contactList);
        } else {
            contactFetch.onContactFetchError("Error while fetching contacts!");
        }
    }
}
