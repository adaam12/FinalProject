package com.example.finalproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.finalproject.BaseFragment;
import com.example.finalproject.R;
import com.example.finalproject.activities.HomeActivity;


public class LanguageFragment extends BaseFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_language, container, false);

        Button btnCanadianFrench = root.findViewById(R.id.btnCanadianFrench);

        Button btnEnglish = root.findViewById(R.id.btnEnglish);

        btnCanadianFrench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("fr");
                Intent intent = new Intent(requireContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("en");
                Intent intent = new Intent(requireContext(), HomeActivity.class);
                startActivity(intent);
            }
        });



        return root;
    }

}
