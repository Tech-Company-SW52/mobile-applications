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
        val view:View= inflater.inflate(R.layout.fragment_client_search_personalnformationfragment, container, false)
        setInfo(view)
        return view
    }
    fun setUser(user: User) {
        this.user = user
    }
    fun setInfo(view_: View){
        val tv_sv_ui_name = view_.findViewById<TextView>(R.id.tv_sv_ui_name)
        tv_sv_ui_name.text = user?.name ?: "None"
    }

}