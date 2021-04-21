package com.example.tribunomobile.view.ui.registerOperation

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tribunomobile.R
import com.example.tribunomobile.service.enum.ParcelStatus
import com.example.tribunomobile.service.enum.StatusOperation
import com.example.tribunomobile.service.enum.TypeOperation
import com.example.tribunomobile.service.enum.TypeOperationConverter
import com.example.tribunomobile.service.model.OperationDetailModel
import com.example.tribunomobile.service.model.OperationModel
import com.example.tribunomobile.view.adapter.OperationDetailAdapter
import kotlinx.android.synthetic.main.activity_register_operation.*
import java.text.SimpleDateFormat
import java.util.*


class RegisterOperationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var operationViewModel: RegisterOperationViewModel

    private var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_operation)

        alterTitleActivity()

        operationViewModel = ViewModelProvider(this).get(RegisterOperationViewModel::class.java)

       // editDueDate.addTextChangedListener(MaskEditUtil.mask(editDueDate, MaskEditUtil.FORMAT_DATE))

        editDueDate.setOnClickListener(this)
        buttonSave.setOnClickListener(this)

        observe()
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
            bindListView(listInstallment)

            operationViewModel.save(operationModel, listInstallment)
        }
        if(id == R.id.editDueDate){

            val editText = findViewById<View>(R.id.editDueDate) as EditText

            val date = OnDateSetListener { _, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = monthOfYear
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                updateEditTextDate(editText, calendar)
            }

            DatePickerDialog(this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
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

    private fun updateEditTextDate(editText: EditText, calendar: Calendar) {
        editText.setText(formatDate().format(calendar.time))
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

    private fun bindListView(listOperationDetailModel: List<OperationDetailModel>){
         val adapter = OperationDetailAdapter(listOperationDetailModel, this)
         val listAdapter: ListView = findViewById<View>(R.id.listInstallments) as ListView

         listAdapter.adapter = adapter;
     }

    private fun getDateDue(): Calendar{
        val dateDueNext: Calendar = Calendar.getInstance()
        dateDueNext.set(calendar[Calendar.YEAR],   calendar[Calendar.MONTH],   calendar[Calendar.DAY_OF_MONTH])

        return dateDueNext
    }

    private fun generateInstallments(): List<OperationDetailModel> {

        val list = mutableListOf<OperationDetailModel>()

        val countInstallments = editCountInstallments.text.toString().toInt()
        val valueInstallment = editInstallmentsValue.text.toString().toFloat()

        for (i in 1..countInstallments) {

            val dateDueNext: Calendar = getDateDue()
            dateDueNext.add(Calendar.MONTH, i - 1)

            var depois = formatDate().format(dateDueNext.time)

            var installment = OperationDetailModel().apply {
                number = i
                value = valueInstallment
                dueDate =  formatDate().format(dateDueNext.time)
                status = ParcelStatus.Current
            }
            list.add(installment)
        }

        return list
    }

    private fun formatDate(): SimpleDateFormat {
        return  SimpleDateFormat(getString(R.string.dateFormat) , Locale.US)
    }
}

