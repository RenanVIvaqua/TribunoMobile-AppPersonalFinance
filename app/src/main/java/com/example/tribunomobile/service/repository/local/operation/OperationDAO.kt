package com.example.tribunomobile.service.repository.local.operation

import androidx.room.*
import com.example.tribunomobile.service.model.OperationModel
import com.example.tribunomobile.service.model.OperationWithDetailModel

@Dao
interface OperationDAO {

    @Insert
    fun save(operationModel: OperationModel): Long

    @Update
    fun update(operationModel: OperationModel): Int

    @Delete
    fun delete(operationModel: OperationModel)

    @Transaction
    @Query("select * from operation")
    fun getOperationWithDetail(): List<OperationWithDetailModel>
}