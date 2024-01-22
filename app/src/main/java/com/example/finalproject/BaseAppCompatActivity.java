package com.example.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class BaseAppCompatActivity extends AppCompatActivity {
    private static final String PREF_KEY_LANGUAGE = "selected_language";
    private static final String DEFAULT_LANGUAGE = "en"; // Default language if none is selected
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyLanguage();
    }

    protected void applyLanguage() {
        String language = getLanguage();
        updateLocale(language);
    }


    protected String getLanguage() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return preferences.getString(PREF_KEY_LANGUAGE, DEFAULT_LANGUAGE);
    }

    protected void setLanguage(String language) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_KEY_LANGUAGE, language);
        editor.apply();
        updateLocale(language);
    }

    private void updateLocale(String language) {
//        Locale newLocale = new Locale(language);
//        Locale.setDefault(newLocale);
//
//        Configuration configuration = getResources().getConfiguration();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            configuration.setLocale(newLocale);
//        } else {
//            configuration.locale = newLocale;
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            LocaleList localeList = new LocaleList(newLocale);
//            LocaleList.setDefault(localeList);
//            configuration.setLocales(localeList);
//        }
//
//        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());


        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
       // recreate();

    }

    protected void showCustomDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dialog Title")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle positive button click, if needed
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle negative button click, if needed
                    }
                })
                .create()
                .show();
    }
}

