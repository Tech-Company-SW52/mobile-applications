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
import com.fastporte.network.ClientsService
import com.fastporte.network.ContractsService
import com.fastporte.network.GeoCodeService
import kotlinx.coroutines.*
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.*
import java.text.SimpleDateFormat
import java.util.*
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
    lateinit var client: User;
    lateinit var contract: ContractPost
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =
            inflater.inflate(R.layout.fragment_client_request_service, container, false)
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
            if (numberPicker.text.toString().toInt() > 0) {
                numberPicker.setText((numberPicker.text.toString().toInt() - 1).toString())
            }
        }

        // Botón de incremento
        buttonIncrease.setOnClickListener {
            numberPicker.setText((numberPicker.text.toString().toInt() + 1).toString())
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
            if (numberPicker.text.toString().toInt() > 0) {
                numberPicker.setText((numberPicker.text.toString().toInt() - 1).toString())
            }
        }

        // Botón de incremento
        buttonIncrease.setOnClickListener {
            numberPicker.setText((numberPicker.text.toString().toInt() + 1).toString())
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
            if(monthOfYear+1>9){
                val selectedDate = "$year-${monthOfYear + 1}-$dayOfMonth"
                editTextDate.setText(selectedDate)
            }else{
                val selectedDate = "$year-0${monthOfYear + 1}-$dayOfMonth"
                editTextDate.setText(selectedDate)
            }
        }, currentYear, currentMonth, currentDay)

        datePickerDialog.datePicker.minDate =
            calendar.timeInMillis // Establecer fecha mínima como hoy
        datePickerDialog.show()
    }
    private fun setFormatTime(date: String): String{
        val inputFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        val parsedDate = inputFormat.parse(date)
        return outputFormat.format(parsedDate)
    }


    private fun sendRequest(view_: View) {
        val bt_client_search_request_send =
            view_.findViewById<Button>(R.id.bt_client_search_request_send)
        bt_client_search_request_send.setOnClickListener(){
            Navigation.findNavController(view_)
                .navigate(R.id.action_clientRequestServiceFragment_to_searchFragment)

            val autoCompleteTextViewFrom = view_.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewFrom)
            val autoCompleteTextViewTo = view_.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewTo)
            val editTextType = view_.findViewById<EditText>(R.id.id_client_search_request_type)
            val editTextDate = view_.findViewById<EditText>(R.id.id_client_search_request_date)
            val peoplePicker = view_.findViewById<EditText>(R.id.peoplePicker)
            val weightPicker = view_.findViewById<EditText>(R.id.weightPicker)
            val editTextPrice = view_.findViewById<EditText>(R.id.id_client_search_request_price)
            val editTextDescription = view_.findViewById<EditText>(R.id.id_client_search_request_description)
            val hourStartPicker = view_.findViewById<NumberPicker>(R.id.hourStartPicker)
            val hourEndPicker = view_.findViewById<NumberPicker>(R.id.hourEndPicker)
            val timeArrival= hourStartPicker.value.toString()+":00:00"
            val timeDeparture= hourEndPicker.value.toString()+":00:00"


            val inputFields = listOf(
                autoCompleteTextViewFrom,
                autoCompleteTextViewTo,
                editTextType,
                editTextDate,
                peoplePicker,
                weightPicker,
                editTextPrice,
                editTextDescription,
                hourStartPicker,
                hourEndPicker
            )

            val allFieldsFilled = inputFields.all { field ->
                when (field) {
                    is EditText -> field.text.isNotEmpty()
                    is NumberPicker -> field.value.toString().isNotEmpty()
                    else -> false
                }
            }

            if (allFieldsFilled) {
                val retrofitU = Retrofit.Builder()
                    .baseUrl("https://api-fastporte.azurewebsites.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val retrofitC = Retrofit.Builder()
                    .baseUrl("https://api-fastporte.azurewebsites.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val contractsService: ContractsService = retrofitC.create(ContractsService::class.java)
                val args = arguments
                if (args != null && args.containsKey("searchUserTemp")) {
                    this.user = args.getSerializable("searchUserTemp") as User
                }


                val clientService: ClientsService = retrofitU.create(ClientsService::class.java)
                clientService.getClient(SharedPreferences(view_.context).getValue("id")!!.toInt(),"json").enqueue(object : retrofit2.Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if(response.isSuccessful){
                            client=response.body()!!
                            contract= ContractPost(
                                subject = editTextType.text.toString(),
                                from = autoCompleteTextViewFrom.text.toString(),
                                to = autoCompleteTextViewTo.text.toString(),
                                contractDate = editTextDate.text.toString(),
                                timeDeparture = setFormatTime(timeDeparture),
                                timeArrival = setFormatTime(timeArrival),
                                amount = editTextPrice.text.toString(),
                                quantity = peoplePicker.text.toString(),
                                description = editTextDescription.text.toString(),
                                visible = true,
                                client = client,
                                driver = user,
                                status = Status(0,"OFFER"),
                                notification = Notification(0,false)
                            )
                            println("subject: ${contract.subject}")
                            println("from: ${contract.from}")
                            println("to: ${contract.to}")
                            println("date: ${contract.contractDate}")
                            println("timeDeparture: ${contract.timeDeparture}")
                            println("timeArrival: ${contract.timeArrival}")
                            println("amount: ${contract.amount}")
                            println("quantity: ${contract.quantity}")
                            println("visible: ${contract.visible}")
                            println("client: ${contract.client}")
                            println("driver: ${contract.driver}")
                            println("status: ${contract.status}")
                            println("notification: ${contract.notification}")

                            contractsService.postContract(client.id,user.id,contract).enqueue(object :retrofit2.Callback<ContractPost>{
                                override fun onResponse(call: Call<ContractPost>, response: Response<ContractPost>) {
                                    if(response.isSuccessful){
                                        /*val navController = Navigation.findNavController(view_)
                                        navController.navigate(R.id.action_clientRequestServiceFragment_to_searchFragment)*/
                                    }else{
                                        println(response.code())
                                        println(response.body())
                                    }

                                }

                                override fun onFailure(call: Call<ContractPost>, t: Throwable) {
                                    TODO("Not yet implemented")
                                }
                            })

                        }else{
                            println("--------FALSE")
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        println("Fail")
                    }
                })


            } else {
                Toast.makeText(context,"Debe rellenar todos los campos",Toast.LENGTH_SHORT).show()
            }



        }
    }


    private fun autoCompleteFrom(view_: View) {
        val autoCompleteTextViewFrom =
            view_.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewFrom)
        val options = mutableListOf("Peru")

        val adapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
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

                val query = autoCompleteTextViewFrom.text.toString() + ", Peru"
                val apiKey = "4f3e2e9c5cce427694e5cbb203ccfb4e"
                val language = "es"
                val pretty = 1
                val replacedQuery = query.replace(" ", "+")
                val call =
                    GeoCodeService.getFormattedResults(replacedQuery, apiKey, language, pretty)
                Log.d("TAG", "My query es: " + query)
                call.enqueue(object : retrofit2.Callback<JsonResponse> {
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
                                    val filteredOptions =
                                        options.filter { it.contains(newText, true) }.toTypedArray()
                                    adapter.clear()
                                    adapter.addAll(*filteredOptions)
                                    adapter.notifyDataSetChanged()
                                }
                            } else {
                            }
                        } else {
                        }
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
        val autoCompleteTextViewTo =
            view_.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewTo)
        val options = mutableListOf("Peru")

        val adapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
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

                val query = autoCompleteTextViewTo.text.toString() + ", Peru"
                val apiKey = "4f3e2e9c5cce427694e5cbb203ccfb4e"
                val language = "es"
                val pretty = 1
                val replacedQuery = query.replace(" ", "+")
                val call =
                    GeoCodeService.getFormattedResults(replacedQuery, apiKey, language, pretty)
                Log.d("TAG", "My query es: " + query)
                call.enqueue(object : retrofit2.Callback<JsonResponse> {
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
                                    val filteredOptions =
                                        options.filter { it.contains(newText, true) }.toTypedArray()
                                    adapter.clear()
                                    adapter.addAll(*filteredOptions)
                                    adapter.notifyDataSetChanged()
                                }
                            } else {
                            }
                        } else {
                        }
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
        tv_back_request.setOnClickListener() {
            val bundle = Bundle()
            bundle.putSerializable("searchUserTemp", user)
            Navigation.findNavController(view_)
                .navigate(
                    R.id.action_clientRequestServiceFragment_to_clientSearchDriverProfile2,
                    bundle
                )
        }
    }


}

