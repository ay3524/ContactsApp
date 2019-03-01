package com.ay3524.contactsapp.ui.list;

import com.ay3524.contactsapp.Contact;
import com.ay3524.contactsapp.helper.ContactTask;
import com.ay3524.contactsapp.helper.IContactFetch;

import java.util.List;

public class ContactListPresenter implements IContactFetch {

    private ContactTask contactTask;
    private ContactContract contactContract;

    public ContactListPresenter(ContactContract contactContract) {
        this.contactContract = contactContract;
    }

    public void fetchContacts() {
        contactTask = new ContactTask(this);
        contactTask.execute();
    }

    @Override
    public void onContactFetchSuccess(List<Contact> contactList) {
        contactContract.updateContactAdapter(contactList);
    }

    @Override
    public void onContactFetchError(String message) {
        contactContract.showError(message);
    }

    public void presenterCleanUps() {
        if (contactTask != null) {
            contactTask.cancel(true);
        }
    }
}
