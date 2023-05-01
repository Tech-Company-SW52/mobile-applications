package com.fastport.CarrierFragments.CarrierContracts.RecyclerViewContracts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastport.Contracts.Contract
import com.fastport.R

class CarrierPendingContractAdapter(var contracts: ArrayList<Contract>) :
    RecyclerView.Adapter<CarrierPendingContractPrototype>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarrierPendingContractPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_pending_contract, parent, false)
        return CarrierPendingContractPrototype(view)
    }

    override fun onBindViewHolder(holder: CarrierPendingContractPrototype, position: Int) {
        holder.bind(contracts.get(position))
    }

    override fun getItemCount(): Int {
        return contracts.size
    }

}

class CarrierPendingContractPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvSubject = itemView.findViewById<TextView>(R.id.tvSubjectCC)
    val tvFrom = itemView.findViewById<TextView>(R.id.tvFromCC)
    val tvTo = itemView.findViewById<TextView>(R.id.tvToCC)
    val tvDate = itemView.findViewById<TextView>(R.id.tvDateCC)
    val tvTime = itemView.findViewById<TextView>(R.id.tvTimeCC)
    val tvQuantity = itemView.findViewById<TextView>(R.id.tvQuantityCC)

    fun bind(contract: Contract) {
        tvSubject.text = "Subject: ${contract.subject}"
        tvFrom.text = "From: ${contract.from}"
        tvTo.text = "To: ${contract.to}"
        tvDate.text = "Date: ${contract.date}"
        tvTime.text = "Time: ${contract.timeDeparture} - ${contract.timeArrival}"
        tvQuantity.text = "Quantity: ${contract.quantity}"
    }
}