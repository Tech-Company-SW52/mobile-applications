package com.fastporte.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fastporte.controller.fragments.ClientFragments.ClientContracts.ClientHistoryContractsFragment
import com.fastporte.controller.fragments.ClientFragments.ClientContracts.ClientPendingContractsFragment

class ClientContractAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ClientPendingContractsFragment()
            else -> ClientHistoryContractsFragment()
        }
    }
}