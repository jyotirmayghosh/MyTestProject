package com.jyotirmayg.mytestproject.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jyotirmayg.mytestproject.base.Application

/**
 * @author jyoti
 * @created on 14-05-2022
 */
class LoginViewModelFactory(private val app: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(app) as T
    }
}