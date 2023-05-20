package com.fastporte.helpers

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.adapter.CarrierHistoryContractAdapter
import com.fastporte.adapter.CarrierPendingContractAdapter
import com.fastporte.adapter.ClientHistoryContractAdapter
import com.fastporte.adapter.ClientPendingContractAdapter
import com.fastporte.adapter.OfferContractAdapter
import com.fastporte.models.Contract
import com.fastporte.network.ContractsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoadContracts {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BaseURL.BASE_URL.toString())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val contractsService: ContractsService = retrofit.create(ContractsService::class.java)

    private fun getContracts(
        request: Call<List<Contract>>,
        context: Context,
        recyclerView: RecyclerView,
        foo: (contracts: List<Contract>) -> (Unit)
    ) {
        request.enqueue(object : Callback<List<Contract>> {
            override fun onFailure(call: Call<List<Contract>>, t: Throwable) {
                Log.d("Activity Fail", "Error: $t")
            }

            override fun onResponse(
                call: Call<List<Contract>>,
                response: Response<List<Contract>>
            ) {
                if (response.isSuccessful) {
                    val contracts: List<Contract> = response.body() ?: ArrayList()
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    foo(contracts)
                } else {
                    Log.d("Activity fail", "Error: " + response.code())
                }
            }
        })
    }

    fun getOfferContracts(view: View, context: Context) {
        val rvOfferContracts = view.findViewById<RecyclerView>(R.id.rvOfferContracts)

        val sharedPreferences = SharedPreferences(context)
        val id = sharedPreferences.getValue("id").toString()

        val request = contractsService.getOfferContractsByDriverId(id.toInt(), "json")

        fun foo(contracts: List<Contract>) {
            rvOfferContracts.adapter = OfferContractAdapter(contracts)
        }

        getContracts(
            request,
            context,
            rvOfferContracts,
            ::foo
        )
    }

    fun getPendingContractsOfDriver(view: View, context: Context) {
        val rvCarrierPendingContracts =
            view.findViewById<RecyclerView>(R.id.rvCarrierPendingContracts)

        val sharedPreferences = SharedPreferences(context)
        val id = sharedPreferences.getValue("id").toString()

        val request = contractsService.getPendingContractsByUserAndId(id.toInt(), "driver", "json")

        fun foo(contracts: List<Contract>) {
            rvCarrierPendingContracts.adapter = CarrierPendingContractAdapter(contracts)
        }

        getContracts(
            request,
            context,
            rvCarrierPendingContracts,
            ::foo
        )
    }

    fun getHistoryContractsOfDriver(view: View, context: Context) {
        val rvCarrierHistoryContracts =
            view.findViewById<RecyclerView>(R.id.rvCarrierHistoryContracts)

        val sharedPreferences = SharedPreferences(context)
        val id = sharedPreferences.getValue("id").toString()

        val request = contractsService.getHistoryContractsByUserAndId(id.toInt(), "driver", "json")

        fun foo(contracts: List<Contract>) {
            rvCarrierHistoryContracts.adapter = CarrierHistoryContractAdapter(contracts)
        }

        getContracts(
            request,
            context,
            rvCarrierHistoryContracts,
            ::foo
        )
    }

    fun getPendingContractsOfClient(view: View, context: Context) {
        val rvClientPendingContracts =
            view.findViewById<RecyclerView>(R.id.rvClientPendingContracts)

        val sharedPreferences = SharedPreferences(context)
        val id = sharedPreferences.getValue("id").toString()

        val request = contractsService.getPendingContractsByUserAndId(id.toInt(), "client", "json")

        fun foo(contracts: List<Contract>) {
            rvClientPendingContracts.adapter = ClientPendingContractAdapter(contracts)
        }

        getContracts(
            request,
            context,
            rvClientPendingContracts,
            ::foo
        )
    }

    fun getHistoryContractsOfClient(view: View, context: Context) {
        val rvClientHistoryContracts =
            view.findViewById<RecyclerView>(R.id.rvClientHistoryContracts)

        val sharedPreferences = SharedPreferences(context)
        val id = sharedPreferences.getValue("id").toString()

        val request = contractsService.getHistoryContractsByUserAndId(id.toInt(), "client", "json")

        fun foo(contracts: List<Contract>) {
            rvClientHistoryContracts.adapter = ClientHistoryContractAdapter(contracts)
        }

        getContracts(
            request,
            context,
            rvClientHistoryContracts,
            ::foo
        )
    }
}