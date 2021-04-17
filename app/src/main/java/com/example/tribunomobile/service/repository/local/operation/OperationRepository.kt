package com.example.tribunomobile.service.repository.local.operation

import android.content.Context
import com.example.tribunomobile.service.model.OperationModel
import com.example.tribunomobile.service.repository.local.AppDataBase

class OperationRepository(context: Context) {

    private val  mDataBase = AppDataBase.getDatabase(context).operationDAO()

    fun save(operationModel: OperationModel): Long{
        return mDataBase.save(operationModel)
    }

    fun update(operationModel: OperationModel): Int{
         return mDataBase.update(operationModel)
    }

    fun delete(operationModel: OperationModel){
        mDataBase.delete(operationModel)
    }
}