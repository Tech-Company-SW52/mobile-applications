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
import com.fastporte.models.DriverNotification
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class ClientNotificationsAdapter(
    val notifications: List<ClientNotification>,
    val context: Context,
    val listener: NotificationAdapterListener
) : RecyclerView.Adapter<ClientNotificationsAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val bt_client_notification_accepted_details =
            view.findViewById<Button>(R.id.bt_client_notification_accepted_details)

        val iv_client_notification_reject_profile =
            view.findViewById<ImageView>(R.id.iv_client_notification_reject_profile)
        val tv_client_notification_reject_name =
            view.findViewById<TextView>(R.id.tv_client_notification_reject_name)
        val tv_client_notification_reject_date =
            view.findViewById<TextView>(R.id.tv_client_notification_reject_date)
        val tv_client_notification_reject_from =
            view.findViewById<TextView>(R.id.tv_client_notification_reject_from)
        val tv_client_notification_reject_to =
            view.findViewById<TextView>(R.id.tv_client_notification_reject_to)

    }

    interface NotificationAdapterListener {
        fun onButtonClick(clientNotification: ClientNotification, view: View)
    }

    override fun getItemViewType(position: Int): Int {
        val notification = notifications[position]
        val viewType = when {
            isAcceptedNotification(notification) -> VIEW_TYPE_ACCEPTED
            else -> VIEW_TYPE_OTHER
        }
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutResId = when (viewType) {
            VIEW_TYPE_ACCEPTED -> R.layout.prototype_client_notification_accepted
            else -> R.layout.prototype_client_notification_rejected
        }
        val view = LayoutInflater.from(context).inflate(layoutResId, parent, false)
        return ViewHolder(view)
    }

    fun isAcceptedNotification(notification: ClientNotification): Boolean {
        return notification.visible && notification.status.status == "PENDING"
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notifications[position]
        if (isAcceptedNotification(notification)) {
            holder.bt_client_notification_accepted_details.setOnClickListener() {
                listener.onButtonClick(notification, holder.view)
            }
        } else {
            val picBuilder = Picasso.Builder(context)
            picBuilder.downloader((OkHttp3Downloader(context)))
            picBuilder
                .build()
                .load(notification.driver.photo)
                .placeholder(R.color.white)
                .error(R.color.white)
                .into(holder.iv_client_notification_reject_profile)

            holder.tv_client_notification_reject_name.text =
                notification.driver.name + " " + notification.driver.lastname
            holder.tv_client_notification_reject_date.text = "Date: " + notification.contractDate
            holder.tv_client_notification_reject_from.text = "From: " + notification.from
            holder.tv_client_notification_reject_to.text = "To: " + notification.to
        }
    }

    companion object {
        private const val VIEW_TYPE_ACCEPTED = 0
        private const val VIEW_TYPE_OTHER = 1
    }
}