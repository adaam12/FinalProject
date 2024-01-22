package com.example.finalproject;

import android.os.AsyncTask;
import android.util.Log;


import com.example.finalproject.interfaces.OnNewsFetchedListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class FetchFeeds extends AsyncTask<String, Void, Elements> {

    private OnNewsFetchedListener listener;

    public FetchFeeds(OnNewsFetchedListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        // You can perform any setup tasks here before starting the background task
    }

    @Override
    protected Elements doInBackground(String... urls) {

        if(urls.length == 0){
            return null;
        }
        try {
            String feedUrl = urls[0];
            Document doc = Jsoup.connect(feedUrl).get();
            Elements items = doc.select("item");
            return items;

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("FetchFeeds", "Error during data fetch", e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Elements jsonData) {
        if (listener != null) {
            listener.onNewsFetched(jsonData);
        }
    }
}

