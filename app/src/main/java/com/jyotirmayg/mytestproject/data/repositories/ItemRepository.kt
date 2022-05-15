package com.jyotirmayg.mytestproject.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jyotirmayg.mytestproject.data.LoginStatus
import com.jyotirmayg.mytestproject.data.db.AppDatabase
import com.jyotirmayg.mytestproject.data.db.entities.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jyoti
 * @created on 14-05-2022
 * This class is responsible for getting the data from the required layer.
 * It could be from local database, local file or server depending upon the condition/situation.
 */
class ItemRepository(private val db: AppDatabase) {

    suspend fun addItem(item: Item): Long {
        return withContext(Dispatchers.IO) {
            db.itemDao().addItem(item)
        }
    }

    suspend fun getUpdatedItemList(): LiveData<List<Item>> {
        return withContext(Dispatchers.IO) {
            db.itemDao().getItemList()
        }
    }
}