package com.fastporte.controller.fragments.ClientFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.fastporte.R

class SearchResultFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_search_result, container, false)


        next(view)
        return view
    }
    private fun next(view_: View) {
        val btnNext = view_.findViewById<TextView>(R.id.tv_back)
        btnNext.setOnClickListener() {
            Navigation.findNavController(view_)
                .navigate(R.id.action_searchResultFragment_to_searchFragment)

        }
    }

}