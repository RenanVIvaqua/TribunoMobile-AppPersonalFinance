package com.example.tribunomobile.view.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.tribunomobile.service.repository.local.user.UserRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val mLogin = MutableLiveData<Boolean>()
    val login = mLogin

    private var userRepository: UserRepository =
        UserRepository(
            application
        )

    fun doLogin(userName: String, password: String){

        var user = userRepository.doLogin(userName, password)

        mLogin.value = !(user == null || user.id <= 0 || !user.active)
    }

}