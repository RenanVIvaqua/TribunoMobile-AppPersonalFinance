package com.example.tribunomobile.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "OperationDetail")
class OperationDetailModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "idOperation")
    var idOperation: Int = 0

    @ColumnInfo(name = "number")
    var number: Int = 0

    @ColumnInfo(name = "value")
    var value: Float = 0f

    @ColumnInfo(name = "dueDate")
    var dueDate: String? = null

    @ColumnInfo(name = "paymentDate")
    var paymentDate: String? = null

    @ColumnInfo(name = "status")
    var status: String = ""

}