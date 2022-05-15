package com.jyotirmayg.mytestproject.view.login

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jyotirmayg.mytestproject.R
import com.jyotirmayg.mytestproject.base.Application
import com.jyotirmayg.mytestproject.data.LoginStatus

/**
 * @author jyoti
 * @created on 14-05-2022
 */
class LoginViewModel(private val app: Application) : AndroidViewModel(app) {

    private val _loginLiveData = MutableLiveData<LoginStatus>()
    val loginStatus: LiveData<LoginStatus> = _loginLiveData

    /**
     * This is called from the layout file to initiate login.
     * @param userName holds the username entered by the user.
     * @param password holds the password entered by the user.
     * */
    fun onClickLogin(userName: String, password: String) {
        val loginStatus: LoginStatus = if (userName == "admin" && password == "admin123") {
            LoginStatus(true, app.getString(R.string.login_success))
        } else {
            LoginStatus(false, app.getString(R.string.login_failed))
        }
        _loginLiveData.postValue(loginStatus)
    }
}