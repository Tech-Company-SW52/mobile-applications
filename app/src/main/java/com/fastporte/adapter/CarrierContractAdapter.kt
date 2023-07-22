package com.fastporte.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fastporte.controller.fragments.CarrierFragments.CarrierContracts.CarrierHistoryContractsFragment
import com.fastporte.controller.fragments.CarrierFragments.CarrierContracts.CarrierOfferContractsFragment
import com.fastporte.controller.fragments.CarrierFragments.CarrierContracts.CarrierPendingContractsFragment

class CarrierContractAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CarrierOfferContractsFragment()
            1 -> CarrierPendingContractsFragment()
            else -> CarrierHistoryContractsFragment()
        }
    }
}