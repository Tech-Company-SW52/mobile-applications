package com.fastporte.models

import android.annotation.SuppressLint
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.controller.fragments.ClientFragments.ClientContracts.MakeCommentDialogFragment
import com.fastporte.helpers.SharedPreferences
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ContractDetailsPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvFrom: TextView = itemView.findViewById(R.id.tvFromCD)
    private val tvTo: TextView = itemView.findViewById(R.id.tvToCD)
    private val tvDate: TextView = itemView.findViewById(R.id.tvDateCD)
    private val tvAmount: TextView = itemView.findViewById(R.id.tvAmountCD)
    private val tvClientName: TextView = itemView.findViewById(R.id.tvClientNameCD)
    private val tvUser: TextView = itemView.findViewById(R.id.tvUser)
    private val civUserCD: CircleImageView = itemView.findViewById(R.id.civUserCD)
    private val sharedPreferences = SharedPreferences(itemView.context)

    @SuppressLint("SetTextI18n")
    @Suppress("DEPRECATION")
    fun bind(contract: Contract) {
        tvFrom.text = Html.fromHtml("From: <b>${contract.from}</b>")
        tvTo.text = Html.fromHtml("To: <b>${contract.to}</b>")
        tvDate.text = Html.fromHtml("Date: <b>${contract.date}</b>")
        tvAmount.text = Html.fromHtml("Amount: <b>S/.${contract.amount}</b>")

        val picBuilder = Picasso.Builder(itemView.context)

        if (sharedPreferences.getValue("typeUser") == "client") {
            tvUser.text = "Driver: "
            tvClientName.text = "${contract.driver.name} ${contract.driver.lastname}"

            val tvStatus = itemView.findViewById<TextView>(R.id.tvStatusC)

            picBuilder.downloader(OkHttp3Downloader(itemView.context))
            picBuilder.build()
                .load(contract.driver.photo)
                .into(civUserCD)

            val makeCommentDialog = MakeCommentDialogFragment(contract)

            civUserCD.setOnClickListener {
                makeCommentDialog.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "MakeCommentDialogFragment"
                )
            }

            tvStatus.text = "Status: Done"
            tvStatus.setTextColor(itemView.resources.getColor(R.color.accept))
        } else {
            tvClientName.text = "${contract.client.name} ${contract.client.lastname}"
            picBuilder.downloader(OkHttp3Downloader(itemView.context))
            picBuilder.build()
                .load(contract.client.photo)
                .into(civUserCD)
        }
    }
}