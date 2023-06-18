package com.fastporte.controller.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat.startActivity
import com.fastporte.R
import com.fastporte.network.ClientsService
import com.fastporte.network.DriversService
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.User
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedPreferences = SharedPreferences(this)
        sharedPreferences.removeValue("typeUser")
        sharedPreferences.removeValue("id")
        sharedPreferences.removeValue("fullName")
        val login = findViewById<Button>(R.id.btn_login)

        login.setOnClickListener {
            login()
        }
        val forgot = findViewById<TextView>(R.id.tvpassword)
        forgot.setOnClickListener {
            val forgotIntent = Intent(this, PasswordActivity::class.java)
            startActivity(forgotIntent)
        }
        val create = findViewById<TextView>(R.id.tvCreate)
        create.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }

        toolbar = findViewById(R.id.myPreToolBar)
        setSupportActionBar(toolbar)
        register()

    }

    @SuppressLint("CutPasteId")
    private fun login() {
        val userEmail = findViewById<EditText>(R.id.et_username)
        val userPassword = findViewById<EditText>(R.id.et_password)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-fastporte.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val clientService: ClientsService = retrofit.create(ClientsService::class.java)

        val driverService: DriversService = retrofit.create(DriversService::class.java)

        val listClient = clientService.getClient("json")
        val listDriver = driverService.getDriver("json")

        listClient.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val userList = response.body()
                if (userList != null) {
                    for (user in userList) {
                        if (userEmail.text.toString() == user.email && userPassword.text.toString() == user.password) {
                            clientIntent(user.id, user.name, user.lastname)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("LoginActivity Client", t.toString())
            }
        })

        listDriver.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val userList = response.body()
                if (userList != null) {
                    for (user in userList) {
                        if (userEmail.text.toString() == user.email && userPassword.text.toString() == user.password) {
                            driverIntent(user.id, user.name, user.lastname)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("LoginActivity Driver", t.toString())
            }
        })

    }

    private fun clientIntent(id: Int, name: String, lastName: String){
        val clientIntent = Intent(this, ClientActivity::class.java)
        saveSharedPreferences("id", id.toString())
        saveSharedPreferences("typeUser", "client")
        saveSharedPreferences("fullName", "$name $lastName")
        val etPassword = findViewById<EditText>(R.id.et_password)
        etPassword.text.clear()
        startActivity(clientIntent)
    }

    private fun driverIntent(id: Int, name: String, lastName: String) {
        val carrierIntent = Intent(this, CarrierActivity::class.java)
        saveSharedPreferences("id", id.toString())
        saveSharedPreferences("typeUser", "driver")
        saveSharedPreferences("fullName", "$name $lastName")
        val etPassword = findViewById<EditText>(R.id.et_password)
        etPassword.text.clear()
        startActivity(carrierIntent)
    }

    private fun saveSharedPreferences(KeyName: String, value: String) {
        val sharedPreferences = SharedPreferences(this)
        sharedPreferences.removeValue(KeyName)
        sharedPreferences.save(KeyName, value)
    }

    private fun register() {
        val tvCreateAccount = findViewById<TextView>(R.id.tvCreateAccount)
        val registerActivity = Intent(this, RegisterActivity::class.java)
        tvCreateAccount.setOnClickListener {
            startActivity(registerActivity)
        }
    }
}