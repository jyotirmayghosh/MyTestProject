package com.jyotirmayg.mytestproject.view.main.addItem

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jyotirmayg.mytestproject.base.Application
import com.jyotirmayg.mytestproject.data.db.entities.Item
import com.jyotirmayg.mytestproject.data.repositories.ItemRepository
import kotlinx.coroutines.launch

/**
 * @author jyoti
 * @created on 14-05-2022
 */
class AddItemViewModel(app: Application, private val itemRepository: ItemRepository) :
    AndroidViewModel(app) {

    private var itemName: String? = null
    private var qty: String? = null
    private var rate: String? = null
    private var gst: String? = null

    /**
     * This is called from the layout file to accept item name.
     * @param s get char sequence
     * */
    fun afterItemNameChange(s: CharSequence) {
        itemName = s.toString()
    }

    /**
     * This is called from the layout file to accept quantity.
     * @param s get char sequence
     * */
    fun afterQtyChange(s: CharSequence) {
        qty = s.toString()
    }

    /**
     * This is called from the layout file to accept rate.
     * @param s get char sequence
     * */
    fun afterRateChange(s: CharSequence) {
        rate = s.toString()
    }

    /**
     * This is called from the layout file to accept gst.
     * @param s get char sequence
     * */
    fun afterGstChange(s: CharSequence) {
        gst = s.toString()
    }


    private val _addItemFlow = MutableLiveData<Long>()
    val addItemFlow: LiveData<Long> = _addItemFlow

    /**
     * This is called to add item to the database.
     * */
    fun addItem() {
        viewModelScope.launch {
            val item = Item(itemName = itemName, qty = qty, rate = rate?.toDouble(), gst = gst?.toDouble())
            val result = itemRepository.addItem(item)
            _addItemFlow.postValue(result)
        }
    }
}