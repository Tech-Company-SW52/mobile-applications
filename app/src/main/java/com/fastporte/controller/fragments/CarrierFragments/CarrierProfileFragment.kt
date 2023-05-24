package com.fastporte.controller.fragments.CarrierFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.fastporte.helpers.BaseURL
import com.fastporte.R
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.CarrierProfileAdapter
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.Components.EditProfileDriverDialogFragment
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.User
import com.fastporte.network.ProfileService
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarrierProfileFragment : Fragment() {

    var tabTitle = arrayOf("Personal information", "Experience", "Vehicle", "Comments")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_carrier_profile, container, false)

        val view: View = inflater.inflate(R.layout.fragment_carrier_profile, container, false)
        val editProfileDialog = EditProfileDriverDialogFragment()

        //Tab layout
        val viewPager = view.findViewById<ViewPager2>(R.id.vpProfile)
        val tabLayout = view.findViewById<TabLayout>(R.id.tlProfile)

        val adapter = CarrierProfileAdapter(childFragmentManager, lifecycle)

        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[0]))
        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[1]))
        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[2]))
        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[3]))

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

        val btnEditProfile = view.findViewById<Button>(R.id.btnEditProfile)
        btnEditProfile?.setOnClickListener {
            editProfileDialog.show(parentFragmentManager, "Edit profile")
        }

        return view
    }

    private fun loadData(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL.BASE_URL.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val profileService: ProfileService = retrofit.create(ProfileService::class.java)

        val request = profileService.getDriverProfile(
            SharedPreferences(view.context).getValue("id")!!.toInt(), "json"
        )

        request.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("profileInformationFragment", t.toString())
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    showData(response.body()!!)
                }
            }
        })
    }

    private fun showData(user: User) {

        val civCarrierProfile = view?.findViewById<CircleImageView>(R.id.civCarrierProfile)
        val tvCarrierName = view?.findViewById<TextView>(R.id.tvProfileName)
        val tvCarrierDescription = view?.findViewById<TextView>(R.id.tvProfileDescription)

        //Usar picasso para cargar la imagen
        Picasso.get().load(user.photo)
            .error(R.drawable.ic_launcher_background)
            .into(civCarrierProfile)

        tvCarrierName?.text = user.name
        tvCarrierDescription?.text = user.description
    }
}