package com.example.tribunomobile.service.repository.local.User

import android.content.Context
import com.example.tribunomobile.service.model.UserModel
import com.example.tribunomobile.service.repository.local.AppDataBase

class UserRepository(context: Context) {

    private val mDataBase = AppDataBase.getDatabase(
        context
    ).userDAO()

    fun save(user: UserModel): Boolean{
        return mDataBase.save(user) > 0
    }

    fun update(user: UserModel): Boolean{
        return mDataBase.update(user) > 0
    }

    fun delete(user: UserModel){
        mDataBase.delete(user)
    }

    fun get(id: Int): UserModel{
        return mDataBase.get(id)
    }

    fun doLogin(address: String, password: String): UserModel {
        return mDataBase.doLogin(address,password)
    }

    fun verifyEmailExist(email: String): Boolean{

        var email = email.toUpperCase()
        var contagem = mDataBase.verifyEmailExist(email.toUpperCase())
        return mDataBase.verifyEmailExist(email.toUpperCase()) > 0
    }
}