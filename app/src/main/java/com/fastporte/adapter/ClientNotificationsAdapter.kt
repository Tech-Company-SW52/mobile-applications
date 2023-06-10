package com.fastporte.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.models.ClientNotification

class ClientNotificationsAdapter(val notifications: List<ClientNotification>, val context: Context):RecyclerView.Adapter<ClientNotificationsAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val bt_client_notification_accepted_details=view.findViewById<Button>(R.id.bt_client_notification_accepted_details)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewAccepted = LayoutInflater.from(context).inflate(R.layout.prototype_client_notification_accepted,parent,false)
        return ViewHolder(viewAccepted)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Codigo
    }
}