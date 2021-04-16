package com.example.tribunomobile.service.repository.local

import android.content.Context
import androidx.room.*
import com.example.tribunomobile.service.model.OperationDetailModel
import com.example.tribunomobile.service.model.UserModel
import com.example.tribunomobile.service.model.OperationModel
import com.example.tribunomobile.service.repository.local.User.UserDAO
import com.example.tribunomobile.service.repository.local.Operation.OperationDAO

@Database(entities = [UserModel::class, OperationModel::class, OperationDetailModel::class],version = 2)
abstract class AppDataBase : RoomDatabase(){

    abstract fun userDAO(): UserDAO
    abstract fun operationDAO(): OperationDAO

    companion object{
        private lateinit var INSTANCE: AppDataBase

        fun getDatabase(context: Context): AppDataBase{

            if(!:: INSTANCE.isInitialized){
                synchronized(AppDataBase::class.java){
                    INSTANCE =
                        Room.databaseBuilder(context, AppDataBase::class.java, "ApplicationDB")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }
    }
}