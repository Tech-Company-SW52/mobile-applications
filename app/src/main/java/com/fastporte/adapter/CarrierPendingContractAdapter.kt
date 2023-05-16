package com.fastporte.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.models.Contract
import com.fastporte.models.ContractCardPrototype
import com.fastporte.R

class CarrierPendingContractAdapter(var contracts: List<Contract>) :
    RecyclerView.Adapter<ContractCardPrototype>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContractCardPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_pending_contract, parent, false)
        return ContractCardPrototype(view)
    }

    override fun onBindViewHolder(holder: ContractCardPrototype, position: Int) {
        holder.bind(contracts[position])
    }

    override fun getItemCount(): Int {
        return contracts.size
    }

}