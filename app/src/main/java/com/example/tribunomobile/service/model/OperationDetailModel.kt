package com.example.tribunomobile.service.model

import androidx.room.*
import com.example.tribunomobile.service.enum.ParcelStatus
import com.example.tribunomobile.service.enum.ParcelStatusConverter
import com.example.tribunomobile.service.enum.TypeOperation
import com.example.tribunomobile.service.enum.TypeOperationConverter
import java.util.*
import kotlin.String as String

@Entity(tableName = "operationDetail")
class OperationDetailModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "idOperation")
    var idOperation: Long = 0

    @ColumnInfo(name = "number")
    var number: Int = 0

    @ColumnInfo(name = "value")
    var value: Float = 0f

    @ColumnInfo(name = "dueDate")
    var dueDate: String? = null

    @ColumnInfo(name = "paymentDate")
    var paymentDate: String? = null

    @ColumnInfo(name = "status")
    @TypeConverters(ParcelStatusConverter::class)
    var status: ParcelStatus = ParcelStatus.Undefined

    fun getNumber(): String =  "$number"

    fun getValue(): String = "$value"

    fun  getDate(): String = if(dueDate != null) "$dueDate" else ""
}


