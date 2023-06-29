package com.fastporte.controller.fragments.RegisterFragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.fastporte.Interface.RegisterInterface
import com.fastporte.controller.activities.LoginActivity
import com.fastporte.R
import com.fastporte.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewAccountFragment : Fragment() {
    lateinit var clientUser: User
    lateinit var driverUser: User
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_new_account, container, false)

        val termsConditions = view.findViewById<Button>(R.id.btTermsConditions)

        termsConditions.setOnClickListener {
            val url =
                "https://www.freeprivacypolicy.com/live/8c9bf7ae-7d09-424d-8243-d6f04cc8c058"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        register(view)
        //Inflate the layout for this fragment
        return view
    }

    private fun register(view_: View) {
        val btnSignUp = view_.findViewById<Button>(R.id.btnSignUp)
        val txtUsername = view_.findViewById<EditText>(R.id.txtUsername)
        val txtUserDescription = view_.findViewById<EditText>(R.id.txtUserDescription)
        val checkboxConditions = view_.findViewById<CheckBox>(R.id.checkboxConditions)
        val checkboxInformation = view_.findViewById<CheckBox>(R.id.checkboxInformation)
        btnSignUp.setOnClickListener {
            val username = txtUsername.text.toString()
            val userDescription = txtUserDescription.text.toString()
            val conditionsChecked = checkboxConditions.isChecked
            val informationChecked = checkboxInformation.isChecked

            if (username.isEmpty() || userDescription.isEmpty() || !conditionsChecked || !informationChecked) {
                Toast.makeText(
                    context,
                    "Debe rellenar y marcar todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val tempInfoUser = arguments?.getSerializable("tempInfoUser") as User
                val userTypeText = arguments?.getString("userType")

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api-fastporte.azurewebsites.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val registerService: RegisterInterface =
                    retrofit.create(RegisterInterface::class.java)

                if (userTypeText == "client") {
                    clientUser = User(
                        tempInfoUser.birthdate,
                        txtUserDescription.text.toString(),
                        tempInfoUser.email,
                        tempInfoUser.id,
                        tempInfoUser.name,
                        tempInfoUser.lastname,
                        txtUsername.text.toString(),
                        tempInfoUser.phone,
                        tempInfoUser.region,
                        tempInfoUser.password,
                        "https://static.vecteezy.com/system/resources/previews/005/544/718/original/profile-icon-design-free-vector.jpg"
                    )
                    registerService.registerClient(clientUser).enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {
                                val loginIntent = Intent(context, LoginActivity::class.java)
                                startActivity(loginIntent)
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            TODO("Not yet implemented")
                        }
                    })
                } else {
                    driverUser = User(
                        tempInfoUser.birthdate,
                        txtUserDescription.text.toString(),
                        tempInfoUser.email,
                        tempInfoUser.id,
                        tempInfoUser.name,
                        tempInfoUser.lastname,
                        txtUsername.text.toString(),
                        tempInfoUser.phone,
                        tempInfoUser.region,
                        tempInfoUser.password,
                        "https://static.vecteezy.com/system/resources/previews/005/544/718/original/profile-icon-design-free-vector.jpg"
                    )
                    registerService.registerDriver(driverUser).enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {
                                val loginIntent = Intent(context, LoginActivity::class.java)
                                startActivity(loginIntent)
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            TODO("Not yet implemented")
                        }
                    })
                }
            }
        }
    }
}