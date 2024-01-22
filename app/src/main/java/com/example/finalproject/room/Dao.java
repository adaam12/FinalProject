package com.example.finalproject.room;


import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.finalproject.model.FeedModel;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFeed(FeedModel model);

    @Update
    void updateFeed(FeedModel model);

    @Delete
    void deleteFeed(FeedModel model);

    @Query("SELECT * FROM feed_table")
    LiveData<List<FeedModel>> getAllFeeds();

    @Query("DELETE FROM feed_table WHERE id = :feedId")
    void deleteFeedById(int feedId);
}