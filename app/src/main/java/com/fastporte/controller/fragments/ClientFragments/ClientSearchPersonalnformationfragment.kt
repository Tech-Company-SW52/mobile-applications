package com.fastporte.controller.fragments.ClientFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fastporte.R
import com.fastporte.models.User

class ClientSearchPersonalnformationfragment : Fragment() {
    private var user: User? = null;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(
            R.layout.fragment_client_search_personalnformationfragment,
            container,
            false
        )
        setInfo(view)
        return view
    }

    fun setUser(user: User) {
        this.user = user
    }

    fun setInfo(view_: View) {
        val tv_sv_ui_name = view_.findViewById<TextView>(R.id.tv_sv_ui_name)
        val tv_sv_ui_age = view_.findViewById<TextView>(R.id.tv_sv_ui_age)
        val tv_sv_ui_email = view_.findViewById<TextView>(R.id.tv_sv_ui_email)
        val tv_sv_ui_phone = view_.findViewById<TextView>(R.id.tv_sv_ui_phone)
        tv_sv_ui_name.text = user?.name ?: "None"
        tv_sv_ui_age.text = (user?.birthdate ?: "None") + " years"
        tv_sv_ui_email.text = user?.email ?: "None"
        tv_sv_ui_phone.text = user?.phone ?: "None"
    }

}