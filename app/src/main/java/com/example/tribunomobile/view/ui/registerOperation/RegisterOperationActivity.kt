package com.example.tribunomobile.view.ui.registerOperation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tribunomobile.R
import kotlinx.android.synthetic.main.activity_register_operation.*


class RegisterOperationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var operationViewModel: RegisterOperationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_operation)

        // recupera valor passado por da outra activity
        val it = intent
        val information = it.getStringExtra("typeOperation")

        operationViewModel = ViewModelProvider(this).get(RegisterOperationViewModel::class.java)

        buttonSave.setOnClickListener(this)
         observe()
    }

    private fun observe(){
        operationViewModel.text.observe(this, Observer {

        })
    }

    override fun onClick(v: View) {
        val id = v.id

        if(id == R.id.buttonSave){
            operationViewModel.save()
        }
    }
}