package com.example.finalproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.example.finalproject.BaseAppCompatActivity;
import com.example.finalproject.R;


public class MainActivity extends BaseAppCompatActivity {
    private Button btn_getFeeds, btn_viewFeeds;
    private EditText edt_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        btn_getFeeds = findViewById(R.id.btn_getFeeds);
        btn_viewFeeds = findViewById(R.id.btn_viewFeeds);
        edt_url = findViewById(R.id.edt_url);
        edt_url.setText("http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml");

        btn_getFeeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = edt_url.getText().toString();
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(MainActivity.this, "Please Enter Url", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), FeedsActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);


            }
        });
        btn_viewFeeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SavedFeedActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();


        if (itemId == R.id.help) {
            showCustomDialog("This is Main Screen by using this screen we get url from user and get data and also show saved data.");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}