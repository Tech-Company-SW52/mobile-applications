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
import android.widget.TextView
import androidx.navigation.Navigation
import com.fastporte.R
import com.fastporte.controller.activities.RegisterActivity


class ForgotFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_forgot, container, false)
        next(view)
        validate(view)
        register(view)
        return view
    }

    private fun next(view_: View) {
        val btnNext = view_.findViewById<Button>(R.id.btn_submit)
        btnNext.setOnClickListener() {
            Navigation.findNavController(view_)
                .navigate(R.id.action_forgotFragment_to_renewFragment)

        }
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

        btnSubmit.isEnabled = false

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

    }

    // Función para validar el formato de correo electrónico
    private fun isValidEmail(email: String): Boolean {
        val pattern = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return email.matches(pattern)
    }


}