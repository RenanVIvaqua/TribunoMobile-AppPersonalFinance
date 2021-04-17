package com.example.tribunomobile.view.ui.registerOperation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tribunomobile.R
import com.example.tribunomobile.service.enum.ParcelStatus
import com.example.tribunomobile.service.enum.StatusOperation
import com.example.tribunomobile.service.enum.TypeOperation
import com.example.tribunomobile.service.enum.TypeOperationConverter
import com.example.tribunomobile.service.model.*
import kotlinx.android.synthetic.main.activity_register_operation.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class RegisterOperationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var operationViewModel: RegisterOperationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_operation)

        alterTitleActivity()

        operationViewModel = ViewModelProvider(this).get(RegisterOperationViewModel::class.java)

        buttonSave.setOnClickListener(this)

        observe()
    }

    private fun alterTitleActivity(){

        if(TypeOperation.Income == contextOperationRegister()) {
            txtTitle.text = getString(R.string.titleIncomeRegisterActivity)
        }else{
            txtTitle.text = getString(R.string.titleExpenseRegisterActivity)
        }
    }

    private fun contextOperationRegister(): TypeOperation{
        val contextTypeOperation =  intent.getStringExtra(getString(R.string.parameterTypeOperation)).toString()
        return TypeOperationConverter().toTypeOperation(contextTypeOperation.toInt())
    }

    private fun observe(){
        operationViewModel.operationModel.observe(this, Observer {

            if(it){
                Toast.makeText(this,"Gravado",Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onClick(v: View) {
        val id = v.id

        if(id == R.id.buttonSave){

            var operationModel = OperationModel().apply {
                nameOperation = editNameOperator.text.toString()
                typeOperation = TypeOperation.Income
                registerDate = SimpleDateFormat(getString(R.string.dateTimeFormat)).format(Date())
                statusOperation = StatusOperation.Current
            }

            val listInstallment = generateInstallments()

            operationViewModel.save(operationModel, listInstallment)
        }
    }

    private fun generateInstallments(): List<OperationDetailModel> {

        val list = mutableListOf<OperationDetailModel>()

       // val dueDateInstallment = SimpleDateFormat(getString(R.string.dateFormat)).format(editDueDate.text.toString())

        val countInstallments = editCountInstallments.text.toString().toInt()

        val valueInstallment = editInstallmentsValue.text.toString().toFloat()

        for (i in 1..countInstallments) {

            var installment = OperationDetailModel().apply {
                number = i
                value = valueInstallment
                //dueDate = dueDateInstallment
                status = ParcelStatus.Current
            }
            list.add(installment)
        }

        return list
    }
}