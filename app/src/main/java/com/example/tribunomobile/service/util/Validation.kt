package com.example.tribunomobile.service.util

import android.widget.EditText

object Validation {
     fun validationFieldMandatory(editText: EditText, nameField: String = "", focusField: Boolean = true, messageField: Boolean = true): Boolean{
        if(editText.text.isNullOrEmpty()){
            if(messageField)
                editText.error = "$nameField is mandatory"
            if(focusField) {
                editText.isFocusable = true
                editText.requestFocus()
            }
            return false
        }
        return true
    }

    fun validationMinValueField(editText: EditText, nameField: String, minCharacters: Int): Boolean{
        val text=  editText.text

        if(text.count() <= minCharacters){
            editText.error = " $nameField must be longer than $minCharacters characters"
            editText.isFocusable = true
            editText.requestFocus()
            return false
        }
        return true
    }

    fun validationAddressEmail(email: String): Boolean{

        if(!email.isNullOrBlank())
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

        return false
    }
}