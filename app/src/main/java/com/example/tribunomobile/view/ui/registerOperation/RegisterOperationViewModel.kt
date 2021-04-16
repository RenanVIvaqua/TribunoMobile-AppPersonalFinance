package com.example.tribunomobile.view.ui.registerOperation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterOperationViewModel : ViewModel() {

    private var _text = MutableLiveData<String>()

    var text: LiveData<String> = _text


     fun save(){
         _text.value = "teste";

    }
}