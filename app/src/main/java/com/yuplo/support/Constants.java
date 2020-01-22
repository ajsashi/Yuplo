package com.yuplo.support;

import android.util.Log;

import com.google.gson.JsonObject;

public class Constants {

    public static final String APPLICATION_CONTEXT = "application";
    public static final String ACTIVITY_CONTEXT = "activity";
    public static final String BASE_URL = " http://192.168.88.23/yuplo/public/api/";


    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

    //Preference KEY's
    public static final String TOKEN = "token";

    public static JsonObject getUserLoginJson(String email, String password) {
        JsonObject object = new JsonObject();
        object.addProperty("email", email);
        object.addProperty("password", password);
        Log.d("Json",object.toString());
        return object;
    }

    public static JsonObject getUserSignUpJson(String fName,String lName,String email, String password, String conPassword) {
        JsonObject object = new JsonObject();
        object.addProperty("first_name", fName);
        object.addProperty("last_name", lName);
        object.addProperty("email", email);
        object.addProperty("password", password);
        object.addProperty("c_password", conPassword);
        Log.d("Json",object.toString());
        return object;
    }
}
