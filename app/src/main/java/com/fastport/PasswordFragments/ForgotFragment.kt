package com.fastport.PasswordFragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.fastport.R
import com.fastport.RegisterActivity


class ForgotFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_forgot, container, false)
        next(view)
        register(view)
        return view
    }
    private fun next(view_: View){
        val btnNext = view_.findViewById<Button>(R.id.btn_login)
        btnNext.setOnClickListener(){
            Navigation.findNavController(view_).navigate(R.id.action_forgotFragment_to_renewFragment)

        }
    }
    private fun register(view_: View){
        val register = view_.findViewById<TextView>(R.id.tvCreate)
        register.setOnClickListener(){
            val registerIntent = Intent(context, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }


}