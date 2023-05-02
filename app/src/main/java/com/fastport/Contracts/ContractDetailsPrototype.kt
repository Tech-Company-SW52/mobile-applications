package com.fastport.Contracts

import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastport.R

class ContractDetailsPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvFrom = itemView.findViewById<TextView>(R.id.tvFromCD)
    val tvTo = itemView.findViewById<TextView>(R.id.tvToCD)
    val tvDate = itemView.findViewById<TextView>(R.id.tvDateCD)
    val tvAmount = itemView.findViewById<TextView>(R.id.tvAmountCD)

    val tvClientName = itemView.findViewById<TextView>(R.id.tvClientNameCD)

    @Suppress("DEPRECATION")
    fun bind(contract: Contract) {
        tvFrom.text = Html.fromHtml("From: <b>${contract.from}</b>")
        tvTo.text = Html.fromHtml("To: <b>${contract.to}</b>")
        tvDate.text = Html.fromHtml("Date: <b>${contract.date}</b>")
        tvAmount.text = Html.fromHtml("Amount: <b>S/.${contract.amount}</b>")
        tvClientName.text = "${contract.client}"
    }
}