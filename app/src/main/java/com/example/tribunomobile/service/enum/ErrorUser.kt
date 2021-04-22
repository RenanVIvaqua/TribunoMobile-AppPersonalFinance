package com.example.tribunomobile.service.enum

enum class ErrorUser(val value: String){
    ErrorRecord("Error trying record"),
    AddressAlreadyRegistered("There is already registered email"),
    AddressEmailIsInvalid("There is invalid address email")
}

class ErrorUserConverter{
    fun fromErrorUserConverter(value: ErrorUser): Int{
        return value.ordinal
    }

    fun toErrorUserConverter(value: Int): ErrorUser{
        return when(value){
            1 -> ErrorUser.ErrorRecord
            2 -> ErrorUser.AddressAlreadyRegistered
            else -> ErrorUser.AddressEmailIsInvalid
        }
    }
}