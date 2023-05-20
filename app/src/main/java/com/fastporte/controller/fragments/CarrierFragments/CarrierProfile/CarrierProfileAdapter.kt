package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CarrierProfileAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CarrierInformationProfileFragment()
            1 -> CarrierExperienceProfileFragment()
            2 -> CarrierVehicleProfileFragment()
            else -> CarrierCommentsProfileFragment()
        }
    }
}