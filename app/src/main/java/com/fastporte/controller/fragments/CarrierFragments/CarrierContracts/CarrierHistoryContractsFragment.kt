package com.fastporte.controller.fragments.CarrierFragments.CarrierContracts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fastporte.R
import com.fastporte.helpers.LoadContracts

class CarrierHistoryContractsFragment : Fragment() {

//    var historyContracts = ArrayList<Contract>()
//
//    var historyContractAdapter = CarrierHistoryContractAdapter(historyContracts)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrier_history_contracts, container, false)

//        loadOfferContracts()
//        initView(view)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loadContracts = LoadContracts()
        loadContracts.getHistoryContractsOfDriver(view, view.context)
    }

    //    private fun initView(view: View) {
//        val rvHistoryContracts = view.findViewById<RecyclerView>(R.id.rvCarrierHistoryContracts)
//
//        rvHistoryContracts.adapter = historyContractAdapter
//        rvHistoryContracts.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
//    }

//    private fun loadOfferContracts() {
//        historyContracts.add(
//            Contract(
//                1,
//                "Example Subject",
//                "Example From",
//                "Example To",
//                "Example Date",
//                "Example Time Departure",
//                "Example Time Arrival",
//                1,
//                1,
//                true,
//                "Done",
//                "Example Client",
//                "Example Phone",
//                "Example Image"
//            )
//        )
//        historyContracts.add(
//            Contract(
//                2,
//                "Example Subject 2",
//                "Example From 2",
//                "Example To 2",
//                "Example Date 2",
//                "Example Time Departure 2",
//                "Example Time Arrival 2",
//                2,
//                2,
//                true,
//                "Done",
//                "Example Client 2",
//                "Example Phone 2",
//                "Example Image"
//            )
//        )
//    }
}