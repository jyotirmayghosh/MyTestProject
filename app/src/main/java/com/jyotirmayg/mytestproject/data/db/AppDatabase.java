package com.jyotirmayg.mytestproject.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jyotirmayg.mytestproject.data.db.dao.ItemDao;
import com.jyotirmayg.mytestproject.data.db.entities.Item;

/**
 * @author jyoti
 * @created on 14-05-2022
 * This class is responsible for creating room database.
 */
@Database(entities = Item.class, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "item_db";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract ItemDao itemDao();
}
