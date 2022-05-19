package com.jyotirmayg.mytestproject.feature.main;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.jyotirmayg.mytestproject.data.db.entities.Item;
import com.jyotirmayg.mytestproject.data.repositories.ItemRepository;

import java.util.List;

/**
 * @author jyoti
 * @created on 17-05-2022
 */
public class MainViewModel extends ViewModel {

    private final ItemRepository itemRepository;

    public MainViewModel(Context context) {
        itemRepository = ItemRepository.getInstance(context);
    }

    /**
     * This is called to get updated list from the database.
     */
    public LiveData<List<Item>> getItemList() {
        return itemRepository.getUpdatedItemList();
    }

}
