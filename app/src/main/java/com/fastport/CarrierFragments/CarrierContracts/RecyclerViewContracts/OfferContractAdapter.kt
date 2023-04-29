package com.fastport.Contracts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastport.R

class OfferContractAdapter(var contracts: ArrayList<Contract>) : RecyclerView.Adapter<OfferContractPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferContractPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_offer_contract, parent, false)
        return OfferContractPrototype(view)
    }

    override fun onBindViewHolder(holder: OfferContractPrototype, position: Int) {
        holder.bind(contracts.get(position))
    }

    override fun getItemCount(): Int {
        return contracts.size
    }

}

class OfferContractPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvSubject = itemView.findViewById<TextView>(R.id.tvSubjectCC)
    val tvFrom = itemView.findViewById<TextView>(R.id.tvFromCC)

    fun bind(contract: Contract) {
        tvSubject.text = "Subject: ${contract.subject}"
        tvFrom.text = "From: ${contract.from}"
    }
}