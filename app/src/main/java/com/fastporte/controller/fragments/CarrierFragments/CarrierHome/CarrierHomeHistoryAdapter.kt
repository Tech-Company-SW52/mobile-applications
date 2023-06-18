package com.fastporte.controller.fragments.CarrierFragments.CarrierHome

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.models.Contract
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class CarrierHomeHistoryAdapter(var contracts: List<Contract>, val context: Context):
RecyclerView.Adapter<CarrierHomeHistoryAdapter.ViewHolder>() {
    class ViewHolder(val view: android.view.View): RecyclerView.ViewHolder(view){
        val cvPhoto = view.findViewById<CircleImageView>(R.id.civClientProfile)
        val tvName = view.findViewById<android.widget.TextView>(R.id.tvClientName)
        val tvFrom = view.findViewById<android.widget.TextView>(R.id.tvLugar)
        val tvTo = view.findViewById<android.widget.TextView>(R.id.tvDestino)
        val tvTypeServices = view.findViewById<android.widget.TextView>(R.id.tvTypeService)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_contracts_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contract = contracts[position]
        //holder.tvName.text = contract.client.name
        holder.tvFrom.text = contract.from
        //holder.tvTo.text = contract.to
        //holder.tvTypeServices.text = contract.subject
        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build()
            .load(contract.client.photo)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.cvPhoto)
    }

    override fun getItemCount(): Int {
        return contracts.size
    }



}