package com.fastporte.controller.fragments.ClientFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.fastporte.R
import com.fastporte.models.User

class ClientRequestServiceFragment : Fragment() {
    lateinit var user: User;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_client_request_service, container, false)
        getUser()
        back(view)
        return view
    }

    private fun getUser() {
        user = arguments?.getSerializable("searchUserTemp") as User
    }

    private fun back(view_: View) {
        val tv_back_request = view_.findViewById<TextView>(R.id.tv_back_request)
        tv_back_request.setOnClickListener(){
            val bundle = Bundle()
            bundle.putSerializable("searchUserTemp", user)
            Navigation.findNavController(view_)
                .navigate(R.id.action_clientRequestServiceFragment_to_clientSearchDriverProfile2,bundle)
        }
    }

}