package com.fastporte.controller.fragments.ClientFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.fastporte.R
import com.google.android.material.tabs.TabLayout

class ClientSearchDriverProfile : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_client_search_driver_profile, container, false)

        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.view_pager)

        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(ClientSearchPersonalnformationfragment(), "Personal Information")
        adapter.addFragment(ClientSearchExperienceFragment(), "Experience")
        adapter.addFragment(ClientSearchVehicleFragment(), "Vehicle")
        adapter.addFragment(ClientSearchCommentsFragment(), "Comments")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        return view
    }

    private inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val fragmentList = ArrayList<Fragment>()
        private val fragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }
    }
}