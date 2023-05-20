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
import com.fastporte.models.Vehicle
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.Components.AddVehicle
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.Components.AddVehicleInterface
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.RecyclerViewProfile.VehicleProfileAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var type: String = ""
var capacity: Int = 0
var image: String = ""

/**
 * A simple [Fragment] subclass.
 * Use the [CarrierVehicleProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("NAME_SHADOWING")
class CarrierVehicleProfileFragment : Fragment(), AddVehicleInterface {


    var vehicles = ArrayList<Vehicle>()
    var vehicleAdapter = VehicleProfileAdapter(vehicles)

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // show dialog add vehicle


        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        showAddVehicle()
    }

    private fun showAddVehicle() {
        /*val btnAddVehicle = vehicleView.findViewById<Button>(R.id.btAddVehicle)
        btnAddVehicle?.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.fragment_add_vehicle, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.etVehicleName)

            with(builder){
                setTitle("Agregar Vehiculo")
                setPositiveButton("Agregar"){dialog, which ->
                    Toast.makeText(context, "Vehiculo Agregado", Toast.LENGTH_SHORT).show()
                }
                setNegativeButton("Cancelar"){dialog, which ->
                    Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show()
                }
                setView(dialogLayout)
                show()
            }
        }*/

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vehicleView: View = inflater.inflate(
            com.fastporte.R.layout.fragment_carrier_vehicle_profile,
            container,
            false
        )
        //val itTypeVehicle = vehicleView.findViewById<TextInputEditText>(com.fastport.R.id.etVehicleName)
        //val itCapacity = vehicleView.findViewById<TextInputEditText>(com.fastport.R.id.etCapacity)
        //val itImage = vehicleView.findViewById<TextInputEditText>(com.fastport.R.id.etPhoto)
        val addVehicle = AddVehicle()
        addVehicle.setAddVehicleListener(this)

        val btnAddVehicle = vehicleView.findViewById<Button>(com.fastporte.R.id.btAddVehicle)
        btnAddVehicle?.setOnClickListener {
            addVehicle.show(parentFragmentManager, "AddVehicle")
            /*
            val inflater = layoutInflater
            val dialogLayout =
                inflater.inflate(com.fastport.R.layout.fragment_add_vehicle, null)
            val editText =
                dialogLayout.findViewById<TextInputEditText>(com.fastport.R.id.etVehicleName)
            val capacity =
                dialogLayout.findViewById<TextInputEditText>(com.fastport.R.id.etCapacity)
            val image = dialogLayout.findViewById<TextInputEditText>(com.fastport.R.id.etPhoto)
            */

        }
        loadVehicles()
        initView(vehicleView)

        return vehicleView

    }

    fun onAddVehicleFormulary() {
        Toast.makeText(context, "Vehiculo Agregado", Toast.LENGTH_SHORT).show()

    }

    private fun initView(view: View) {
        val rvVehicle = view.findViewById<RecyclerView>(com.fastporte.R.id.rvExperienceCarrier)

        rvVehicle.adapter = vehicleAdapter
        rvVehicle.layoutManager = LinearLayoutManager(view.context)
    }

    private fun loadVehicles() {
        vehicles.add(
            Vehicle(
                1,
                "Taxi",
                6,
                "https://autoland.com.pe/wp-content/uploads/2022/04/MgZxPlus_Interna_miniatura.png"
            )
        )
        vehicles.add(
            Vehicle(
                2,
                "Camioneta",
                10,
                "https://movertis.com/wp-content/uploads/2020/08/shutterstock_308683868-1.jpgwidth1000ampnameshutterstock_308683868-1.jpg"
            )
        )
        vehicles.add(
            Vehicle(
                3,
                "Trailer",
                15,
                "https://www.esan.edu.pe/images/blog/2018/08/29/1500x844-factores-vehiculo-carga.jpg"
            )
        )
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


    @SuppressLint("NotifyDataSetChanged")
    override fun onAddVehicleFormulary(
        vehicleType: String,
        vehicleCapacity: Int,
        vehiclePhoto: String
    ) {
        type = vehicleType
        capacity = vehicleCapacity
        image = vehiclePhoto
        Toast.makeText(context, "datos $type $capacity $image", Toast.LENGTH_SHORT).show()

        vehicles.add(Vehicle(vehicles.size + 1, type, capacity, image))
        vehicleAdapter.notifyDataSetChanged()


    }
}