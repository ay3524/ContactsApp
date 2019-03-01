package com.ay3524.contactsapp.helper;

import com.ay3524.contactsapp.Contact;

import java.util.List;

public interface IContactFetch {
    void onContactFetchSuccess(List<Contact> contactList);

    void onContactFetchError(String message);
}
