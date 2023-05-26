package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.RecyclerViewProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.models.Vehicle
import com.fastporte.R
import com.squareup.picasso.Picasso

class VehicleProfileAdapter(var vehicles: ArrayList<Vehicle>) :
    RecyclerView.Adapter<VehicleProfilePrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleProfilePrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_vehicle_profile, parent, false)
        return VehicleProfilePrototype(view)
    }

    override fun onBindViewHolder(holder: VehicleProfilePrototype, position: Int) {
        holder.bind(vehicles.get(position))
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }

}

class VehicleProfilePrototype(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
    val btTypeVehicle = itemView.findViewById<Button>(R.id.btTypeVehicle)
    val btCapacity = itemView.findViewById<Button>(R.id.btCapacity)
    val ivPhotoVehicle = itemView.findViewById<ImageView>(R.id.ivPhotoVehicle)

    fun bind(vehicle: Vehicle) {
        btTypeVehicle.text = vehicle.type
        btCapacity.text = (vehicle.quantity).toString()


        Picasso.get().load(vehicle.photo)
            .error(R.mipmap.ic_launcher_round)
            .into(ivPhotoVehicle)
    }

}