package com.fastport.RegisterFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.navigation.Navigation
import com.fastport.R
import com.fastport.databinding.ActivityRegisterBinding


class FillInformationFragment : Fragment() {
    val countries= arrayListOf("Select Country","Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Costa Rica", "Cuba", "Ecuador", "El Salvador", "Guatemala", "Haití", "Honduras", "Jamaica", "México", "Nicaragua", "Panamá", "Paraguay", "Perú", "Puerto Rico", "República Dominicana", "Uruguay", "Venezuela")
    val types= arrayListOf("Select type","Transportista","Cliente")
    val cards= arrayListOf("Select type","DNI","Pasaporte")
    private lateinit var mBinding: ActivityRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_fill_information, container, false)
        next(view)
        spinnerCountry(view)
        spinnerUserType(view)
        spinnerCardType(view)
        return view
    }
    private fun spinnerCountry(view_: View){
        val spCountry=view_.findViewById<Spinner>(R.id.spCountry)
        mBinding=ActivityRegisterBinding.inflate(layoutInflater)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spCountry.adapter=adapter

        spCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                deleteCountryHint()
                val selectedItem = parent.getItemAtPosition(position).toString()
                Log.d("Spinner", "Opción seleccionada: $selectedItem")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No se ha seleccionado ninguna opción
            }
        }
    }
    private fun spinnerUserType(view_: View){
        val spUserType=view_.findViewById<Spinner>(R.id.spUserType)
        mBinding=ActivityRegisterBinding.inflate(layoutInflater)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spUserType.adapter=adapter

        spUserType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                deleteUserHint()
                val selectedItem = parent.getItemAtPosition(position).toString()
                Log.d("Spinner", "Opción seleccionada: $selectedItem")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No se ha seleccionado ninguna opción
            }
        }
    }
    private fun spinnerCardType(view_: View){
        val spIdentityCardType=view_.findViewById<Spinner>(R.id.spIdentityCardType)
        mBinding=ActivityRegisterBinding.inflate(layoutInflater)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cards)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spIdentityCardType.adapter=adapter

        spIdentityCardType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                deleteCardHint()
                val selectedItem = parent.getItemAtPosition(position).toString()
                Log.d("Spinner", "Opción seleccionada: $selectedItem")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No se ha seleccionado ninguna opción
            }
        }
    }
    private fun deleteCountryHint(){
        val index = countries.indexOf("Select Country")
        if (index != -1) {
            countries.removeAt(index)
        }
    }
    private fun deleteUserHint(){
        val index = types.indexOf("Select type")
        if (index != -1) {
            types.removeAt(index)
        }
    }
    private fun deleteCardHint(){
        val index = cards.indexOf("Select type")
        if (index != -1) {
            cards.removeAt(index)
        }
    }
    private fun next(view_: View){
        val btnNext=view_.findViewById<Button>(R.id.btnNext2)
        btnNext.setOnClickListener(){
            Navigation.findNavController(view_).navigate(R.id.action_fillInformationFragment_to_newAccountFragment)
        }
    }
}