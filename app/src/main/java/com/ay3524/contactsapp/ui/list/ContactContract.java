package com.ay3524.contactsapp.ui.list;

import com.ay3524.contactsapp.Contact;

import java.util.List;

public interface ContactContract {

    void updateContactAdapter(List<Contact> contactList);

    void showError(String message);

}
