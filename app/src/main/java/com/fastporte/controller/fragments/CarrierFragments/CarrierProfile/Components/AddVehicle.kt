package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.Components

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.CarrierVehicleProfileFragment
import com.fastporte.databinding.FragmentAddVehicleBinding
import com.fastporte.helpers.BaseURL
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.Experience
import com.fastporte.models.Vehicle
import com.fastporte.network.ProfileService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [AddVehicle.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddVehicle(

) : DialogFragment() {
    private lateinit var binding: FragmentAddVehicleBinding
    val bundle = Bundle()
    lateinit var vehicle: Vehicle
    //private var listener: AddVehicleInterface? = null
    //fun setAddVehicleListener(listener: CarrierVehicleProfileFragment) {
        //this.listener = listener
    //}
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BaseURL.BASE_URL.toString())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val vehicleService: ProfileService = retrofit.create(ProfileService::class.java)


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentAddVehicleBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        builder.setPositiveButton("Add") { dialog, which ->
            val type = binding.etVehicleName.text.toString()
            val capacity = binding.etCapacity.text.toString().toInt()
            var urlImage = binding.etPhoto.text.toString()
            if (urlImage.isEmpty())
                urlImage = "https://png.pngtree.com/png-vector/20191021/ourlarge/pngtree-vector-car-icon-png-image_1834527.jpg"
            //Toast.makeText(requireContext(), "datos $type $capacity $urlImage", Toast.LENGTH_SHORT).show()
            //listener?.onAddVehicleFormulary(type, capacity, urlImage)
            //añadir vehicle al arraylist
            vehicle = Vehicle(0, type, capacity, urlImage)
            //listener?.onAddVehicleFormulary(type, capacity, urlImage)

            postVehicle()
            dismiss()
        }
        val dialog = builder.create()
        dialog.show()
        return dialog
    }
    private fun postVehicle() {

        val request = vehicleService.postVehicleDriver(
            SharedPreferences(this.requireContext()).getValue("id")!!.toInt(), vehicle
        )

        request.enqueue(object : Callback<Vehicle> {
            override fun onFailure(call: Call<Vehicle>, t: Throwable) {
                Log.d("Activity fail", t.toString())
            }

            override fun onResponse(call: Call<Vehicle>, response: Response<Vehicle>) {
                if (response.isSuccessful) {
                    Log.d("Activity success", response.body().toString())
                } else {
                    Log.d("Activity fail", response.toString())
                }
            }
        })
    }
}