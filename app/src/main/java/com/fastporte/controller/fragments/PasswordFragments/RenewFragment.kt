package com.fastporte.controller.fragments.PasswordFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
    }

}