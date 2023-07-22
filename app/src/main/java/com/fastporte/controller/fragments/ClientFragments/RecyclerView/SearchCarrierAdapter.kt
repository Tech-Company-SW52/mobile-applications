package com.fastporte.controller.fragments.ClientFragments.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.models.User
import com.fastporte.models.Vehicle
import com.squareup.picasso.Picasso
import retrofit2.Call

class SearchCarrierAdapter(
    val users: List<Vehicle>,
    val context: Context,
    val listener: SearchCarrierListener
) :
    RecyclerView.Adapter<SearchCarrierPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCarrierPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_search_carrier, parent, false)
        return SearchCarrierPrototype(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: SearchCarrierPrototype, position: Int) {
        holder.bind(users.get(position), listener)
    }

}

class SearchCarrierPrototype(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
    val Imagenperson = itemView.findViewById<ImageView>(R.id.civProfileImage)
    val tvName = itemView.findViewById<TextView>(R.id.tvName)
    val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    val btNext = itemView.findViewById<ImageButton>(R.id.btnNext)


    fun bind(vehicle: Vehicle, listener: SearchCarrierListener) {
        tvName.text = vehicle.driver.name
        tvDescription.text = vehicle.driver.description
        btNext.setOnClickListener() {
            listener.onActionsItemClick(vehicle, itemView)
        }

        Picasso.get().load(vehicle.driver.photo)
            .error(R.mipmap.ic_launcher_round)
            .into(Imagenperson)
    }

}

interface SearchCarrierListener {
    fun onActionsItemClick(vehicle: Vehicle, view_: View)
}
