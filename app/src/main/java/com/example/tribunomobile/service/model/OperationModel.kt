package com.example.tribunomobile.service.model

import android.text.style.TtsSpan
import androidx.room.*
import java.time.Instant
import java.util.*

@Entity(tableName = "Operation")

class OperationModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "idUser")
    var idUser: Long = 0

    @ColumnInfo(name = "nameOperation")
    var nameOperation: String = ""

    @ColumnInfo(name = "typeOperation")
    var typeOperation: String = ""

    @ColumnInfo(name = "registerDate")
    var registerDate:  String? = null

    @ColumnInfo(name = "alterDate")
    var alterDate: String? = null

    @ColumnInfo(name = "statusOperation")
    var statusOperation: String = ""

}