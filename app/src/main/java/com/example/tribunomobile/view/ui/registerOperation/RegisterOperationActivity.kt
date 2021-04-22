package com.example.tribunomobile.view.ui.registerOperation

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.*
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
import com.example.tribunomobile.service.util.MaskEditUtil
import com.example.tribunomobile.service.util.Validation
import com.example.tribunomobile.view.adapter.OperationDetailAdapter
import kotlinx.android.synthetic.main.activity_register_operation.*
import java.text.SimpleDateFormat
import java.util.*


class RegisterOperationActivity : AppCompatActivity(), View.OnClickListener, View.OnTouchListener {

    private lateinit var operationViewModel: RegisterOperationViewModel

    private var calendar = Calendar.getInstance()

    private var listInstallment: List<OperationDetailModel> = mutableListOf<OperationDetailModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_operation)

        alterTitleActivity()
        alterTextButtonSaveToGenerateInstallments()
        fillTypeOperation()

        operationViewModel = ViewModelProvider(this).get(RegisterOperationViewModel::class.java)

        editInstallmentsValue.setOnFocusChangeListener { _, boolean -> checkGeneratingInstallments(boolean) }
        editCountInstallments.setOnFocusChangeListener { _, boolean -> checkGeneratingInstallments(boolean) }

        editDueDate.setOnTouchListener(this)
        buttonSave.setOnClickListener(this)

        observe()
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val id = v.id

        if (event.action == MotionEvent.ACTION_DOWN && id == R.id.editDueDate) {
            val editText = findViewById<View>(R.id.editDueDate) as EditText

            val date = OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = monthOfYear
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth

                updateEditTextDate(editText, calendar)
                checkGeneratingInstallments()
            }
            DatePickerDialog(this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        return true
    }

    override fun onClick(v: View) {
        val id = v.id

        if(id == R.id.buttonSave){

            if(!validationField())
                return

            if(!checkOperationHasInstallment())
                return

            var operationModel = OperationModel().apply {
                nameOperation = editNameOperator.text.toString()
                typeOperation = contextOperationRegister().value
                registerDate = SimpleDateFormat(getString(R.string.dateTimeFormat)).format(Date())
                statusOperation = StatusOperation.Current
            }

            bindListView(listInstallment)

            operationViewModel.save(operationModel, listInstallment)
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

    private fun alterTextButtonSaveToGenerateInstallments(){
        buttonSave.text = "Generate Installments"
    }

    private fun fillTypeOperation(){

        val dropdown = findViewById<Spinner>(R.id.spinnerCategory)

        val array1 = arrayListOf("Gasto fixo","Gasto variável")

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array1)

        dropdown.adapter = adapter;
    }

    private fun getCategory():String{
        val spinnerTypeOperation = findViewById<View>(R.id.spinnerCategory) as Spinner
        return  spinnerTypeOperation.selectedItem.toString()
    }

    private fun checkOperationHasInstallment(): Boolean{
        val countList = listInstallment.count()
        checkGeneratingInstallments()

        if(listInstallment.count() < 1) {
            Toast.makeText(this, "Nenhuma parcela foi gerada", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(listInstallment.count() != countList) {
            Toast.makeText(this, "Novas parcelas fora gerado", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun validationField():Boolean{
        if( Validation.validationFieldMandatory(editNameOperator) &&
           // Validation.validationFieldMandatory(editTypeOperation) &&
            Validation.validationFieldMandatory(editCountInstallments) &&
            Validation.validationFieldMandatory(editInstallmentsValue) &&
            Validation.validationFieldMandatory(editDueDate)) {
            return true
        }
        return false
    }

    private fun checkGeneratingInstallments(value: Boolean = false){
        if(!value)
            validateGeneratingInstallments()
    }

    private fun validateGeneratingInstallments(){

        if( Validation.validationFieldMandatory(editCountInstallments, "Installments", focusField = false, messageField = false)
            &&  Validation.validationFieldMandatory(editInstallmentsValue, "Value", focusField = false, messageField = false)
            && Validation.validationFieldMandatory(editDueDate,"Due Date", focusField = false, messageField = false))
        {
            generateInstallmentsAndBindListView()
            buttonSave.text = "Save"
        }
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
        val footerView: View = layoutInflater.inflate(R.layout.header_list_operation_detail, null)

        if(listAdapter.headerViewsCount < 1)
            listAdapter.addHeaderView(footerView)

        listAdapter.adapter = adapter
     }

    private fun generateInstallmentsAndBindListView(){
        listInstallment = generateInstallments()
        bindListView(listInstallment)
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

