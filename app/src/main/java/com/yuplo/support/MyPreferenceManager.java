package com.yuplo.support;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Android on 7/13/2018.
 */

public class MyPreferenceManager {
    private Context context;
    private static MyPreferenceManager mInstance;
    private static SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_FILE_NAME = "MyPreference";



    public MyPreferenceManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public static synchronized MyPreferenceManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyPreferenceManager(context);
        }
        return mInstance;
    }

    public void storeData(String Key, String s) {
        editor.putString(Key, s);
        editor.apply();
        editor.commit();

    }

    public String getData(String Key) {
        return sharedPreferences.getString(Key, null);
    }

    public void storeBoolsData(String Key, boolean s) {
        editor.putBoolean(Key, s);
        editor.apply();
        editor.commit();
    }

    public Boolean getBoolsData(String Key) {
        return sharedPreferences.getBoolean(Key, false);
    }

}


