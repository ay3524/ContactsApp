package com.ay3524.contactsapp;

import android.app.Application;
import android.content.Context;

public class ContactApplication extends Application {

    public static Context context;

    public void onCreate() {
        super.onCreate();
        ContactApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ContactApplication.context;
    }
}
