package com.example.tribunomobile.service.repository.local.operationDetail

import androidx.room.*
import com.example.tribunomobile.service.model.OperationDetailModel

@Dao
interface OperationDetailDAO {

    @Insert
    fun save(operationDetail: OperationDetailModel): Long

    @Update
    @Transaction
    fun update(operationDetail: OperationDetailModel): Int

    @Delete
    @Transaction
    fun delete(operationDetail: OperationDetailModel)
}