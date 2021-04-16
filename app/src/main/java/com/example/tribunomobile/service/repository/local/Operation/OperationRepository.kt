package com.example.tribunomobile.service.repository.local.Operation

import android.content.Context
import com.example.tribunomobile.service.model.OperationModel
import com.example.tribunomobile.service.repository.local.AppDataBase


class OperationRepository(context: Context) {

    private val  mDataBase = AppDataBase.getDatabase(context).operationDAO()

    fun save(operationModel: OperationModel): Boolean{
        return mDataBase.save(operationModel) > 0
    }

    fun update(operationModel: OperationModel){
        return mDataBase.update(operationModel)
    }

    fun delete(operationModel: OperationModel){
        mDataBase.delete(operationModel)
    }



}