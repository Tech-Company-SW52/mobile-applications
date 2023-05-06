package com.fastport.ClientFragments.ClientContracts.RecyclerViewContracts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fastport.Contracts.Contract
import com.fastport.Contracts.ContractDetailsPrototype
import com.fastport.R

class ClientHistoryContractAdapter(var contracts: ArrayList<Contract>) :
    RecyclerView.Adapter<ContractDetailsPrototype>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContractDetailsPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_client_history_contract, parent, false)
        return ContractDetailsPrototype(view)
    }

    override fun onBindViewHolder(holder: ContractDetailsPrototype, position: Int) {
        holder.bind(contracts.get(position))
    }

    override fun getItemCount(): Int {
        return contracts.size
    }

}