package com.fastport.ClientFragments.ClientContracts.RecyclerViewContracts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fastport.Contracts.Contract
import com.fastport.Contracts.ContractCardPrototype
import com.fastport.R

class ClientPendingContractAdapter(var contracts: ArrayList<Contract>) :
    RecyclerView.Adapter<ContractCardPrototype>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContractCardPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_client_pending_contract, parent, false)
        return ContractCardPrototype(view)
    }

    override fun onBindViewHolder(holder: ContractCardPrototype, position: Int) {
        holder.bind(contracts.get(position))
    }

    override fun getItemCount(): Int {
        return contracts.size
    }

}