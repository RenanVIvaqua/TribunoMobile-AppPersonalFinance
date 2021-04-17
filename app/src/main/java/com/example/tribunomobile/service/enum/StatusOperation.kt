package com.example.tribunomobile.service.enum

import androidx.room.TypeConverter

enum class  StatusOperation(value: Int){
    Undefined(0),
    Current(1),
    Finished(2),
    Expired(3)
}

class StatusOperationConverter{

    @TypeConverter
    fun fromTypeStatusOperation(value: StatusOperation): Int{
        return value.ordinal
    }

    @TypeConverter
    fun toTypeStatusOperation(value: Int): StatusOperation{
        return when(value){
            1 -> StatusOperation.Current
            2 -> StatusOperation.Finished
            3 ->StatusOperation.Expired
            else -> StatusOperation.Undefined
        }
    }
}
