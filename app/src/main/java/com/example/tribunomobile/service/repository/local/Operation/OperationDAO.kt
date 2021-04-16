package com.example.tribunomobile.service.repository.local.Operation

import androidx.room.*
import com.example.tribunomobile.service.model.OperationModel
import com.example.tribunomobile.service.model.OperationWithDetailModel
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDAO {

    @Insert
    fun save(operationModel: OperationModel): Long

    @Update
    fun update(operationModel: OperationModel)

    @Delete
    fun delete(operationModel: OperationModel)

    @Transaction
    @Query("select * from Operation")
    fun getOperationWithDetail(): List<OperationWithDetailModel>
}