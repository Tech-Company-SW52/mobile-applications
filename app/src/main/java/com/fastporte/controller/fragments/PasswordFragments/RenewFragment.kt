package com.fastporte.controller.fragments.PasswordFragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.fastporte.controller.activities.LoginActivity
import com.fastporte.R

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

    private fun login(view_: View) {
        val login = view_.findViewById<Button>(R.id.btredirect)
        login.setOnClickListener() {
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