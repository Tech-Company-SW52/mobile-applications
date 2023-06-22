package com.fastporte.controller.fragments.ClientFragments

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
import com.fastporte.adapter.ClientNotificationsAdapter
import com.fastporte.adapter.ClientNotificationsDenniedAdapter
import com.fastporte.adapter.SearchClientCommentsAdapter
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.ClientNotification
import com.fastporte.network.NotificationService
import com.fastporte.network.ProfileService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientNotificationsFragment : Fragment(),
    ClientNotificationsAdapter.NotificationAdapterListener {
    lateinit var notificationAcceptedRecyclerView: RecyclerView
    lateinit var notificationDenniedRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_client_notifications, container, false)
        notificationAcceptedRecyclerView = view.findViewById(R.id.rv_client_notifications_accepted)
        notificationDenniedRecyclerView = view.findViewById(R.id.rv_client_notifications_dennied)
        loadNotifications(view)
        return view
    }

    private fun loadNotifications(view_: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-fastporte.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(NotificationService::class.java)
        val request = service.getClientNotifiacations(
            SharedPreferences(view_.context).getValue("id")!!.toInt(),
            "json"
        )
        request.enqueue(object : Callback<List<ClientNotification>> {
            override fun onResponse(
                call: Call<List<ClientNotification>>,
                response: Response<List<ClientNotification>>
            ) {
                val notificationList = response.body()
                if (notificationList != null) {
                    val visibleNotifications = mutableListOf<ClientNotification>()
                    val invisibleNotifications = mutableListOf<ClientNotification>()

                    for (notification in notificationList) {
                        if (isValidNotification(notification)) {
                            visibleNotifications.add(notification)
                        }
                    }
                    notificationAcceptedRecyclerView.adapter = ClientNotificationsAdapter(
                        visibleNotifications,
                        requireContext(),
                        this@ClientNotificationsFragment
                    )
                    notificationAcceptedRecyclerView.layoutManager = LinearLayoutManager(context)

                }
            }

            override fun onFailure(call: Call<List<ClientNotification>>, t: Throwable) {
                // Manejar el fallo de la solicitud
                Toast.makeText(
                    context, "There was an error loading the notifications",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun isValidNotification(notification: ClientNotification): Boolean {
        return (notification.visible && notification.status.status == "PENDING") ||
                (!notification.visible && notification.status.status == "OFFER")
    }

    override fun onButtonClick(clientNotification: ClientNotification, view: View) {
        Toast.makeText(context, clientNotification.id.toString(), Toast.LENGTH_SHORT).show()
        saveSharedPreferences("idNotification", clientNotification.id.toString(), view)
        Navigation.findNavController(view)
            .navigate(R.id.action_clientNotification_to_clientNotificationPayFragment)

    }

    private fun saveSharedPreferences(KeyName: String, value: String, view: View) {
        val sharedPreferences = SharedPreferences(view.context)
        sharedPreferences.removeValue(KeyName)
        sharedPreferences.save(KeyName, value)
    }
}