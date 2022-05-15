package com.jyotirmayg.mytestproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jyotirmayg.mytestproject.data.db.dao.ItemDao
import com.jyotirmayg.mytestproject.data.db.entities.Item

/**
 * @author jyoti
 * @created on 14-05-2022
 * This class is responsible for creating room database.
 */
@Database(entities = [Item::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    /**
     * This created a singleton object of the AppDatabase class.
     * And creates the database.
     * */
    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "App.db"
            ).build()
    }
}