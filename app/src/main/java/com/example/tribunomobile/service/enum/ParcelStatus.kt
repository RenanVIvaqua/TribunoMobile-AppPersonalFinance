package com.example.tribunomobile.service.enum

import androidx.room.TypeConverter

enum class  ParcelStatus(value: Int){
    Undefined(0),
    Current(1),
    Paid(2),
    Expired(3)
}

class ParcelStatusConverter{

    @TypeConverter
    fun fromParcelStatusConverter(value: ParcelStatus): Int{
        return value.ordinal
    }

    @TypeConverter
    fun toParcelStatusConverter(value: Int): ParcelStatus{
        return when(value){
            1 -> ParcelStatus.Current
            2 -> ParcelStatus.Paid
            3 -> ParcelStatus.Expired
            else -> ParcelStatus.Undefined
        }
    }
}