package com.fastport.RegisterFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.fastport.Beans.Clients
import com.fastport.Beans.Drivers
import com.fastport.Interface.ClientsInterface
import com.fastport.Interface.RegisterInterface
import com.fastport.LoginActivity
import com.fastport.R
import com.fastport.helpers.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewAccountFragment : Fragment() {
    lateinit var clientUser: Clients
    lateinit var driverUser: Drivers
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View= inflater.inflate(R.layout.fragment_new_account, container, false)
        register(view)
         //Inflate the layout for this fragment

        return view
    }
    private fun register(view_: View){
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

            if(userTypeText=="client"){
                clientUser= Clients(
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

            }else{
                driverUser= Drivers(
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