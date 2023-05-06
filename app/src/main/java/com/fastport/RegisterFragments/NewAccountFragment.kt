package com.fastport.RegisterFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.fastport.LoginActivity
import com.fastport.R

class NewAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View= inflater.inflate(R.layout.fragment_renew, container, false)
        login(view)
        // Inflate the layout for this fragment
        return view
    }
    private fun login(view_: View){
        val login = view_.findViewById<Button>(R.id.btnSignUp)
        login.setOnClickListener(){
            val loginIntent = Intent(context, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

}