package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.models.Vehicle
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.Components.AddVehicle
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.Components.AddVehicleInterface
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.RecyclerViewProfile.VehicleProfileAdapter
import com.fastporte.helpers.BaseURL
import com.fastporte.helpers.SharedPreferences
import com.fastporte.network.ProfileService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var type: String = ""
var capacity: Int = 0
var image: String = ""

class CarrierVehicleProfileFragment : Fragment(), AddVehicleInterface {

    lateinit var vehicleRecyclerView: RecyclerView
    //var vehiclesNew = ArrayList<Vehicle>()
    //var vehicleAdapter: VehicleProfileAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vehicleView: View =
            inflater.inflate(R.layout.fragment_carrier_vehicle_profile, container, false)
        val addVehicle = AddVehicle()
        addVehicle.setAddVehicleListener(this)

        val btnAddVehicle = vehicleView.findViewById<Button>(R.id.btAddVehicle)
        btnAddVehicle?.setOnClickListener {
            addVehicle.show(parentFragmentManager, "AddVehicle")
        }
        //vehicleAdapter = VehicleProfileAdapter(vehiclesNew, vehicleView.context)
        return vehicleView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vehicleRecyclerView = view.findViewById(R.id.rvVehicleCarrier)
        loadVehicles(view)
    }

    private fun loadVehicles(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL.BASE_URL.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val vehicleService: ProfileService = retrofit.create(ProfileService::class.java)

        val request = vehicleService.getDriverVehicle(
            SharedPreferences(view.context).getValue("id")!!.toInt(),
            "json"
        )

        request.enqueue(object : Callback<List<Vehicle>> {
            override fun onResponse(call: Call<List<Vehicle>>, response: Response<List<Vehicle>>) {
                if (response.isSuccessful) {
                    if (response.message() == "No Content") {
                        Toast.makeText(
                            view.context,
                            "No hay vehiculos registrados",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val vehicles: List<Vehicle> = response.body()!!
                        //Agregar un elemento a vehicles
                        //vehiclesNew.add(Vehicle(vehiclesNew.size + 1, type/*, capacity, image*/))
                        vehicleRecyclerView.layoutManager = LinearLayoutManager(view.context)
                        vehicleRecyclerView.adapter = VehicleProfileAdapter(vehicles, view.context)
                    }
                }
            }

            override fun onFailure(call: Call<List<Vehicle>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(view.context, "Error al cargar vehiculos", Toast.LENGTH_SHORT).show()
            }

        })
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onAddVehicleFormulary(
        vehicleType: String,
        vehicleCapacity: Int,
        vehiclePhoto: String
    ) {
        type = vehicleType
        capacity = vehicleCapacity
        image = vehiclePhoto
        //Toast.makeText(context, "datos $type $capacity $image", Toast.LENGTH_SHORT).show()

        //vehiclesNew.add(Vehicle(vehiclesNew.size + 1, type/*, capacity, image*/))
        //vehicleAdapter!!.notifyDataSetChanged()
    }

}