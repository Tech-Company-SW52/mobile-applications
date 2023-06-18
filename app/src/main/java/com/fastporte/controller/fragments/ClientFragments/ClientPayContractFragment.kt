package com.fastporte.controller.fragments.ClientFragments

import android.annotation.SuppressLint
import android.content.Intent
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
import com.fastporte.controller.activities.MainActivity
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

class ClientPayContractFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_client_pay_contract, container, false)


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
                    showData(response.body()!!)
                }
                else    {
                    println("FALLO: " + response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<ClientNotification>, t: Throwable) {
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
        val tvClientNameCC = view?.findViewById<TextView>(R.id.tvClientNameCC)
        val tvClientPhoneCC = view?.findViewById<TextView>(R.id.tvClientPhoneCC)
        val tvUserData = view?.findViewById<TextView>(R.id.tvUserData)
        val tvAmountCC = view?.findViewById<TextView>(R.id.tvAmountCC)

        tvUserData?.text = "Driver data"

        Picasso.get()
            .load(notification.driver.photo)
            .error(R.drawable.ic_launcher_background)
            .into(civUserCC)

        tvClientNameCC?.text = "Name:"+notification.driver.name
        tvClientPhoneCC?.text = "Phone:"+notification.driver.phone
        tvAmountCC?.text = "S/."+notification.amount



        tvSubjectCC?.text = "Subject:   "+notification.subject
        tvFromCC?.text =    "From:      "+notification.from
        tvToCC?.text =      "To:        "+notification.to
        tvDateCC?.text =    "Date:      "+ notification.contractDate
        tvTimeCC?.text =    "Time:      "+ notification.timeDeparture
        tvQuantityCC?.text ="Quantity:  "+ notification.quantity

    }

    private fun paymentFormConstraints(view: View){
        val tilYear = view.findViewById<TextInputLayout>(R.id.til_year)
        val tilMonth = view.findViewById<TextInputLayout>(R.id.til_month)
        val tilCvv = view.findViewById<TextInputLayout>(R.id.til_cvv)
        val tilHolderName = view.findViewById<TextInputLayout>(R.id.til_holder)
        val tilCardNumber = view.findViewById<TextInputLayout>(R.id.til_number)

        val tilNumberLength = 16
        val tilYearLength = 4
        val tilMonthLength = 2
        val tilCvvLength = 3

        tilYear.error = " "
        tilMonth.error = " "
        tilCvv.error = " "
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
            btPay.isEnabled = tilYear.error == null &&
                    tilMonth.error == null &&
                    tilCvv.error == null &&
                    tilHolderName.error == null &&
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

        val tilYearEditText = tilYear.editText
        tilYearEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length != tilYearLength) {
                    tilYear.error = "Invalid year"
                    tilYear.isErrorEnabled = true
                } else {
                    tilYear.error = null
                    tilYear.isErrorEnabled = false
                }

                if ( (s?.length == tilYearLength) && (s.toString().toInt() < 2023)) {
                    tilYear.error = "Invalid year"
                    tilYear.isErrorEnabled = true
                } else if ( (s?.length == tilYearLength) && (s.toString().toInt() >= 2023)) {
                    tilYear.error = null
                    tilYear.isErrorEnabled = false
                }

                updateButtonState()

            }
        })

        val tilCvvEditText = tilCvv.editText
        tilCvvEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length != tilCvvLength) {
                    tilCvv.error = "Invalid CVV"
                    tilCvv.isErrorEnabled = true
                } else {
                    tilCvv.error = null
                    tilCvv.isErrorEnabled = false
                }
                updateButtonState()
            }
        })

        val tilMonthEditText = tilMonth.editText
        tilMonthEditText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length != tilMonthLength) {
                    tilMonth.error = "Invalid month"
                    tilMonth.isErrorEnabled = true
                } else {
                    tilMonth.error = null
                    tilMonth.isErrorEnabled = false
                }

                if ( (s?.length == tilMonthLength) && (s.toString().toInt() > 12)) {
                    tilMonth.error = "Invalid month"
                    tilMonth.isErrorEnabled = true
                } else if ( (s?.length == tilMonthLength) && (s.toString().toInt() <= 12)) {
                    tilMonth.error = null
                    tilMonth.isErrorEnabled = false
                }
                updateButtonState()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){}
        })

        btPay.setOnClickListener {
            _holderName = tilHolderName.editText?.text.toString()
            _cardNumber = tilCardNumber.editText?.text.toString().toLong()
            _month = tilMonth.editText?.text.toString()
            _year = tilYear.editText?.text.toString()
            _cvv = tilCvv.editText?.text.toString().toInt()

            val cardDetails = CardClient(
                "email@gmail.com",
                _holderName!!,
                _cardNumber!!,
                "1000"+"-"+"01"+"-01",
                _cvv!!,
                " ",
                _holderName!!)

            val retrofit = Retrofit.Builder()
                .baseUrl(BaseURL.BASE_URL.toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val paymentService = retrofit.create(CardService::class.java)
            val request = paymentService.postCardClient(
                    SharedPreferences(view.context).getValue("id")!!.toInt(),
                    cardDetails)

            request.enqueue(object : Callback<CardClient> {
                override fun onResponse(call: Call<CardClient>, response: Response<CardClient>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Payment successful", Toast.LENGTH_SHORT).show()
                        //updateContractStatus(view)
                        Navigation.findNavController(view)
                            .navigate(R.id.action_fragment_client_pay_to_fragment_client_pay_success)
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
                .navigate(R.id.action_fragment_client_pay_to_fragment_client_pay_success)
        }

    }

    private fun updateContractStatus(view: View){
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL.BASE_URL.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val contractService = retrofit.create(CardService::class.java)
        val request = contractService.updateContractStatus(
            SharedPreferences(view.context).getValue("idNotification")!!.toInt(), 2)

        request.enqueue(object : Callback<CardClient> {
            override fun onResponse(call: Call<CardClient>, response: Response<CardClient>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Contract status updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Contract status update failed", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<CardClient>, t: Throwable) {
                Toast.makeText(context, "Contract status update failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}