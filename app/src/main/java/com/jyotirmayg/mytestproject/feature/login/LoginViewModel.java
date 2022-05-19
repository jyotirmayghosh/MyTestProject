package com.jyotirmayg.mytestproject.feature.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jyotirmayg.mytestproject.data.LoginStatus;

/**
 * @author jyoti
 * @created on 17-05-2022
 */
public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginStatus> _loginLiveData = new MutableLiveData();
    LiveData<LoginStatus> loginStatus = _loginLiveData;

    /**
     * This is called from the layout file to initiate login.
     *
     * @param userName holds the username entered by the user.
     * @param password holds the password entered by the user.
     */
    void onClickLogin(String userName, String password) {
        LoginStatus loginStatus;
        Boolean status = doLogin(userName, password);
        if (status) {
            loginStatus = new LoginStatus(true, "Login successful");
        } else {
            loginStatus = new LoginStatus(false, "Login failed");
        }
        _loginLiveData.postValue(loginStatus);
    }

    Boolean doLogin(String userName, String password) {
        return userName.equals("admin") && password.equals("admin123");
    }
}
