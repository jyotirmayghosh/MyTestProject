package com.jyotirmayg.mytestproject.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jyotirmayg.mytestproject.base.Application
import com.jyotirmayg.mytestproject.data.repositories.ItemRepository

class MainViewModelFactory(
    private val app: Application
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(app) as T
    }
}