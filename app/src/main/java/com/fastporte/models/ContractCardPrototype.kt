package com.fastporte.models

import android.annotation.SuppressLint
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.helpers.SharedPreferences
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.Date

class ContractCardPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvSubject: TextView = itemView.findViewById(R.id.tvSubjectCC)
    private val tvFrom: TextView = itemView.findViewById(R.id.tvFromCC)
    private val tvTo: TextView = itemView.findViewById(R.id.tvToCC)
    private val tvDate: TextView = itemView.findViewById(R.id.tvDateCC)
    private val tvTime: TextView = itemView.findViewById(R.id.tvTimeCC)
    private val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantityCC)
    private val tvAmount: TextView = itemView.findViewById(R.id.tvAmountCC)
    private val tvClientName: TextView = itemView.findViewById(R.id.tvClientNameCC)
    private val tvClientPhone: TextView = itemView.findViewById(R.id.tvClientPhoneCC)
    private val tvUserData: TextView = itemView.findViewById(R.id.tvUserData)
    private val civUserCC: CircleImageView = itemView.findViewById(R.id.civUserCC)
    private val sharedPreferences = SharedPreferences(itemView.context)

    @SuppressLint("SetTextI18n")
    @Suppress("DEPRECATION")
    fun bind(contract: Contract) {
        tvSubject.text = "Subject: ${contract.subject}"
        tvFrom.text = Html.fromHtml("From: <b>${contract.from}</b>")
        tvTo.text = Html.fromHtml("To: <b>${contract.to}</b>")
        val parser = SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy")
        val date: Date = parser.parse(contract.date.toString())
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val formattedDate: String = formatter.format(date)
        tvDate.text = Html.fromHtml("Date: <b>${formattedDate}</b>")
        tvTime.text = Html.fromHtml("Time: <b>${contract.timeDeparture} - ${contract.timeArrival}</b>")
        tvQuantity.text = Html.fromHtml("Quantity: <b>${contract.quantity}</b>")
        tvAmount.text = "S/. ${contract.amount}"

        val picBuilder = Picasso.Builder(itemView.context)

        if (sharedPreferences.getValue("typeUser") == "client") {
            tvUserData.text = "Driver Data"
            tvClientName.text = "${contract.driver.name} ${contract.driver.lastname}"
            tvClientPhone.text = "Phone: ${contract.driver.phone}"
            picBuilder.downloader(OkHttp3Downloader(itemView.context))
            picBuilder.build()
                .load(contract.driver.photo)
                .into(civUserCC)
        } else {
            tvClientName.text = "${contract.client.name} ${contract.client.lastname}"
            tvClientPhone.text = "Phone: ${contract.client.phone}"
            picBuilder.downloader(OkHttp3Downloader(itemView.context))
            picBuilder.build()
                .load(contract.client.photo)
                .into(civUserCC)
        }
    }
}