package com.example.tribunomobile.service.repository.local.operationDetail

import android.content.Context
import com.example.tribunomobile.service.model.OperationDetailModel
import com.example.tribunomobile.service.repository.local.AppDataBase

class OperationDetailRepository(context: Context) {

    private val mDataBase = AppDataBase.getDatabase(context).operationDetailDAO()

    fun save(operationDetail:OperationDetailModel): Long{
        return mDataBase.save(operationDetail)
    }

    fun saveList(list: List<OperationDetailModel>){
        for(item in list){
            mDataBase.save(item)
        }
    }

    fun update(operationDetail: OperationDetailModel): Int{
        return mDataBase.update(operationDetail)
    }

    fun delete(operationDetail: OperationDetailModel){
        mDataBase.delete(operationDetail)
    }

}