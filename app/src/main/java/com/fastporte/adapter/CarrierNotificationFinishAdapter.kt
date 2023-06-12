package com.fastporte.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.models.DriverNotification

class CarrierNotificationFinishAdapter(val notifications: List<DriverNotification>, val context: Context, val listener: NotificationAdapterFinishListener):RecyclerView.Adapter<CarrierNotificationFinishAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val bt_client_notification_accepted_details=view.findViewById<Button>(R.id.bt_carrier_notification_request_details)

    }
    interface NotificationAdapterFinishListener {
        fun onButtonFinishClick(driverNotification: DriverNotification, view: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.prototype_carrier_notification_finished,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification=notifications[position]
        holder.bt_client_notification_accepted_details.setOnClickListener(){
            listener.onButtonFinishClick(notification, holder.view)
        }
    }

}