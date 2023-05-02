package com.fastport.Contracts

import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastport.R

class ContractCardPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvSubject = itemView.findViewById<TextView>(R.id.tvSubjectCC)
    val tvFrom = itemView.findViewById<TextView>(R.id.tvFromCC)
    val tvTo = itemView.findViewById<TextView>(R.id.tvToCC)
    val tvDate = itemView.findViewById<TextView>(R.id.tvDateCC)
    val tvTime = itemView.findViewById<TextView>(R.id.tvTimeCC)
    val tvQuantity = itemView.findViewById<TextView>(R.id.tvQuantityCC)
    val tvAmount = itemView.findViewById<TextView>(R.id.tvAmountCC)

    val tvClientName = itemView.findViewById<TextView>(R.id.tvClientNameCC)
    val tvClientPhone = itemView.findViewById<TextView>(R.id.tvClientPhoneCC)

    @Suppress("DEPRECATION")
    fun bind(contract: Contract) {
        tvSubject.text = "Subject: ${contract.subject}"
        tvFrom.text = Html.fromHtml("From: <b>${contract.from}</b>")
        tvTo.text = Html.fromHtml("To: <b>${contract.to}</b>")
        tvDate.text = Html.fromHtml("Date: <b>${contract.date}</b>")
        tvTime.text =
            Html.fromHtml("Time: <b>${contract.timeDeparture} - ${contract.timeArrival}</b>")
        tvQuantity.text = Html.fromHtml("Quantity: <b>${contract.quantity}</b>")
        tvAmount.text = "S/. ${contract.amount}"
        tvClientName.text = "${contract.client}"
        tvClientPhone.text = "Phone: ${contract.phone}"
    }
}