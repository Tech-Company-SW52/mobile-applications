package com.fastport.CarrierFragments.CarrierContracts.RecyclerViewContracts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fastport.Contracts.Contract
import com.fastport.Contracts.ContractCardPrototype
import com.fastport.R

class OfferContractAdapter(var contracts: ArrayList<Contract>) :
    RecyclerView.Adapter<ContractCardPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractCardPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_offer_contract, parent, false)
        return ContractCardPrototype(view)
    }

    override fun onBindViewHolder(holder: ContractCardPrototype, position: Int) {
        holder.bind(contracts.get(position))
    }

    override fun getItemCount(): Int {
        return contracts.size
    }

}

//class OfferContractPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    val tvSubject = itemView.findViewById<TextView>(R.id.tvSubjectCC)
//    val tvFrom = itemView.findViewById<TextView>(R.id.tvFromCC)
//    val tvTo = itemView.findViewById<TextView>(R.id.tvToCC)
//    val tvDate = itemView.findViewById<TextView>(R.id.tvDateCC)
//    val tvTime = itemView.findViewById<TextView>(R.id.tvTimeCC)
//    val tvQuantity = itemView.findViewById<TextView>(R.id.tvQuantityCC)
//
//    fun bind(contract: Contract) {
//        tvSubject.text = "Subject: ${contract.subject}"
//        tvFrom.text = "From: ${contract.from}"
//        tvTo.text = "To: ${contract.to}"
//        tvDate.text = "Date: ${contract.date}"
//        tvTime.text = "Time: ${contract.timeDeparture} - ${contract.timeArrival}"
//        tvQuantity.text = "Quantity: ${contract.quantity}"
//    }
//}