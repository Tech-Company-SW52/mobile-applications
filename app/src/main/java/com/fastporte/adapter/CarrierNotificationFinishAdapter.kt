package com.fastporte.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.models.DriverNotification

class CarrierNotificationFinishAdapter(val notifications: List<DriverNotification>,
                                       val context: Context,
                                       val listener: NotificationAdapterFinishListener
                                       ):RecyclerView.Adapter<CarrierNotificationFinishAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val bt_carrier_notification_request_details=view.findViewById<Button>(R.id.bt_carrier_notification_request_details)
        val bt_carrier_notification_finished_details=view.findViewById<Button>(R.id.bt_carrier_notification_finished_details)

    }

    interface NotificationAdapterFinishListener {
        fun onButtonFinishClick(driverNotification: DriverNotification, view: View)

    }


    override fun getItemViewType(position: Int): Int {
        val notification = notifications[position]
        val viewType = when {
            isAcceptedNotification(notification) -> VIEW_TYPE_OFFER
            isFinishNotification(notification) -> VIEW_TYPE_HISTORY
            else -> NULL
        }
        return viewType
    }
    fun isAcceptedNotification(notification: DriverNotification): Boolean {
        return notification.visible && notification.status.status == "OFFER"
    }
    fun isFinishNotification(notification: DriverNotification): Boolean {
        return notification.visible && notification.status.status == "HISTORY"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutResId = when (viewType) {
            VIEW_TYPE_OFFER -> R.layout.prototype_carrier_notification_request
            VIEW_TYPE_HISTORY -> R.layout.prototype_carrier_notification_finished
            else -> R.layout.prototype_carrier_notification_request
        }
        val view = LayoutInflater.from(context).inflate(layoutResId,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification=notifications[position]
        if (isAcceptedNotification(notification)){
            holder.bt_carrier_notification_request_details.setOnClickListener(){
                listener.onButtonFinishClick(notification, holder.view)
            }
        }
        if (isFinishNotification(notification)){
            holder.bt_carrier_notification_finished_details.setOnClickListener(){
                listener.onButtonFinishClick(notification, holder.view)
            }
        }

    }
    companion object {
        private const val VIEW_TYPE_OFFER = 0
        private const val VIEW_TYPE_HISTORY = 1
        private const val NULL = 2
    }
}