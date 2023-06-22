package com.fastporte.controller.fragments.PasswordFragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.fastporte.controller.activities.LoginActivity
import com.fastporte.R
import com.fastporte.helpers.BaseURL
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.User
import com.fastporte.network.ClientsService
import com.fastporte.network.DriversService
import com.fastporte.network.ProfileService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RenewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_renew, container, false)

        login(view)
        return view

    }

    private fun changePassword( view_: View ){
        val sharedPreferences = SharedPreferences(view_.context!!)
        val type = sharedPreferences.getValue("RPtype")
        val userId = sharedPreferences.getValue("RPid")

        var newUser: User? = null

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL.BASE_URL.toString() + "api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        if(type == "client"){
            val clientService: ClientsService = retrofit.create(ClientsService::class.java)
            val client = clientService.getClient(userId!!.toInt(),"json")
            client.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    newUser = response.body()!!
                    newUser!!.password = view_.findViewById<EditText>(R.id.editTextTextPassword2).text.toString()

                    val updateClient = clientService.updateClient(newUser!!.id, newUser)
                    updateClient.enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, responseUpdate: Response<User>) {
                            if(responseUpdate.isSuccessful){
                                Log.d("Password changed succes: ", responseUpdate.body().toString())
                            }
                            else{
                                Log.d("Password changed error: ", responseUpdate.toString())
                            }
                        }
                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Log.d("Password change error: ", t.toString())
                        }
                    })
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("Error getting client: ", t.toString())
                }
            })
        }
        else if(type == "driver"){
            val driverService: DriversService = retrofit.create(DriversService::class.java)
            val listDriver = driverService.getDriver(userId!!.toInt(),"json")
            listDriver.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val user = response.body()
                    user!!.password = view_.findViewById<EditText>(R.id.editTextTextPassword2).text.toString()

                    val updateDriver = driverService.updateDriver(user.id, user)

                    updateDriver.enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, responseUpdate: Response<User>) {
                            if(responseUpdate.isSuccessful){
                                Log.d("Password changed succes: ", responseUpdate.body().toString())
                            }
                            else{
                                Log.d("Password changed error: ", responseUpdate.toString())
                            }
                        }
                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Log.d("Password change error: ", t.toString())
                        }
                    })
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("Error getting driver: ", t.toString())
                }
            })

        }

    }

    private fun login(view_: View) {
        val login = view_.findViewById<Button>(R.id.btredirect)
        login.setOnClickListener() {
            changePassword(view_)
            val loginIntent = Intent(context, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        login.isEnabled = false

        val etPassword1 = view_.findViewById<EditText>(R.id.editTextTextPassword2)
        val etPassword2 = view_.findViewById<EditText>(R.id.editTextTextPassword3)

        etPassword1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val password1 = s.toString()
                val password2 = etPassword2.text.toString()
                val isValidPassword = isValidPassword(password1)
                val isValidPassword2 = isValidPassword(password2)
                if (isValidPassword) {
                    if(isValidPassword2 && isSamePassword(password1, password2)){
                        login.isEnabled = true
                        etPassword1.error = null
                        etPassword2.error = null
                    }
                    else if(!isSamePassword(password1, password2)){
                        etPassword1.error = "Passwords must be the same"
                        login.isEnabled = false
                    }

                } else {
                    etPassword1.error = "Password must be at least 7 characters"
                    login.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // TODO Auto-generated method stub
            }
        })

        etPassword2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val password1 = etPassword1.text.toString()
                val password2 = s.toString()
                val isValidPassword = isValidPassword(password1)
                val isValidPassword2 = isValidPassword(password2)
                if (isValidPassword2) {
                    if(isValidPassword && isSamePassword(password1, password2)){
                        login.isEnabled = true
                        etPassword1.error = null
                        etPassword2.error = null
                    }
                    else if(!isSamePassword(password1, password2)){
                        etPassword2.error = "Passwords must be the same"
                        login.isEnabled = false
                    }
                } else {
                    etPassword2.error = "Password must be at least 7 characters"
                    login.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // TODO Auto-generated method stub
            }
        })


    }

    private fun isValidPassword(password: String): Boolean{
        if (password.length >= 7) {
            return true
        }
        return false
    }

    private fun isSamePassword(password1: String, password2: String): Boolean{
        if (password1 == password2) {
            return true
        }
        return false
    }

}