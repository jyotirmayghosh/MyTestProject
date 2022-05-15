package com.jyotirmayg.mytestproject.view.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jyotirmayg.mytestproject.base.Application
import com.jyotirmayg.mytestproject.data.repositories.ItemRepository

class HomeModelFactory(
    private val app: Application,
    private val itemRepository: ItemRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(app, itemRepository) as T
    }
}