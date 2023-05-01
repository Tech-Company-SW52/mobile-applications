package com.fastport.ClientFragments.ClientContracts.RecyclerViewContracts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastport.Contracts.Contract
import com.fastport.R

class ClientHistoryContractAdapter(var contracts: ArrayList<Contract>) :
    RecyclerView.Adapter<ClientHistoryContractPrototype>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClientHistoryContractPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_client_history_contract, parent, false)
        return ClientHistoryContractPrototype(view)
    }

    override fun onBindViewHolder(holder: ClientHistoryContractPrototype, position: Int) {
        holder.bind(contracts.get(position))
    }

    override fun getItemCount(): Int {
        return contracts.size
    }

}

class ClientHistoryContractPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvDate = itemView.findViewById<TextView>(R.id.tvDateCD)
    val tvFrom = itemView.findViewById<TextView>(R.id.tvFromCD)
    val tvTo = itemView.findViewById<TextView>(R.id.tvToCD)

    fun bind(contract: Contract) {
        tvFrom.text = "From: ${contract.from}"
        tvTo.text = "To: ${contract.to}"
        tvDate.text = "Date: ${contract.date}"
    }
}