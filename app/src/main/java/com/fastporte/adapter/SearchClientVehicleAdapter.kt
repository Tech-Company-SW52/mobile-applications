package com.fastporte.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.models.Vehicle
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class SearchClientVehicleAdapter(val vehicles: List<Vehicle>,val context: Context): RecyclerView.Adapter<SearchClientVehicleAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val ivSearchClientPhotoVehicle =
            view.findViewById<CircleImageView>(R.id.ivSearchClientPhotoVehicle)
        val tvSearchClientTypeVehicle = view.findViewById<TextView>(R.id.tvSearchClientTypeVehicle)
        val tvSearchClientVehicleCapacity =
            view.findViewById<TextView>(R.id.tvSearchClientVehicleCapacity)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.prototype_client_search_profile_vehicle, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.tvSearchClientTypeVehicle.text = vehicle.type
        holder.tvSearchClientVehicleCapacity.text = vehicle.quantity.toString()

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader((OkHttp3Downloader(context)))
        picBuilder.build().load(vehicle.photo).placeholder(R.color.white).error(R.color.white)
            .into(holder.ivSearchClientPhotoVehicle)

    }
}