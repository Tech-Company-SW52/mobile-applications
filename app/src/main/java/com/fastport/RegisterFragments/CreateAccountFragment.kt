package com.fastport.RegisterFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.fastport.R

class CreateAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_create_account, container, false)
        next(view)
        return view
    }

    private fun next(view_: View){
        val btnNext=view_.findViewById<Button>(R.id.btnNext)
        btnNext.setOnClickListener(){
            Navigation.findNavController(view_).navigate(R.id.action_createAccountFragment_to_fillInformationFragment)
        }
    }

}