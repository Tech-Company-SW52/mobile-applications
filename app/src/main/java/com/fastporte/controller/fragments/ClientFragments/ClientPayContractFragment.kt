package com.fastporte.controller.fragments.ClientFragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fastporte.R
import com.fastporte.helpers.BaseURL
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.ClientNotification
import com.fastporte.models.User
import com.fastporte.network.NotificationService
import com.fastporte.network.ProfileService
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

}