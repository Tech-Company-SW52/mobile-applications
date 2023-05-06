package com.fastport.CarrierFragments.CarrierProfile.Components

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.fastport.CarrierClasses.Vehicle
import com.fastport.CarrierFragments.CarrierProfile.CarrierVehicleProfileFragment
import com.fastport.databinding.FragmentAddVehicleBinding

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
    private var listener: AddVehicleInterface? = null
    fun setAddVehicleListener(listener: CarrierVehicleProfileFragment) {
        this.listener = listener
    }

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentAddVehicleBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        builder.setPositiveButton("Add") { dialog, which ->
            val type = binding.etVehicleName.text.toString()
            val capacity = binding.etCapacity.text.toString().toInt()
            val urlImage = binding.etPhoto.text.toString()
            //Toast.makeText(requireContext(), "datos $type $capacity $urlImage", Toast.LENGTH_SHORT).show()
            //listener?.onAddVehicleFormulary(type, capacity, urlImage)
            //añadir vehicle al arraylist

            listener?.onAddVehicleFormulary(type, capacity, urlImage)


            dismiss()
        }


        val dialog = builder.create()
        dialog.show()

        return dialog
    }


}