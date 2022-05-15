package com.jyotirmayg.mytestproject.view.main.home

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.jyotirmayg.mytestproject.base.Application
import com.jyotirmayg.mytestproject.data.db.entities.Item
import com.jyotirmayg.mytestproject.data.repositories.ItemRepository
import kotlinx.coroutines.launch

/**
 * @author jyoti
 * @created on 14-05-2022
 */
class HomeViewModel(app: Application, private val itemRepository: ItemRepository) : AndroidViewModel(app) {

    /**
     * This is called to get updated list from the database.
     * */
    suspend fun getItemList() = itemRepository.getUpdatedItemList()
}