package com.fastporte.controller.fragments.RegisterFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.fastporte.Interface.RegisterInterface
import com.fastporte.controller.activities.LoginActivity
import com.fastporte.R
import com.fastporte.models.User
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
        register(view)
        //Inflate the layout for this fragment

        return view
    }

    private fun register(view_: View) {
        val btnSignUp = view_.findViewById<Button>(R.id.btnSignUp)
        val txtUsername = view_.findViewById<EditText>(R.id.txtUsername)
        val txtUserDescription = view_.findViewById<EditText>(R.id.txtUserDescription)
        btnSignUp.setOnClickListener {
            val tempInfoUser = arguments?.getSerializable("tempInfoUser") as User
            val userTypeText = arguments?.getString("userType")

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api-fastporte.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val registerService: RegisterInterface
            registerService = retrofit.create(RegisterInterface::class.java)

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
                    tempInfoUser.photo
                )
                /*registerService.registerClient(clientUser).enqueue(object : Callback<Clients>{
                    override fun onResponse(call: Call<Clients>, response: Response<Clients>) {
                        TODO("Not yet implemented")
                    }

                    override fun onFailure(call: Call<Clients>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })*/

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
                    tempInfoUser.photo
                )
                /*registerService.registerDriver(driverUser).enqueue(object : Callback<Drivers>{
                    override fun onResponse(call: Call<Drivers>, response: Response<Drivers>) {
                        TODO("Not yet implemented")
                    }

                    override fun onFailure(call: Call<Drivers>, t: Throwable) {
                        TODO("Not yet implemented")
                    }


                })*/
            }
            val loginIntent = Intent(context, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

}