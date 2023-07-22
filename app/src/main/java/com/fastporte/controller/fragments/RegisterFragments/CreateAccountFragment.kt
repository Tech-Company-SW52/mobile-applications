package com.fastporte.controller.fragments.RegisterFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.fastporte.R

class CreateAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_create_account, container, false)
        next(view)
        return view
    }

    private fun next(view_: View) {
        val btnNext = view_.findViewById<Button>(R.id.btnNext)
        val txtEmail = view_.findViewById<TextView>(R.id.txtEmail)
        val txtPassword = view_.findViewById<TextView>(R.id.txtPassword)
        val txtConfirmPassword = view_.findViewById<TextView>(R.id.txtConfirmPassword)
        btnNext.setOnClickListener() {

            if (txtEmail.text.isEmpty() || txtPassword.text.isEmpty() || txtConfirmPassword.text.isEmpty()) {
                Toast.makeText(context, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                if (txtPassword.text.toString() == txtConfirmPassword.text.toString()) {
                    val temporalUser =
                        arrayOf(txtEmail.text.toString(), txtPassword.text.toString())
                    val bundle = Bundle()
                    bundle.putStringArray("tempUser", temporalUser)
                    Navigation.findNavController(view_).navigate(
                        R.id.action_createAccountFragment_to_fillInformationFragment,
                        bundle
                    )
                } else {
                    Toast.makeText(context, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }


    }

}