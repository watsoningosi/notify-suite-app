package com.watson.project;

import android.app.Application;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.List;


public class ApplicationClass extends Application {

    public static final String APPLICATION_ID = "508CEBC8-DC38-655A-FF93-4602BCB58300";
    public static final String API_KEY = "0CA5E07E-D2C0-407F-873E-86FE8E16A331";
    public static final String SERVER_URL = "https://api.backendless.com";

    public static BackendlessUser user;
    public static List<Contact> contacts;
    public static List<Notes> notes;

    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );

    }
}
