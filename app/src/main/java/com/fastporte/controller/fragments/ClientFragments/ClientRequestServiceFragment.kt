package com.fastporte.controller.fragments.ClientFragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import com.fastporte.R
import com.fastporte.models.JsonResponse
import com.fastporte.models.User
import com.fastporte.network.GeoCodeService
import kotlinx.coroutines.*


import okhttp3.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

class ClientRequestServiceFragment : Fragment() {
    lateinit var user: User;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_client_request_service, container, false)
        val editTextDate = view.findViewById<EditText>(R.id.id_client_search_request_date)
        getUser()
        back(view)

        //search
        autoCompleteFrom(view)
        autoCompleteTo(view)
        //

        setHourStart(view)
        setHourEnd(view)


        selectPeople(view)
        selectWeight(view)
        editTextDate.setOnClickListener { selectDate(view) }

        sendRequest(view)
        return view
    }

    private fun setHourEnd(view: View) {
        val hourPicker = view.findViewById<NumberPicker>(R.id.hourEndPicker)

        // Establecer rango de horas
        hourPicker.minValue = 0
        hourPicker.maxValue = 23

        // Formatear visualización de horas
        hourPicker.setFormatter { value ->
            String.format(Locale.getDefault(), "%02d:00", value)
        }
    }

    private fun setHourStart(view: View) {
        val hourPicker = view.findViewById<NumberPicker>(R.id.hourStartPicker)

        // Establecer rango de horas
        hourPicker.minValue = 0
        hourPicker.maxValue = 23

        // Formatear visualización de horas
        hourPicker.setFormatter { value ->
            String.format(Locale.getDefault(), "%02d:00", value)
        }
    }

    private fun selectWeight(view: View) {
        val numberPicker = view.findViewById<EditText>(R.id.weightPicker)
        val buttonDecrease = view.findViewById<Button>(R.id.buttonWeightDecrease)
        val buttonIncrease = view.findViewById<Button>(R.id.buttonWeightIncrease)


        // Establecer el valor inicial
        numberPicker.setText("0")

        // Botón de decremento
        buttonDecrease.setOnClickListener {
            if ( numberPicker.text.toString().toInt() > 0) {
                numberPicker.setText((numberPicker.text.toString().toInt()-1).toString())
            }
        }

        // Botón de incremento
        buttonIncrease.setOnClickListener {
            numberPicker.setText((numberPicker.text.toString().toInt()+1).toString())
        }
    }

    private fun selectPeople(view: View) {
        val numberPicker = view.findViewById<EditText>(R.id.peoplePicker)
        val buttonDecrease = view.findViewById<Button>(R.id.buttonPeopleDecrease)
        val buttonIncrease = view.findViewById<Button>(R.id.buttonPeopleIncrease)


        // Establecer el valor inicial
        numberPicker.setText("0")

        // Botón de decremento
        buttonDecrease.setOnClickListener {
            if ( numberPicker.text.toString().toInt() > 0) {
                numberPicker.setText((numberPicker.text.toString().toInt()-1).toString())
            }
        }

        // Botón de incremento
        buttonIncrease.setOnClickListener {
            numberPicker.setText((numberPicker.text.toString().toInt()+1).toString())
        }
    }

    private fun selectDate(view_: View) {
        val editTextDate = view_.findViewById<EditText>(R.id.id_client_search_request_date)

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
            editTextDate.setText(selectedDate)
        }, currentYear, currentMonth, currentDay)

        datePickerDialog.datePicker.minDate = calendar.timeInMillis // Establecer fecha mínima como hoy
        datePickerDialog.show()
    }

    private fun sendRequest(view_: View) {
        val bt_client_search_request_send = view_.findViewById<Button>(R.id.bt_client_search_request_send)
        bt_client_search_request_send.setOnClickListener(){
            Navigation.findNavController(view_)
                .navigate(R.id.action_clientRequestServiceFragment_to_searchFragment)
        }
    }


    private fun autoCompleteFrom(view_: View) {
            val autoCompleteTextViewFrom = view_.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewFrom)
            val options = mutableListOf("Peru")

            val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
        autoCompleteTextViewFrom.setAdapter(adapter)

        autoCompleteTextViewFrom.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val newText = s.toString()
                    val retrofit = Retrofit.Builder()
                        .baseUrl("https://api.opencagedata.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    val GeoCodeService = retrofit.create(GeoCodeService::class.java)

                    val query =autoCompleteTextViewFrom.text.toString()+", Peru"
                    val apiKey = "4f3e2e9c5cce427694e5cbb203ccfb4e"
                    val language = "es"
                    val pretty = 1
                    val replacedQuery = query.replace(" ", "+")
                    val call = GeoCodeService.getFormattedResults(replacedQuery, apiKey, language, pretty)
                    Log.d("TAG", "My query es: "+query)
                    call.enqueue(object:retrofit2.Callback<JsonResponse>{
                        override fun onResponse(
                            call: Call<JsonResponse>,
                            response: Response<JsonResponse>
                        ) {
                            if (response.isSuccessful) {
                                val jsonResponse = response.body()
                                var formattedResults: List<String>? = null

                                if (jsonResponse != null) {
                                    formattedResults = jsonResponse.results?.mapNotNull { it.formatted }
                                    if (formattedResults != null) {
                                        for (result in formattedResults) {
                                            options.add(result)
                                        }
                                        val filteredOptions = options.filter { it.contains(newText, true) }.toTypedArray()
                                        adapter.clear()
                                        adapter.addAll(*filteredOptions)
                                        adapter.notifyDataSetChanged()
                                    }
                                } else {}
                            } else {}
                        }
                        override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                            TODO("Not yet implemented")
                        }
                    })
                }

                override fun afterTextChanged(s: Editable?) {}
            })

    }

    private fun autoCompleteTo(view_: View) {
        val autoCompleteTextViewTo = view_.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewTo)
        val options = mutableListOf("Peru")

        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
        autoCompleteTextViewTo.setAdapter(adapter)

        autoCompleteTextViewTo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newText = s.toString()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.opencagedata.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val GeoCodeService = retrofit.create(GeoCodeService::class.java)

                val query =autoCompleteTextViewTo.text.toString()+", Peru"
                val apiKey = "4f3e2e9c5cce427694e5cbb203ccfb4e"
                val language = "es"
                val pretty = 1
                val replacedQuery = query.replace(" ", "+")
                val call = GeoCodeService.getFormattedResults(replacedQuery, apiKey, language, pretty)
                Log.d("TAG", "My query es: "+query)
                call.enqueue(object:retrofit2.Callback<JsonResponse>{
                    override fun onResponse(
                        call: Call<JsonResponse>,
                        response: Response<JsonResponse>
                    ) {
                        if (response.isSuccessful) {
                            val jsonResponse = response.body()
                            var formattedResults: List<String>? = null

                            if (jsonResponse != null) {
                                formattedResults = jsonResponse.results?.mapNotNull { it.formatted }
                                if (formattedResults != null) {
                                    for (result in formattedResults) {
                                        options.add(result)
                                    }
                                    val filteredOptions = options.filter { it.contains(newText, true) }.toTypedArray()
                                    adapter.clear()
                                    adapter.addAll(*filteredOptions)
                                    adapter.notifyDataSetChanged()
                                }
                            } else {}
                        } else {}
                    }
                    override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun getUser() {
        user = arguments?.getSerializable("searchUserTemp") as User
    }

    private fun back(view_: View) {
        val tv_back_request = view_.findViewById<TextView>(R.id.tv_back_request)
        tv_back_request.setOnClickListener(){
            val bundle = Bundle()
            bundle.putSerializable("searchUserTemp", user)
            Navigation.findNavController(view_)
                .navigate(R.id.action_clientRequestServiceFragment_to_clientSearchDriverProfile2,bundle)
        }
    }


}

