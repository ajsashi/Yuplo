package com.yuplo.support;

import android.util.Log;

import com.google.gson.JsonObject;
import com.yuplo.view.activity.HomeActivity;

import java.util.ArrayList;

public class Constants {

    public static final String APPLICATION_CONTEXT = "application";
    public static final String ACTIVITY_CONTEXT = "activity";
    public static final String BASE_URL = "http://secure.appinnovativesolutions.com/yuplo/public/api/";
    public static final ArrayList<String> FRAGMENTS = new ArrayList<>();


    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

    //Preference KEY's
    public static final String TOKEN = "token";
    public static final String LOGGED_IN = "logged_in";
    public static final String LOCATION_PERMISSION = "location_permission";
    public static final String STORAGE_PERMISSION = "storage_permission";
    public static final int LOCATION_REQUEST_CODE = 100;
    public static final int STORAGE_REQUEST_CODE = 101;
    public static final int IMAGE_PICKER_REQUEST_CODE = 102;
    public static final HomeActivity.HomeInterface homeInterface = null;

    public static JsonObject getUserLoginJson(String email, String password) {
        JsonObject object = new JsonObject();
        object.addProperty("email", email);
        object.addProperty("password", password);
        Log.d("Json", object.toString());
        return object;
    }

    public static JsonObject getUserSignUpJson(String fName, String lName, String email, String password, String conPassword) {
        JsonObject object = new JsonObject();
        object.addProperty("first_name", fName);
        object.addProperty("last_name", lName);
        object.addProperty("email", email);
        object.addProperty("password", password);
        object.addProperty("c_password", conPassword);
        Log.d("Json", object.toString());
        return object;
    }

    public static String getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    public static String getActivityContext() {
        return ACTIVITY_CONTEXT;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static ArrayList<String> getFRAGMENTS() {
        return FRAGMENTS;
    }

    public static String getPasswordPattern() {
        return PASSWORD_PATTERN;
    }

    public static String getTOKEN() {
        return TOKEN;
    }

    public static String getLoggedIn() {
        return LOGGED_IN;
    }

    public static String getLocationPermission() {
        return LOCATION_PERMISSION;
    }

    public static int getLocationRequestCode() {
        return LOCATION_REQUEST_CODE;
    }

    public static String getStoragePermission() {
        return STORAGE_PERMISSION;
    }

    public static int getStorageRequestCode() {
        return STORAGE_REQUEST_CODE;
    }

    public static int getImagePickerRequestCode() {
        return IMAGE_PICKER_REQUEST_CODE;
    }

}
