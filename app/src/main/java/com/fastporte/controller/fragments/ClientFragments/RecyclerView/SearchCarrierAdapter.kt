package com.fastporte.controller.fragments.ClientFragments.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.models.User
import com.fastporte.models.Vehicle
import com.squareup.picasso.Picasso
import retrofit2.Call

class SearchCarrierAdapter(var users: ArrayList<Vehicle>):
        RecyclerView.Adapter<SearchCarrierPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCarrierPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_search_carrier, parent, false)
        return  SearchCarrierPrototype(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: SearchCarrierPrototype, position: Int) {
        holder.bind(users.get(position))
    }

}

class SearchCarrierPrototype(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
    val Imagenperson = itemView.findViewById<ImageView>(R.id.civProfileImage)
    val tvName = itemView. findViewById<TextView>(R.id.tvName)
    val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    fun bind(vehicle: Vehicle) {
        tvName.text = vehicle.driver.name
        tvDescription.text = vehicle.driver.description

        Picasso.get().load(vehicle.driver.photo)
            .error(R.mipmap.ic_launcher_round)
            .into(Imagenperson)
    }

}
