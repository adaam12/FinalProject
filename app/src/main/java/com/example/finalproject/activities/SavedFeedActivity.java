package com.example.finalproject.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.finalproject.BaseAppCompatActivity;
import com.example.finalproject.R;
import com.example.finalproject.adapter.SavedFeedAdapter;
import com.example.finalproject.interfaces.onNewsClick;
import com.example.finalproject.model.FeedModel;
import com.example.finalproject.room.Dao;
import com.example.finalproject.room.FeedsDatabase;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SavedFeedActivity extends BaseAppCompatActivity implements onNewsClick {
    RecyclerView rv_saved_feeds;
    Dao dao;
    TextView tv_nodata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_feed);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv_nodata = findViewById(R.id.tv_nodata);



        rv_saved_feeds = findViewById(R.id.rv_saved_feeds);

        // Initializing database and DAO
        FeedsDatabase db = FeedsDatabase.getDatabase(getApplicationContext());
        dao = db.feedDao();
        // Observe changes in the contact list and update RecyclerView
        dao.getAllFeeds().observe(this, new Observer<List<FeedModel>>() {
            @Override
            public void onChanged(List<FeedModel> feedModelList) {
                if(feedModelList.size() > 0) {
                    setData(feedModelList);
                }else{
                    tv_nodata.setVisibility(View.VISIBLE);
                    setData(feedModelList);
                }
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private void setData(List<FeedModel> feedModelArrayList) {

        SavedFeedAdapter adapter = new SavedFeedAdapter(feedModelArrayList,this);
        rv_saved_feeds.setHasFixedSize(true);
        rv_saved_feeds.setLayoutManager(new LinearLayoutManager(this));
        rv_saved_feeds.setAdapter(adapter);
    }

    @Override
    public void onNewsClick(FeedModel model, boolean isAdd) {
        dao.deleteFeed(model);
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "News Feed Deleted", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();


        if (itemId == R.id.help) {
            showCustomDialog("This is Saved/Favourites Screen. In this Screen we can get all favorite news feeds and delete them.");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}