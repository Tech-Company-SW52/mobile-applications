package com.fastporte.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.models.Contract
import com.fastporte.models.ContractDetailsPrototype
import com.fastporte.R

class CarrierHistoryContractAdapter(var contracts: List<Contract>) :
    RecyclerView.Adapter<ContractDetailsPrototype>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContractDetailsPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_history_contract, parent, false)
        return ContractDetailsPrototype(view)
    }

    override fun onBindViewHolder(holder: ContractDetailsPrototype, position: Int) {
        holder.bind(contracts[position])
    }

    override fun getItemCount(): Int {
        return contracts.size
    }

}