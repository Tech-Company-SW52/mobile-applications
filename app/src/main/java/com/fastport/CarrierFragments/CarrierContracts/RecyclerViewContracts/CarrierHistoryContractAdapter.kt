package com.fastport.CarrierFragments.CarrierContracts.RecyclerViewContracts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastport.Contracts.Contract
import com.fastport.R

class CarrierHistoryContractAdapter(var contracts: ArrayList<Contract>) :
    RecyclerView.Adapter<CarrierHistoryContractPrototype>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarrierHistoryContractPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_history_contract, parent, false)
        return CarrierHistoryContractPrototype(view)
    }

    override fun onBindViewHolder(holder: CarrierHistoryContractPrototype, position: Int) {
        holder.bind(contracts.get(position))
    }

    override fun getItemCount(): Int {
        return contracts.size
    }

}

class CarrierHistoryContractPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvDate = itemView.findViewById<TextView>(R.id.tvDateCD)
    val tvFrom = itemView.findViewById<TextView>(R.id.tvFromCD)
    val tvTo = itemView.findViewById<TextView>(R.id.tvToCD)

    fun bind(contract: Contract) {
        tvFrom.text = "From: ${contract.from}"
        tvTo.text = "To: ${contract.to}"
        tvDate.text = "Date: ${contract.date}"
    }
}