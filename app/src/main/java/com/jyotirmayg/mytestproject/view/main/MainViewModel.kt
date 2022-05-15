package com.jyotirmayg.mytestproject.view.main

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
class MainViewModel(app: Application) : AndroidViewModel(app) {

    // for navigation
    private val _navigationFlow = MutableLiveData<Route>()
    val mainNavigationFlow: LiveData<Route> = _navigationFlow

    /**
     * This will be called to route from one fragment to another fragment.
     * @param route where to route.
     * */
    fun routeTo(route: Route) {
        _navigationFlow.postValue(route)
    }
}