package com.fastport.CarrierFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.fastport.CarrierFragments.CarrierContracts.CarrierContractAdapter
import com.fastport.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CarrierContractsFragment : Fragment() {

    var tabTitle = arrayOf("Offers", "Pending Contracts", "History")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_carrier_contracts, container, false)

        val viewPager = view.findViewById<ViewPager2>(R.id.vpCarrierContracts)
        val tabLayout = view.findViewById<TabLayout>(R.id.tlCarrierContracts)

        val adapter = CarrierContractAdapter(parentFragmentManager, lifecycle)

        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[0]))
        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[1]))
        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[2]))

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