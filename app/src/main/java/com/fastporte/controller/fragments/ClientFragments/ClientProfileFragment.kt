package com.fastporte.controller.fragments.ClientFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.fastporte.R
import com.fastporte.controller.fragments.ClientFragments.ClientProfile.ClientProfileAdapter
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ClientProfileFragment : Fragment() {

    var tabTitle = arrayOf("Personal information")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_client_profile, container, false)

        val viewPager = view.findViewById<ViewPager2>(R.id.vpProfile)
        val tabLayout = view.findViewById<TabLayout>(R.id.tlProfile)

        val adapter = ClientProfileAdapter(childFragmentManager, lifecycle)

        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[0]))

        viewPager.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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

        loadData(view)
        return view
    }

    private fun loadData(view: View) {
        val civCarrierProfile = view.findViewById<CircleImageView>(R.id.civProfileImage)

        //Usar picasso para cargar la imagen
        Picasso.get()
            .load("https://pbs.twimg.com/profile_images/1251666168026468357/77bjLb3i_400x400.jpg")
            .error(R.drawable.ic_launcher_background)
            .into(civCarrierProfile)

    }

}