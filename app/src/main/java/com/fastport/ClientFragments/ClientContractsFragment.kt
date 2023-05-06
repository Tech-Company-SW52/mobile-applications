package com.fastport.ClientFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.fastport.CarrierFragments.CarrierContracts.CarrierContractAdapter
import com.fastport.ClientFragments.ClientContracts.ClientContractAdapter
import com.fastport.R
import com.google.android.material.tabs.TabLayout

class ClientContractsFragment : Fragment() {

    var tabTitle = arrayOf("Pending Contracts", "History")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_client_contracts, container, false)

        val viewPager = view.findViewById<ViewPager2>(R.id.vpClientContracts)
        val tabLayout = view.findViewById<TabLayout>(R.id.tlClientContracts)

        val adapter = ClientContractAdapter(parentFragmentManager, lifecycle)

        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[0]))
        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[1]))

        viewPager.adapter = adapter

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        return view
    }

}