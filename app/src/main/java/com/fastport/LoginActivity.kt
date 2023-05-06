package com.fastport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.fastport.Beans.Clients
import com.fastport.Beans.Drivers
import com.fastport.Interface.ClientsInterface
import com.fastport.Interface.DriversInterface
import com.fastport.helpers.SharedPreferences
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<Button>(R.id.btn_login)

        login.setOnClickListener(){
            Login()
        }
        val forgot = findViewById<TextView>(R.id.tvpassword)
        forgot.setOnClickListener(){
            val forgotIntent= Intent(this,PasswordActivity::class.java)
            startActivity(forgotIntent)
        }
        toolbar=findViewById(R.id.myPreToolBar)
        setSupportActionBar(toolbar)
        register()

    }
    private fun Login(){
        val userEmail = findViewById<EditText>(R.id.et_username)
        val userPassword = findViewById<EditText>(R.id.et_password)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-fastporte.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val clientService: ClientsInterface
        clientService = retrofit.create(ClientsInterface::class.java)
        val driverService: DriversInterface
        driverService = retrofit.create(DriversInterface::class.java)

        val ListClient = clientService.getClient("json")
        val ListDriver = driverService.getDriver("json")
        ListClient.enqueue(object: Callback<List<Clients>>{
            override fun onResponse(call: Call<List<Clients>>, response: Response<List<Clients>>) {
                val UserList=response?.body()
                if(UserList!=null){
                    for (user in UserList){
                        if (userEmail.text.toString() == user.email && userPassword.text.toString()== user.password){
                            clientintent(user.id)

                        }

                    }
                }
            }
            override fun onFailure(call: Call<List<Clients>>, t: Throwable) {
                Log.d("LoginActivity Client", t.toString())
            }
        })


        ListDriver.enqueue(object: Callback<List<Drivers>>{
            override fun onResponse(call: Call<List<Drivers>>, response: Response<List<Drivers>>) {
                val UserList=response?.body()
                if(UserList!=null){
                    for (user in UserList){
                        if (userEmail.text.toString() == user.email && userPassword.text.toString()== user.password){
                            driverintent(user.id)
                        }

                    }
                }
            }
            override fun onFailure(call: Call<List<Drivers>>, t: Throwable) {
                Log.d("LoginActivity Driver", t.toString())
            }
        })

    }

    private fun clientintent(id: Int) {
        val clientIntent= Intent(this, ClientActivity::class.java)
        savesharedpreference("id",id.toString())
        startActivity(clientIntent)
    }
    private fun driverintent(id: Int){
        val carrierIntent= Intent(this,CarrierActivity::class.java)
        savesharedpreference("id",id.toString())
        startActivity(carrierIntent)
    }
    private fun savesharedpreference(KeyName: String, value: String){
        val sharedpreferences = SharedPreferences(this)
        sharedpreferences.save(KeyName, value)
    }
    private fun register(){
        val tvCreateAccount=findViewById<TextView>(R.id.tvCreateAccount)
        val registerActivity= Intent(this, RegisterActivity::class.java)
        tvCreateAccount.setOnClickListener(){
            startActivity(registerActivity)
        }
    }
}