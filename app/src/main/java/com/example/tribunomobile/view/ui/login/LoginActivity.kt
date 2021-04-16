package com.example.tribunomobile.view.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tribunomobile.R
import com.example.tribunomobile.view.ui.main.MainActivity
import com.example.tribunomobile.view.ui.registerUser.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        buttonLogin.setOnClickListener(this)
        linkCreateAccount.setOnClickListener(this)

        removeBar()
        observer()
    }

    override fun onClick(v: View) {
        val id = v.id

        if (id == R.id.buttonLogin) {
            val address: String = editAddress.text.toString()
            val password: String = editPassword.text.toString()

            mViewModel.doLogin(address,password)

        }

        if (id == R.id.linkCreateAccount) {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        if (id == R.id.linkForgot) {

        }
    }

    private fun observer() {

        mViewModel.login.observe(this, Observer {
            if (it) {
            startActivity(Intent(this,
                MainActivity::class.java))
            }
            else {
                Toast.makeText(this, "incorrect address or password", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun removeBar() {
        if (supportActionBar != null)
            supportActionBar!!.hide()
    }

}