package com.example.tribunomobile.service.enum

import androidx.room.TypeConverter

enum class TypeOperation(val value: Int){
    Undefined(0),
    Income(1),
    Expense(2);
}

class TypeOperationConverter{

    @TypeConverter
    fun fromTypeOperation(value: TypeOperation): Int{
        return value.ordinal
    }

    @TypeConverter
    fun toTypeOperation(value: Int): TypeOperation{
        return when(value){
            1 -> TypeOperation.Income
            2 -> TypeOperation.Expense
            else -> TypeOperation.Undefined
        }
    }
}