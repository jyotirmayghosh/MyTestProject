package com.jyotirmayg.mytestproject.data.preference;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author jyoti
 * @created on 20-05-2022
 */
public class SharePref {

    private static SharePref instance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor;

    private final String IS_FIRST_TIME = "first_time";

    public static synchronized SharePref getInstance(Context context) {
        if (instance == null) {
            String PREFERENCE_NAME = "MY_TEST";
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
            sharedPreferencesEditor = sharedPreferences.edit();
            instance = new SharePref();
        }
        return instance;
    }

    public void saveFirstTime(Boolean flag) {
        sharedPreferencesEditor.putBoolean(IS_FIRST_TIME, flag);
        sharedPreferencesEditor.commit();
    }

    public Boolean isFirstTime() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME, false);
    }
}
