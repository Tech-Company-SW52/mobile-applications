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
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class ClientNotificationsDenniedAdapter(val notifications: List<ClientNotification>, val context: Context):RecyclerView.Adapter<ClientNotificationsDenniedAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val iv_client_notification_reject_profile=view.findViewById<ImageView>(R.id.iv_client_notification_reject_profile)
        val tv_client_notification_reject_name=view.findViewById<TextView>(R.id.tv_client_notification_reject_name)
        val tv_client_notification_reject_date=view.findViewById<TextView>(R.id.tv_client_notification_reject_date)
        val tv_client_notification_reject_from=view.findViewById<TextView>(R.id.tv_client_notification_reject_from)
        val tv_client_notification_reject_to=view.findViewById<TextView>(R.id.tv_client_notification_reject_to)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDennied = LayoutInflater.from(context).inflate(R.layout.prototype_client_notification_rejected,parent,false)
        return ViewHolder(viewDennied)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notifications[position]
        val picBuilder= Picasso.Builder(context)
        picBuilder.downloader((OkHttp3Downloader(context)))
        picBuilder.build().load(notification.client.photo).placeholder(R.color.white).error(R.color.white).into(holder.iv_client_notification_reject_profile)
        holder.tv_client_notification_reject_name.text = notification.client.name
        holder.tv_client_notification_reject_date.text = "Date: "+notification.contractDate
        holder.tv_client_notification_reject_from.text = "From: "+notification.from
        holder.tv_client_notification_reject_to.text = "To: "+notification.to
    }
}