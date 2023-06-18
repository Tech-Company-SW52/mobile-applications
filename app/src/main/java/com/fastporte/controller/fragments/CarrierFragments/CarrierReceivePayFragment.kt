package com.fastporte.controller.fragments.CarrierFragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.fastporte.R
import com.fastporte.helpers.BaseURL
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.CardClient
import com.fastporte.models.ClientNotification
import com.fastporte.network.CardService
import com.fastporte.network.NotificationService
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarrierReceivePayFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_carrier_receive_pay, container, false)

        paymentFormConstraints(view)
        loadData(view)
        return view
    }
    private fun loadData(view: View){
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL.BASE_URL.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val service = retrofit.create(NotificationService::class.java)

        val request = service.getContract(
            SharedPreferences(view.context).getValue("idNotification")!!.toInt(),
            "json"
        )

        request.enqueue(object : Callback<ClientNotification> {
            override fun onResponse(call: Call<ClientNotification>, response: Response<ClientNotification>) {
                if (response.isSuccessful) {
                    println("RESPONSEEEEEEEEEEE")
                    println("RESPONSE: "+response.body()!!)
                    showData(response.body()!!)
                }
                else    {
                    println("FALLO: " + response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<ClientNotification>, t: Throwable) {
                println("FALLOOOOOOO")
                println("Error: ${t.message}")
            }
        })

    }

    @SuppressLint("SetTextI18n")
    private fun showData(notification: ClientNotification) {
        val tvSubjectCC = view?.findViewById<TextView>(R.id.tvSubjectCC)
        val tvFromCC = view?.findViewById<TextView>(R.id.tvFromCC)
        val tvToCC = view?.findViewById<TextView>(R.id.tvToCC)
        val tvDateCC = view?.findViewById<TextView>(R.id.tvDateCC)
        val tvTimeCC = view?.findViewById<TextView>(R.id.tvTimeCC)
        val tvQuantityCC = view?.findViewById<TextView>(R.id.tvQuantityCC)

        val civUserCC = view?.findViewById<CircleImageView>(R.id.civUserCC)
        val tvDriverNameCC = view?.findViewById<TextView>(R.id.tvClientNameCC)
        val tvDriverPhoneCC = view?.findViewById<TextView>(R.id.tvClientPhoneCC)
        val tvUserData = view?.findViewById<TextView>(R.id.tvUserData)
        val tvAmountCC = view?.findViewById<TextView>(R.id.tvAmountCC)

        tvUserData?.text = "Driver data"

        Picasso.get()
            .load(notification.driver.photo)
            .error(R.drawable.ic_launcher_background)
            .into(civUserCC)

        tvDriverNameCC?.text = "Name:"+notification.driver.name
        tvDriverPhoneCC?.text = "Phone:"+notification.driver.phone
        tvAmountCC?.text = "S/."+notification.amount



        tvSubjectCC?.text = "Subject:   "+notification.subject
        tvFromCC?.text =    "From:      "+notification.from
        tvToCC?.text =      "To:        "+notification.to
        tvDateCC?.text =    "Date:      "+ notification.contractDate
        tvTimeCC?.text =    "Time:      "+ notification.timeDeparture
        tvQuantityCC?.text ="Quantity:  "+ notification.quantity

    }
    private fun paymentFormConstraints(view: View){

        val tilHolderName = view.findViewById<TextInputLayout>(R.id.til_holder)
        val tilCardNumber = view.findViewById<TextInputLayout>(R.id.til_number)

        val tilNumberLength = 20


        tilHolderName.error = " "
        tilCardNumber.error = " "

        var _holderName: String? = null
        var _cardNumber: Long? = null
        var _month: String? = null
        var _year: String? = null
        var _cvv: Int? = null

        val btPay = view.findViewById<Button>(R.id.bt_pay)
        btPay.isEnabled = false
        fun updateButtonState() {
            btPay.isEnabled = tilHolderName.error == null &&
                    tilCardNumber.error == null
        }

        tilHolderName.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 0) {
                    tilHolderName.error = "Invalid holder name"
                    tilHolderName.isErrorEnabled = true
                } else {
                    tilHolderName.error = null
                    tilHolderName.isErrorEnabled = false
                }
                updateButtonState()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){}
        })

        tilCardNumber.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 0 || s?.length != tilNumberLength) {
                    tilCardNumber.error = "Card number must have $tilNumberLength digits"
                    tilCardNumber.isErrorEnabled = true
                } else {
                    tilCardNumber.error = null
                    tilCardNumber.isErrorEnabled = false
                }
                updateButtonState()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){}

        })




        btPay.setOnClickListener {
            _holderName = tilHolderName.editText?.text.toString()
            _cardNumber = tilCardNumber.editText?.text.toString().toLong()


            val cardDetails = CardClient(
                "email@gmail.com",
                _holderName!!,
                _cardNumber!!,
                "-"+"-"+"-"+"-01",
                0,
                " ",
                _holderName!!)

            val retrofit = Retrofit.Builder()
                .baseUrl(BaseURL.BASE_URL.toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val paymentService = retrofit.create(CardService::class.java)
            val request = paymentService.postCardDriver(
                SharedPreferences(view.context).getValue("id")!!.toInt(),
                cardDetails)

            request.enqueue(object : Callback<CardClient> {
                override fun onResponse(call: Call<CardClient>, response: Response<CardClient>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Payment successful", Toast.LENGTH_SHORT).show()
                        //updateContractStatus(view)
                        Navigation.findNavController(view)
                            .navigate(R.id.action_carrierReceivePayFragment_to_id_carrier_notifications_fragment)
                    } else {
                        Toast.makeText(context, "Payment failed", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<CardClient>, t: Throwable) {
                    Toast.makeText(context, "Payment failed", Toast.LENGTH_SHORT).show()
                }
            })
        }

        val btCancel = view.findViewById<Button>(R.id.bt_cancel)

        btCancel.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_carrierReceivePayFragment_to_id_carrier_notifications_fragment)
        }

    }

}