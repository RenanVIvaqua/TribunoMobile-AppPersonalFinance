package com.example.tribunomobile.service.repository.local

import android.content.Context
import androidx.room.*
import com.example.tribunomobile.service.model.OperationDetailModel
import com.example.tribunomobile.service.model.UserModel
import com.example.tribunomobile.service.model.OperationModel
import com.example.tribunomobile.service.repository.local.user.UserDAO
import com.example.tribunomobile.service.repository.local.operation.OperationDAO
import com.example.tribunomobile.service.repository.local.operationDetail.OperationDetailDAO

@Database(entities = [UserModel::class, OperationModel::class, OperationDetailModel::class],version = 6)
abstract class AppDataBase : RoomDatabase(){

    abstract fun userDAO(): UserDAO
    abstract fun operationDAO(): OperationDAO
    abstract fun operationDetailDAO(): OperationDetailDAO

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