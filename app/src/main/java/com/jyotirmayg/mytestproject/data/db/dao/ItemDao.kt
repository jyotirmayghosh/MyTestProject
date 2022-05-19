package com.jyotirmayg.mytestproject.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jyotirmayg.mytestproject.data.db.entities.Item

/**
 * @author jyoti
 * @created on 14-05-2022
 * This interface is responsible for querying with database.
 */
@Dao
interface ItemDao {
    /**
     * This suspended function is called to add item to the database.
     * @param item is the data class holding item details.
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(item: Item): Long

    /**
     * This function will be called to get the saved item details.
     * */
    @Transaction
    @Query("SELECT * FROM Item")
    fun getItemList(): LiveData<List<Item>>
}