package com.example.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class BaseFragment extends Fragment {
    private static final String PREF_KEY_LANGUAGE = "selected_language";
    private static final String DEFAULT_LANGUAGE = "en"; // Default language if none is selected
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        applyLanguage();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void applyLanguage() {
        String language = getLanguage();
        updateLocale(language);
    }


    protected String getLanguage() {
        SharedPreferences preferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return preferences.getString(PREF_KEY_LANGUAGE, DEFAULT_LANGUAGE);
    }

    protected void setLanguage(String language) {
        SharedPreferences preferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_KEY_LANGUAGE, language);
        editor.apply();
        updateLocale(language);
    }

    private void updateLocale(String language) {
        if (getActivity() != null) {
//            Context context = getActivity();
//            Locale newLocale = new Locale(language);
//            Locale.setDefault(newLocale);
//
//            Configuration configuration = context.getResources().getConfiguration();
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                configuration.setLocale(newLocale);
//            } else {
//                configuration.locale = newLocale;
//            }
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                LocaleList localeList = new LocaleList(newLocale);
//                LocaleList.setDefault(localeList);
//                configuration.setLocales(localeList);
//            }
//
//            context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
           // getActivity().recreate();
        }
    }
}
