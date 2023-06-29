package com.fastporte.models

import android.annotation.SuppressLint
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.adapter.ClientNotificationsAdapter
import com.fastporte.helpers.BaseURL
import com.fastporte.helpers.SharedPreferences
import com.fastporte.network.ContractsService
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContractCardPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BaseURL.BASE_URL.toString())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val contractsService: ContractsService = retrofit.create(ContractsService::class.java)

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



    fun responseService(request: Call<Contract>) {
        request.enqueue(object : Callback<Contract> {
            override fun onFailure(call: Call<Contract>, t: Throwable) {
                Log.d("Activity Fail", "Error: $t")
            }

            override fun onResponse(
                call: Call<Contract>,
                response: Response<Contract>
            ) {
                if (response.isSuccessful) {
                    val contract: Contract = response.body()!!
                    Log.d("Activity success", "Success: $contract")
                } else {
                    Log.d("Activity fail", "Error: " + response.code())
                }
            }
        })
    }

    fun responseServiceRedired(request: Call<Contract>, view_: View) {
        request.enqueue(object : Callback<Contract> {
            override fun onFailure(call: Call<Contract>, t: Throwable) {
                Log.d("Activity Fail", "Error: $t")
            }

            override fun onResponse(
                call: Call<Contract>,
                response: Response<Contract>
            ) {
                if (response.isSuccessful) {
                    val contract: Contract = response.body()!!
                    saveSharedPreferences("to",contract.to, view_)
                    saveSharedPreferences("from", contract.from, view_)

                    Navigation.findNavController(view_)
                        .navigate(
                            R.id.action_id_client_contracts_fragment_to_fragment_Map
                        )

                    Log.d("Activity success", "Success: $contract")
                } else {
                    Log.d("Activity fail", "Error: " + response.code())
                }
            }
        })
    }


    @SuppressLint("SetTextI18n")
    @Suppress("DEPRECATION")
    fun bind(contract: Contract) {
        tvSubject.text = "Subject: ${contract.subject}"
        tvFrom.text = Html.fromHtml("From: <b>${contract.from}</b>")
        tvTo.text = Html.fromHtml("To: <b>${contract.to}</b>")
//        val parser = SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy")
//        val date: Date = parser.parse(contract.date.toString())
//        val formatter = SimpleDateFormat("dd/MM/yyyy")
//        val formattedDate: String = formatter.format(date)
        tvDate.text = Html.fromHtml("Date: <b>${contract.date}</b>")
        tvTime.text =
            Html.fromHtml("Time: <b>${contract.timeDeparture} - ${contract.timeArrival}</b>")
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

            // Decline button
            val btDeclineClient = itemView.findViewById<Button>(R.id.btDeclineClient)
            btDeclineClient.setOnClickListener {
                // Activar la notificación de cancelación de servicio
                if (!contract.notification.readStatus) {
                    val request = contractsService.changeNotificationStatus(contract.id, "json")
                    responseService(request)
                }
                // Cambiar visibilidad del contrato
                val request2 = contractsService.changeVisible(contract.id, "json")
                responseService(request2)
            }
            val btMapClient = itemView.findViewById<Button>(R.id.btMapClient)
            btMapClient.setOnClickListener {
                // Traer la información del contrato para guardarlo y redirigirlo al fragment
                val request = contractsService.getContractsById(contract.id, "json")
                responseServiceRedired(request,itemView)
            }

        } else {
            tvClientName.text = "${contract.client.name} ${contract.client.lastname}"
            tvClientPhone.text = "Phone: ${contract.client.phone}"
            picBuilder.downloader(OkHttp3Downloader(itemView.context))
            picBuilder.build()
                .load(contract.client.photo)
                .into(civUserCC)

            if (contract.status.id == 1) {
                // Decline button
                val btDeclineDriver = itemView.findViewById<Button>(R.id.btDeclineDriver)
                btDeclineDriver.setOnClickListener {
                    // Activar la notificación del rechazo del servicio
                    if (!contract.notification.readStatus) {
                        val request = contractsService.changeNotificationStatus(contract.id, "json")
                        responseService(request)
                    }
                    // Cambiar visibilidad del contrato
                    val request2 = contractsService.changeVisible(contract.id, "json")
                    responseService(request2)
                }
                // Accept button
                val btAcceptDriver = itemView.findViewById<Button>(R.id.btAcceptDriver)
                btAcceptDriver.setOnClickListener {
                    // Activar la notificación de la aceptación del servicio
                    if (!contract.notification.readStatus) {
                        val request = contractsService.changeNotificationStatus(contract.id, "json")
                        responseService(request)
                    }
                    // Cambiar estado del contrato a pending
                    val request2 = contractsService.updateContractStatus(contract.id, 2, "json")
                    responseService(request2)
                }
            }
        }
    }
    private fun saveSharedPreferences(KeyName: String, value: String, view: View) {
        val sharedPreferences = SharedPreferences(view.context)
        sharedPreferences.removeValue(KeyName)
        sharedPreferences.save(KeyName, value)
    }
}