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
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.fastporte.R
import com.fastporte.controller.activities.RegisterActivity
import com.fastporte.helpers.BaseURL
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.User
import com.fastporte.network.ClientsService
import com.fastporte.network.DriversService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ForgotFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_forgot, container, false)
        validate(view)
        register(view)
        return view
    }

    private fun register(view_: View) {

        val register = view_.findViewById<TextView>(R.id.tvCreate)
        register.setOnClickListener() {
            val registerIntent = Intent(context, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }

    private fun validate(view_: View){
        val btnSubmit = view_.findViewById<Button>(R.id.btn_submit)
        val etEmail = view_.findViewById<TextView>(R.id.editTextTextEmailAddress5)
        val radioGroup = view_.findViewById<RadioGroup>(R.id.radioGroup)

        btnSubmit.isEnabled = false

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton = view_.findViewById(checkedId)

            //Seleccionar solo una opción del radioGroup y deseleccionar las demás
            for (i in 0 until group.childCount) {
                val btn: RadioButton = group.getChildAt(i) as RadioButton
                if (btn.id != checkedId) {
                    btn.isChecked = false
                }
            }
        }

        etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString()
                val isValidEmail = isValidEmail(email)
                btnSubmit.isEnabled = email.isNotEmpty() && isValidEmail
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                btnSubmit.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString()
                val isValidEmail = isValidEmail(email)
                btnSubmit.isEnabled = email.isNotEmpty() && isValidEmail
            }
        })

        btnSubmit.setOnClickListener() {
            val selected = view_.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)

            val retrofit = Retrofit.Builder()
                .baseUrl(BaseURL.BASE_URL.toString()+"api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            var encontrado = false

            if(selected.text == "Client"){
                val clientService: ClientsService = retrofit.create(ClientsService::class.java)
                val listClient = clientService.getClient("json")
                listClient.enqueue(object : Callback<List<User>> {
                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        val userList = response.body()
                        if (userList != null) {
                            for (user in userList) {
                                if (etEmail.text.toString() == user.email) {
                                    val sharedPreferences = SharedPreferences(context!!)
                                    sharedPreferences.save("RPtype", "client")
                                    sharedPreferences.save("RPid", user.id.toString())
                                    encontrado = true
                                    break
                                }
                            }
                            if(encontrado){
                                Navigation.findNavController(view_).navigate(R.id.action_forgotFragment_to_renewFragment)
                            }
                            else{
                                etEmail.error = "Email not found in clients list"
                            }
                        }
                    }
                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        Log.d("Error Client", t.toString())
                    }
                })

            }
            else if(selected.text == "Driver"){
                val driverService: DriversService = retrofit.create(DriversService::class.java)
                val listDriver = driverService.getDriver("json")
                listDriver.enqueue(object : Callback<List<User>> {
                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        val userList = response.body()
                        if (userList != null) {
                            for (user in userList) {
                                if (etEmail.text.toString() == user.email) {
                                    val sharedPreferences = SharedPreferences(context!!)
                                    sharedPreferences.save("RPtype", "driver")
                                    sharedPreferences.save("RPid", user.id.toString())
                                    encontrado = true
                                    break
                                }
                            }
                            if(encontrado){
                                Navigation.findNavController(view_).navigate(R.id.action_forgotFragment_to_renewFragment)
                            }
                            else{
                                etEmail.error = "Email not found in drivers list"
                            }
                        }
                    }
                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        Log.d("Error Driver", t.toString())
                    }
                })
            }
        }

    }

    // Función para validar el formato de correo electrónico
    private fun isValidEmail(email: String): Boolean {
        val pattern = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return email.matches(pattern)
    }


}