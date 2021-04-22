package com.example.tribunomobile.view.ui.registerUser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.tribunomobile.service.enum.ErrorUser
import com.example.tribunomobile.service.model.UserModel
import com.example.tribunomobile.service.repository.local.user.UserRepository
import com.example.tribunomobile.service.util.Validation.validationAddressEmail

class RegisterViewModel(application: Application): AndroidViewModel(application) {

    private var mCreateAccount = MutableLiveData<UserValidate>()
    var createAccount = mCreateAccount

    private var validationUser = UserValidate()

    private var userRepository = UserRepository(application)

    fun validateAndCreateAccount(userModel: UserModel){

        validationUser = validateUserModel(userModel)

        if(!validationUser.hasError)
            validationUser = saveUser(userModel)

        mCreateAccount.value = validationUser
    }

    private fun saveUser(userModel: UserModel): UserValidate {
        if(!userRepository.save(userModel))
            return  validationUser.applyError(ErrorUser.ErrorRecord)

        return  validationUser
    }

    private fun validateUserModel(userModel: UserModel): UserValidate {

        if(!validationAddressEmail(userModel.email))
            return  validationUser.applyError(ErrorUser.AddressEmailIsInvalid)

        if(userRepository.verifyEmailExist(userModel.email))
            return validationUser.applyError(ErrorUser.AddressAlreadyRegistered)

        return validationUser
    }
}

class UserValidate{
    lateinit var error: ErrorUser
    var hasError: Boolean = false

    fun applyError(errorUser: ErrorUser): UserValidate {

        return apply {
        this.error = errorUser
        hasError = true
        }
    }
}

