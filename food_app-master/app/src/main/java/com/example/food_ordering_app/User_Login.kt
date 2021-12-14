package com.example.food_ordering_app

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class User_Login : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        val email = findViewById<TextInputLayout>(R.id.userEmail)
        val password = findViewById<TextInputLayout>(R.id.userPassword)
        val button = findViewById<Button>(R.id.button)
        val regbutton = findViewById<Button>(R.id.button2)


        val errorMessage = findViewById<TextView>(R.id.errorView)
        val registernav = findViewById<Button>(R.id.button2)

        val inputemailname = MutableLiveData<String>()

        val inputPassword = MutableLiveData<String>()

        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.loading_screen)
        dialog.setCanceledOnTouchOutside(false)

        val intent = Intent(this, MainActivity2::class.java)
        val intent1 = Intent(this, MainActivity::class.java)
        //apiClient = ApiClient()
        val apiClient=application as ApiClient
       sessionManager = SessionManager(this,)

        button.setOnClickListener {
            dialog.show()


        CoroutineScope(Dispatchers.IO).launch {
           var result=apiClient.apiService.login(LoginRequest("user3@gmail.com","password1"))

            withContext(Dispatchers.Main) {
                if(result.isSuccessful){
                    dialog.dismiss()
                    sessionManager.saveAuthToken(result.body()?.token)
                    intent.putExtra("string",result.body()?.token)

                    startActivity(intent)

                    Toast.makeText(this@User_Login,"Login successfull",Toast.LENGTH_LONG)
                }
                else
                    Toast.makeText(this@User_Login,"Login successfull",Toast.LENGTH_LONG)
               }
        }
    }

        regbutton.setOnClickListener {
            startActivity(intent1)
        }
    }


    }

