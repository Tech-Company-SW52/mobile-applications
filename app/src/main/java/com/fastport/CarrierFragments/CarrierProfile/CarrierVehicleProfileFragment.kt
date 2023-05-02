package com.fastport.CarrierFragments.CarrierProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastport.CarrierClasses.Vehicle
import com.fastport.CarrierFragments.CarrierProfile.RecyclerViewProfile.VehicleProfileAdapter
import com.fastport.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarrierVehicleProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarrierVehicleProfileFragment : Fragment() {

    var vehicles = ArrayList<Vehicle>()
    var vehicleAdapter = VehicleProfileAdapter(vehicles)

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vehicleView: View = inflater.inflate(R.layout.fragment_carrier_vehicle_profile, container, false)

        loadVehicles()
        initView(vehicleView)

        return vehicleView
    }

    private fun initView(view: View){
        val rvVehicle = view.findViewById<RecyclerView>(R.id.rvCommentsCarrier)

        rvVehicle.adapter = vehicleAdapter
        rvVehicle.layoutManager = LinearLayoutManager(view.context)
    }

    private fun loadVehicles(){
        vehicles.add(Vehicle(1, "Taxi", 6, "https://autoland.com.pe/wp-content/uploads/2022/04/MgZxPlus_Interna_miniatura.png"))
        vehicles.add(Vehicle(2, "Camioneta", 10, "https://movertis.com/wp-content/uploads/2020/08/shutterstock_308683868-1.jpgwidth1000ampnameshutterstock_308683868-1.jpg"))
        vehicles.add(Vehicle(3, "Trailer", 15, "https://www.esan.edu.pe/images/blog/2018/08/29/1500x844-factores-vehiculo-carga.jpg"))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarrierVehicleProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarrierVehicleProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}