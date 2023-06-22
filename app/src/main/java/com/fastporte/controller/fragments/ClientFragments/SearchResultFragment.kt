package com.fastporte.controller.fragments.ClientFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.controller.fragments.ClientFragments.RecyclerView.SearchCarrierAdapter
import com.fastporte.controller.fragments.ClientFragments.RecyclerView.SearchCarrierListener
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.User
import com.fastporte.models.Vehicle
import com.fastporte.network.CommentsService
import com.fastporte.network.DriversService
import com.fastporte.network.VehicleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchResultFragment : Fragment(), SearchCarrierListener {
    lateinit var user: User;
    lateinit var resultRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_search_result, container, false)

        resultRecyclerView = view.findViewById(R.id.rv_search_result)

        retrofit(view)
        next(view)
        return view
    }

    private fun retrofit(_view: View) {
        val _context = requireContext()
        val sharedPreferences = SharedPreferences(_context)
        val type = sharedPreferences.getValue("type").toString()
        val size = sharedPreferences.getValue("size").toString()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-fastporte.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val vehicleService: VehicleService = retrofit.create(VehicleService::class.java)

        val listVehicle = vehicleService.getVehicleFindTypeQuantity(type, size, "json")
        listVehicle.enqueue(object : Callback<List<Vehicle>> {
            override fun onResponse(call: Call<List<Vehicle>>, response: Response<List<Vehicle>>) {
                val vehicleList = response.body()
                if (vehicleList != null) {
                    resultRecyclerView.adapter = SearchCarrierAdapter(
                        vehicleList,
                        requireContext(),
                        this@SearchResultFragment
                    )
                    resultRecyclerView.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<List<Vehicle>>, t: Throwable) {
                Log.d("Search Vehicle", t.toString())
            }
        })


    }

    private fun next(view_: View) {
        val btnNext = view_.findViewById<TextView>(R.id.tv_back)
        btnNext.setOnClickListener() {
            Navigation.findNavController(view_)
                .navigate(R.id.action_searchResultFragment_to_searchFragment)

        }
    }


    override fun onActionsItemClick(vehicle: Vehicle, view_: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-fastporte.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val driverService: DriversService = retrofit.create(DriversService::class.java)
        val driverById = driverService.getDriver(vehicle.driver.id, "json")
        driverById.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    user = response.body()!!
                    val bundle = Bundle()
                    bundle.putSerializable("searchUserTemp", user)
                    Navigation.findNavController(view_)
                        .navigate(
                            R.id.action_searchResultFragment_to_clientSearchDriverProfile,
                            bundle
                        )
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}