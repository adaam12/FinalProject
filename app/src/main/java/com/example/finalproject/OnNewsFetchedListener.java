package com.example.finalproject;

import org.jsoup.select.Elements;

public interface OnNewsFetchedListener {
    void onNewsFetched(Elements jsonData);
}
