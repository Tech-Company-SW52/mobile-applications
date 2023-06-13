package com.fastporte.controller.fragments.ClientFragments.ClientHome

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.controller.fragments.CarrierFragments.CarrierHome.CarrierHomeHistoryAdapter
import com.fastporte.models.Contract
import com.fastporte.models.Driver
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.NonDisposableHandle.parent

class ClientHomeHistoryAdapter(var contracts: List<Contract>, val context: Context):
RecyclerView.Adapter<ClientHomeHistoryAdapter.ViewHolder>(){
    class ViewHolder(val view: android.view.View): RecyclerView.ViewHolder(view) {
        val cvPhoto = view.findViewById<CircleImageView>(R.id.civClientProfile)
        val tvNamed = view.findViewById<android.widget.TextView>(R.id.tvClientName)
        val tvFrom = view.findViewById<android.widget.TextView>(R.id.tvLugar)
        val tvTo = view.findViewById<android.widget.TextView>(R.id.tvDestino)
        val tvTypeServices = view.findViewById<android.widget.TextView>(R.id.tvTypeService)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contract = contracts[position]
        holder.tvNamed.text = contract.driver.name
        //holder.tvFrom.text = contract.from
        //holder.tvTo.text = contract.to
        //holder.tvTypeServices.text = contract.subject
        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build()
            .load(contract.driver.photo)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.cvPhoto)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_contracts_home, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return contracts.size
    }
}