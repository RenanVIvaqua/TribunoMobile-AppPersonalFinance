package com.example.tribunomobile.service.model

import android.text.style.TtsSpan
import androidx.room.*
import com.example.tribunomobile.service.enum.StatusOperation
import com.example.tribunomobile.service.enum.StatusOperationConverter
import com.example.tribunomobile.service.enum.TypeOperation
import com.example.tribunomobile.service.enum.TypeOperationConverter
import java.time.Instant
import java.util.*

@Entity(tableName = "operation")

class OperationModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "idUser")
    var idUser: Long = 0

    @ColumnInfo(name = "nameOperation")
    var nameOperation: String = ""

    @ColumnInfo(name = "typeOperation")
    @TypeConverters(TypeOperationConverter::class)
    var typeOperation: TypeOperation = TypeOperation.Undefined

    @ColumnInfo(name = "registerDate")
    var registerDate:  String? = null

    @ColumnInfo(name = "alterDate")
    var alterDate: String? = null

    @ColumnInfo(name = "statusOperation")
    @TypeConverters(StatusOperationConverter::class)
    var statusOperation: StatusOperation = StatusOperation.Undefined

}










