package com.fastport.CarrierFragments.CarrierContracts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastport.CarrierFragments.CarrierContracts.RecyclerViewContracts.CarrierHistoryContractAdapter
import com.fastport.CarrierFragments.CarrierContracts.RecyclerViewContracts.OfferContractAdapter
import com.fastport.Contracts.Contract
import com.fastport.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarrierHistoryContractsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarrierHistoryContractsFragment : Fragment() {

    var historyContracts = ArrayList<Contract>()

    var historyContractAdapter = CarrierHistoryContractAdapter(historyContracts)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_carrier_history_contracts, container, false)

        loadOfferContracts()
        initView(view)

        return view
    }

    private fun initView(view: View) {
        val rvHistoryContracts = view.findViewById<RecyclerView>(R.id.rvCarrierHistoryContracts)

        rvHistoryContracts.adapter = historyContractAdapter
        rvHistoryContracts.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
    }

    private fun loadOfferContracts() {
        historyContracts.add(
            Contract(
                1,
                "Example Subject",
                "Example From",
                "Example To",
                "Example Date",
                "Example Time Departure",
                "Example Time Arrival",
                1,
                1,
                "Example Description",
                true
            )
        )
        historyContracts.add(
            Contract(
                2,
                "Example Subject 2",
                "Example From 2",
                "Example To 2",
                "Example Date 2",
                "Example Time Departure 2",
                "Example Time Arrival 2",
                2,
                2,
                "Example Description 2",
                true
            )
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarrierHistoryContracts.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarrierHistoryContractsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}