package com.fastporte.controller.fragments.ClientFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.fastporte.R
import com.fastporte.adapter.FavoriteDriversAdapter
import com.fastporte.database.PopularDriverDB
import com.fastporte.models.Driver


class FavoriteDriversFragment : Fragment() {

    var favoriteDrivers: List<Driver> = ArrayList()
    lateinit var favoriteDriversRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_drivers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteDrivers = PopularDriverDB.getInstance(view.context).getPopularDriverDAO().getAllPopularDrivers()
        favoriteDriversRecyclerView = view.findViewById(R.id.rvFavoriteDrivers)

        favoriteDriversRecyclerView.layoutManager = LinearLayoutManager(context)
        favoriteDriversRecyclerView.adapter = FavoriteDriversAdapter(favoriteDrivers, view.context)
    }

}