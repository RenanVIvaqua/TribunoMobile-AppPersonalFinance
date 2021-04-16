package com.example.tribunomobile.service.repository.local.User

import androidx.room.*
import com.example.tribunomobile.service.model.UserModel

@Dao
interface UserDAO {

    @Insert
    fun save(user: UserModel): Long

    @Update
    fun update(user: UserModel): Int

    @Delete
    fun delete(user: UserModel)

    @Query("SELECT  id, name, email, password, active FROM User Where email = :address and password = :password limit 1")
    fun doLogin(address: String, password: String): UserModel

    @Query("SELECT id, name, email, password, active FROM User Where id = :id")
    fun get(id: Int): UserModel

    @Query("SELECT count(id) FROM User Where upper(email) = :email")
    fun verifyEmailExist(email: String): Int

}