package com.fastporte.controller.fragments.CarrierFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.adapter.CarrierNotificationFinishAdapter
import com.fastporte.adapter.CarrierNotificationRequestAdapter
import com.fastporte.adapter.ClientNotificationsAdapter
import com.fastporte.adapter.ClientNotificationsDenniedAdapter
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.ClientNotification
import com.fastporte.models.DriverNotification
import com.fastporte.network.NotificationService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarrierNotificationsFragment : Fragment(),
    CarrierNotificationFinishAdapter.NotificationAdapterFinishListener {
    lateinit var notificationFinishedRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_carrier_notifications, container, false)
        notificationFinishedRecyclerView = view.findViewById(R.id.rv_carrier_notifications_finish)
        loadNotifications(view)
        return view
    }

    private fun loadNotifications(view_: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-fastporte.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(NotificationService::class.java)
        val request = service.getDriverNotifiacations(
            SharedPreferences(view_.context).getValue("id")!!.toInt(),
            "json"
        )
        request.enqueue(object : Callback<List<DriverNotification>> {
            override fun onResponse(
                call: Call<List<DriverNotification>>,
                response: Response<List<DriverNotification>>
            ) {
                val notificationList = response.body()
                if (notificationList != null) {
                    val finishedNotifications = mutableListOf<DriverNotification>()

                    for (notification in notificationList) {
                        if (isValidNotification(notification)) {
                            finishedNotifications.add(notification)
                        }
                    }

                    notificationFinishedRecyclerView.adapter = CarrierNotificationFinishAdapter(
                        finishedNotifications,
                        requireContext(),
                        this@CarrierNotificationsFragment
                    )
                    notificationFinishedRecyclerView.layoutManager = LinearLayoutManager(context)

                }
            }

            override fun onFailure(call: Call<List<DriverNotification>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun isValidNotification(notification: DriverNotification): Boolean {
        return (notification.visible && notification.status.status == "OFFER") ||
                (notification.status.status == "HISTORY")
    }


    override fun onButtonFinishClick(driverNotification: DriverNotification, view: View) {
        //CODIGO
        if (driverNotification.status.status == "OFFER") {

            Navigation.findNavController(view)
                .navigate(R.id.action_id_carrier_notifications_fragment_to_id_carrier_contracts_fragment)
        }
        if (driverNotification.status.status == "HISTORY") {

            saveSharedPreferences("idNotification", driverNotification.id.toString(), view)
            Navigation.findNavController(view)
                .navigate(R.id.action_id_carrier_notifications_fragment_to_carrierReceivePayFragment)
        }

    }

    private fun saveSharedPreferences(KeyName: String, value: String, view: View) {
        val sharedPreferences = SharedPreferences(view.context)
        sharedPreferences.removeValue(KeyName)
        sharedPreferences.save(KeyName, value)
    }

}