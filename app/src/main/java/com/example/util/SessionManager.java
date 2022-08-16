package com.example.util;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.application.MyApplication;

public class SessionManager {

    private static SessionManager ourInstance = new SessionManager();
    private static String APP_PREFERENCE_NAME = "ClaidPref";
    private static String APPPREFS_SETTINGS = "CliaidPref_Settings";
    private static final String USER_ID = "pref_userId";
    private static final String USER_FIRSTNAME = "pref_firstName";
    private static final String USER_LASTNAME = "pref_lastName";
    private static final String USER_EMAIL = "pref_email";
    private static final String USER_MOBILE_NUMBER = "pref_mobileNumber";
    private static final String ACCESS_TOKEN = "pref_access_token";
    private static final String LANGUAGE_KEY = "pref_language_key";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    private static SharedPreferences pref;

    public static SessionManager getInstance() {
        return ourInstance;
    }

    public SessionManager()
    {
        pref = MyApplication.context.getSharedPreferences(APP_PREFERENCE_NAME, Activity.MODE_PRIVATE);
    }

    public void userDetails(String first_name, String last_name, String email,
                            String mobile_number, String accessToken, String userId) {
        pref.edit().putString(USER_FIRSTNAME, first_name).apply();
        pref.edit().putString(USER_LASTNAME, last_name).apply();
        pref.edit().putString(USER_EMAIL, email).apply();
        pref.edit().putString(USER_MOBILE_NUMBER, mobile_number).apply();
        pref.edit().putString(ACCESS_TOKEN, accessToken).apply();
        pref.edit().putString(USER_ID, userId).apply();
    }

    public void setAppLanguageCode(String appLanguageCode) {
        pref.edit().putString(LANGUAGE_KEY, appLanguageCode).apply();
    }

    public String getAppLanguageCode() {
        return pref.getString(LANGUAGE_KEY, "en");
    }

    public String getAccessToken() {
        return pref.getString(ACCESS_TOKEN, "");
    }

    public void Logout() {
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }

    public static void setData(String key, String value)
    {
        pref.edit().putString(key, value).apply();
    }

    public static String getData(String key, String defaultVal){
        return pref.getString(key,defaultVal);
    }

}