package com.fastport.RegisterFragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.fastport.Beans.Clients
import com.fastport.Beans.Drivers
import com.fastport.R
import com.fastport.databinding.ActivityRegisterBinding
import com.fastport.helpers.User


class FillInformationFragment : Fragment() {
    lateinit var user: User;
    lateinit var userType: String;
    lateinit var userCountry: String;
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
        uploadDocument(view)
        uploadProfileId(view)
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
                userCountry = parent.getItemAtPosition(position).toString()
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
                userType = parent.getItemAtPosition(position).toString()
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
    private fun uploadDocument(view_: View){
        val txtUploadPhoto=view_.findViewById<TextView>(R.id.txtUploadPhoto)
        txtUploadPhoto.setOnClickListener(){
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }
    private fun uploadProfileId(view_: View){
        val txtUploadDocument=view_.findViewById<TextView>(R.id.txtUploadDocument)
        txtUploadDocument.setOnClickListener(){
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }
    private fun next(view_: View){
        val btnNext=view_.findViewById<Button>(R.id.btnNext2)
        val txtName=view_.findViewById<EditText>(R.id.txtName)
        val txtLastName=view_.findViewById<EditText>(R.id.txtLastName)
        val txtdateBirth=view_.findViewById<EditText>(R.id.txtdateBirth)
        val txtPhoneNumber=view_.findViewById<EditText>(R.id.txtPhoneNumber)
        //val spUserType=view_.findViewById<EditText>(R.id.spUserType)
        //val spIdentityCardType=view_.findViewById<EditText>(R.id.spIdentityCardType)
        val txtIdentityCardTypeNumber=view_.findViewById<EditText>(R.id.txtIdentityCardTypeNumber)

        btnNext.setOnClickListener(){

                val argumentos = arguments
                if (argumentos != null) {
                    val valor = argumentos.getStringArray("tempUser")
                    if (valor != null) {
                        user= User(
                            txtdateBirth.text.toString(),
                            "",
                            valor[0].toString(),
                            0,
                            txtName.text.toString(),
                            txtLastName.text.toString(),
                            "",
                            txtPhoneNumber.text.toString(),
                            userCountry,
                            valor[1].toString(),
                            ""
                        );
                        val bundle = Bundle()
                        bundle.putSerializable("tempInfoUser",user)
                        if(userType=="Cliente"){
                            bundle.putSerializable("userType","client")
                        }else {
                            bundle.putSerializable("userType","driver")
                        }
                        Navigation.findNavController(view_).navigate(R.id.action_fillInformationFragment_to_newAccountFragment,bundle)
                    }
                }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                1 -> {
                    val selectedImageUri = data?.data
                    // Manejar la imagen seleccionada
                }
            }
        }
    }

}