package com.fastporte.controller.fragments.ClientFragments.ClientHome

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.controller.fragments.CarrierFragments.CarrierHome.CarrierHomeAdapter
import com.fastporte.models.Driver
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class ClientHomePopularAdapter(var drivers: List<Driver>, val context: Context):
RecyclerView.Adapter<ClientHomePopularAdapter.ViewHolder>(){
    class ViewHolder(val view: android.view.View): RecyclerView.ViewHolder(view) {
        val cvPhoto =
            view.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.civDriverProfile)
        val tvName = view.findViewById<android.widget.TextView>(R.id.tvDriverName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_popular_home, parent, false)
        return ClientHomePopularAdapter.ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val driver = drivers[position]

        holder.tvName.text = driver.name + " " + driver.lastname
        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build()
            .load(driver.photo)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.cvPhoto)
    }
    override fun getItemCount(): Int {
        return 5
    }
}