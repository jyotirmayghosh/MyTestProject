package com.jyotirmayg.mytestproject.data.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.jyotirmayg.mytestproject.data.db.AppDatabase;
import com.jyotirmayg.mytestproject.data.db.entities.Item;

import java.util.List;

/**
 * @author jyoti
 * @created on 14-05-2022
 * This class is responsible for getting the data from the required layer.
 * It could be from local database, local file or server depending upon the condition/situation.
 */
public class ItemRepository {

    private static AppDatabase db;
    private static ItemRepository instance;

    public static synchronized ItemRepository getInstance(Context context) {
        if (instance == null) {
            instance = new ItemRepository();
            db = AppDatabase.getInstance(context);
        }
        return instance;
    }

    public Long addItem(Item item) {
        return db.itemDao().addItem(item);
    }

    public LiveData<List<Item>> getUpdatedItemList() {
        return db.itemDao().getItemList();
    }
}
