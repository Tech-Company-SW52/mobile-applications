package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.RecyclerViewProfile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.models.Vehicle
import com.fastporte.R
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class VehicleProfileAdapter(var vehicles: List<Vehicle>, val context: Context) :
    RecyclerView.Adapter<VehicleProfileAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val ivVehicle = view.findViewById<ImageView>(R.id.ivPhotoVehicle)
        val btTypeVehicle = view.findViewById<Button>(R.id.btTypeVehicle)
        val btCapacity = view.findViewById<Button>(R.id.btCapacity)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_vehicle_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vehicle = vehicles[position]

        holder.btTypeVehicle.text = vehicle.type
        holder.btCapacity.text = vehicle.quantity.toString()

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build()
            .load(vehicle.photo)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.ivVehicle)
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }

}