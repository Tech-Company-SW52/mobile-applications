package com.fastporte.controller.fragments.ClientFragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.fastporte.R


class SearchFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)
        val items = listOf("Trunk", "Van", "Cargo Trunk", "Bus", "Taxi")

        val autoComplete : AutoCompleteTextView = view.findViewById(R.id.auto_complete)

        val adapter = context?.let { ArrayAdapter<String>(it, R.layout.list_item, items) }

        autoComplete.setAdapter(adapter)

        autoComplete.onItemClickListener = AdapterView.OnItemClickListener{
            adapterView, view, i, l ->

            val itemSelected = adapterView.getItemAtPosition(i)
        }
        val buttonresult = view.findViewById<Button>(R.id.bt_result)
        val editnumber = view.findViewById<EditText>(R.id.et_username)
        buttonresult.setOnClickListener(){
            Toast.makeText(context, autoComplete.text.toString() + " " + editnumber.text.toString(), Toast.LENGTH_SHORT).show()
        }
        next(view)

        return view
    }
    private fun next(view_: View) {
        val btnNext = view_.findViewById<Button>(R.id.bt_result)
        btnNext.setOnClickListener() {
            Navigation.findNavController(view_)
                .navigate(R.id.action_searchFragment_to_searchResultFragment)

        }
    }

}