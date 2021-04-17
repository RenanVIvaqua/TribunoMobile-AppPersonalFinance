package com.example.tribunomobile.view.ui.registerOperation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tribunomobile.service.model.OperationDetailModel
import com.example.tribunomobile.service.model.OperationModel
import com.example.tribunomobile.service.repository.local.operation.OperationRepository
import com.example.tribunomobile.service.repository.local.operationDetail.OperationDetailRepository
import com.example.tribunomobile.service.repository.local.user.UserRepository

class RegisterOperationViewModel(application: Application) : AndroidViewModel(application) {

    private var operationRepository = OperationRepository(application)
    private var operationDetailRepository = OperationDetailRepository(application)

    private var _operationModel = MutableLiveData<Boolean>()
    var operationModel: LiveData<Boolean> = _operationModel

     fun save(operationModel: OperationModel, listInstallment: List<OperationDetailModel>)
     {
         var idOperation = operationRepository.save(operationModel)

         operationDetailRepository.saveList(listInstallment)

         _operationModel.value =  true

    }

}