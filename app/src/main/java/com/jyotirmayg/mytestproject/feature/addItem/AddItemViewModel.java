package com.jyotirmayg.mytestproject.feature.addItem;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jyotirmayg.mytestproject.data.db.entities.Item;
import com.jyotirmayg.mytestproject.data.repositories.ItemRepository;

/**
 * @author jyoti
 * @created on 14-05-2022
 */
class AddItemViewModel extends ViewModel {

    private final ItemRepository itemRepository;

    public AddItemViewModel(Context context) {
        itemRepository = ItemRepository.getInstance(context);
    }

    private final MutableLiveData<Long> _addItemFlow = new MutableLiveData();
    LiveData<Long> addItemFlow = _addItemFlow;

    /**
     * This is called to add item to the database.
     * @param item
     */
    public void addItem(Item item) {
        Thread t = new Thread(() -> {
            Long result = itemRepository.addItem(item);
            _addItemFlow.postValue(result);
        });
        t.start();
    }
}
