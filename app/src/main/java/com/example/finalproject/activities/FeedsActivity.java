package com.example.finalproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.finalproject.BaseAppCompatActivity;
import com.example.finalproject.FetchFeeds;
import com.example.finalproject.R;
import com.example.finalproject.adapter.FeedsAdapter;
import com.example.finalproject.interfaces.OnNewsFetchedListener;
import com.example.finalproject.interfaces.onNewsClick;
import com.example.finalproject.model.FeedModel;
import com.example.finalproject.room.Dao;
import com.example.finalproject.room.FeedsDatabase;

import org.jsoup.select.Elements;

import java.util.ArrayList;

public class FeedsActivity extends BaseAppCompatActivity implements OnNewsFetchedListener, onNewsClick {
    ProgressBar progressBar;
    String url;
    RecyclerView rv_feeds;
    TextView tv_nodata;
    Dao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        progressBar = findViewById(R.id.progressBar);
        rv_feeds = findViewById(R.id.rv_feeds);
        tv_nodata = findViewById(R.id.tv_nodata);



        url = getIntent().getStringExtra("url");


        // Create an instance of FetchNewsTask and pass this activity as the listener
        new FetchFeeds(this).execute(url);
        // Initializing database and DAO
        FeedsDatabase db = FeedsDatabase.getDatabase(getApplicationContext());
        dao = db.feedDao();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onNewsFetched(Elements elements) {
        if(elements != null){
            progressBar.setVisibility(View.GONE);
            setData(elements);
        }else{
            tv_nodata.setVisibility(View.VISIBLE);
        }


        Log.d("NewsFeedData",""+elements);

    }

    private void setData(Elements elements) {
        ArrayList<FeedModel> feedModelArrayList = new ArrayList<>();

        for(int i=0; i< elements.size(); i++){
            FeedModel model = new FeedModel();
            model.setTitle(elements.get(i).select("title").text());
            model.setDescription(elements.get(i).select("description").text());
            model.setDate(elements.get(i).select("pubDate").text());
            model.setLink(elements.get(i).select("link").text());
            feedModelArrayList.add(model);
        }
        FeedsAdapter adapter = new FeedsAdapter(feedModelArrayList,this);
        rv_feeds.setHasFixedSize(true);
        rv_feeds.setLayoutManager(new LinearLayoutManager(this));
        rv_feeds.setAdapter(adapter);
    }

    @Override
    public void onNewsClick(FeedModel model,boolean isAdd) {
        if(isAdd){
            dao.insertFeed(model);
            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
        }else{
            dao.deleteFeed(model);
            Toast.makeText(getApplicationContext(),"Removed",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();


        if (itemId == R.id.help) {
            showCustomDialog("This is FeedsActivity. In this Screen we load data from url using AsyncTask and display in ListView and also by Clicking it we can saving that data into Room Database,");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }



}