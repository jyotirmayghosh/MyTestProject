package com.jyotirmayg.mytestproject.view.main.addItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jyotirmayg.mytestproject.base.Application
import com.jyotirmayg.mytestproject.data.repositories.ItemRepository

class AddItemModelFactory(
    private val app: Application,
    private val itemRepository: ItemRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddItemViewModel(app, itemRepository) as T
    }
}