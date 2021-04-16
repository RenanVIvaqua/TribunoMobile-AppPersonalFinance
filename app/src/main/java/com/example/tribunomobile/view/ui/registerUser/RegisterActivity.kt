package com.example.tribunomobile.view.ui.registerUser

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tribunomobile.R
import com.example.tribunomobile.service.model.UserModel
import com.example.tribunomobile.view.ui.login.LoginActivity
import com.example.tribunomobile.view.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: RegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        buttonCreateRegister.setOnClickListener(this)
        linkReturnLogin.setOnClickListener(this)

        observe()
        removeBar()
    }

    override fun onClick(v: View) {

        val id = v.id

        if(id == R.id.buttonCreateRegister){

            if(!validationFieldMandatory(editName,"Name") || !validationFieldMandatory(editAddress,"Address") || !validationFieldMandatory(editPassword,"Password"))
                return

            if(!validationMinValueField(editPassword, "Password", 5))
                return

            val user = UserModel().apply {
                this.id = 0
                this.name = editName.text.toString()
                this.email =  editAddress.text.toString()
                this.password = editPassword.text.toString()
                this.active = true
            }

            mViewModel.validateAndCreateAccount(user)
        }

        if (id == R.id.linkReturnLogin) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun observe() {

        mViewModel.createAccount.observe(this, Observer {
            if (!it.hasError) {
                startActivity(Intent(this, MainActivity::class.java))
            }
            else {
                when (it.error) {
                    ErrorUser.AddressAlreadyRegistered -> {
                        validationAddressEmailError(editAddress, ErrorUser.AddressAlreadyRegistered)
                    }
                    ErrorUser.AddressEmailIsInvalid -> {
                        validationAddressEmailError(editAddress,
                            ErrorUser.AddressEmailIsInvalid)
                    }
                    else -> Toast.makeText(this,it.error.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun validationAddressEmailError(editText: EditText, errorType: ErrorUser){
        editText.error = errorType.toString()
        editText.isFocusable = true
        editText.requestFocus()
    }

    private fun removeBar() {
        if (supportActionBar != null)
            supportActionBar!!.hide()
    }

    private fun validationFieldMandatory(editText: EditText, nameField: String): Boolean{
        if(editText.text.isNullOrEmpty()){
            editText.error = "$nameField is mandatory"
            editText.isFocusable = true
            editText.requestFocus()
            return false
        }

        return true
    }

    private  fun validationMinValueField(editText: EditText, nameField: String, minCharacters: Int): Boolean{

        val text=  editText.text

        if(text.count() <= minCharacters){
            editText.error = " $nameField must be longer than $minCharacters characters"
            editText.isFocusable = true
            editText.requestFocus()
            return false
        }

        return true
    }

}


