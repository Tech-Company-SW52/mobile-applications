package com.fastporte.controller.fragments.ClientFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.adapter.SearchClientCommentsAdapter
import com.fastporte.adapter.SearchClientVehicleAdapter
import com.fastporte.models.User
import com.fastporte.models.Vehicle
import com.fastporte.network.CommentsService
import com.fastporte.network.VehicleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ClientSearchVehicleFragment : Fragment() {
    private var user: User? = null;

    lateinit var vehicleRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_client_search_vehicle, container, false)
        vehicleRecyclerView = view.findViewById(R.id.rv_client_search_vehicle)
        loadVehicles(view)
        return view
    }

    fun setUser(user: User) {
        this.user = user
    }

    private fun loadVehicles(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-fastporte.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val vehicleService: VehicleService = retrofit.create(VehicleService::class.java)
        val listVehicles = vehicleService.getVehicleByDriverID(user?.id ?: 0)
        listVehicles.enqueue(object : Callback<List<Vehicle>> {
            override fun onResponse(call: Call<List<Vehicle>>, response: Response<List<Vehicle>>) {
                val listVehicle = response.body()
                if (listVehicle != null) {
                    vehicleRecyclerView.adapter =
                        SearchClientVehicleAdapter(listVehicle, requireContext())
                    vehicleRecyclerView.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<List<Vehicle>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}