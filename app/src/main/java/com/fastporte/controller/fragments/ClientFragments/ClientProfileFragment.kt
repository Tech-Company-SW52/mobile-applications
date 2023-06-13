package com.fastporte.controller.fragments.ClientFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.fastporte.Interface.EditProfileDialogListener
import com.fastporte.R
import com.fastporte.controller.fragments.ClientFragments.ClientProfile.ClientProfileAdapter
import com.fastporte.controller.fragments.ClientFragments.ClientProfile.EditProfileClientDialogFragment
import com.fastporte.helpers.BaseURL
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

class ClientProfileFragment : Fragment(), EditProfileDialogListener {

    var tabTitle = arrayOf("Personal information")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_client_profile, container, false)
        val editProfileDialog = EditProfileClientDialogFragment()
        editProfileDialog.setDialogListener(this)

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

        val btnEditProfile = view.findViewById<Button>(R.id.btnViewHistory)
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

        val service = retrofit.create(ProfileService::class.java)
        val request = service.getClientProfile(
            SharedPreferences(view.context).getValue("id")!!.toInt(),
            "json"
        )

        request.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    showData(response.body()!!)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })


    }

    private fun showData(user: User) {
        val civProfileImage = view?.findViewById<CircleImageView>(R.id.civProfileImage)
        val tvProfileName = view?.findViewById<TextView>(R.id.tvProfileName)
        val tvProfileDescription = view?.findViewById<TextView>(R.id.tvProfileDescription)

        Picasso.get()
            .load(user.photo)
            .error(R.drawable.ic_launcher_background)
            .into(civProfileImage)

        tvProfileName?.text = user.name
        tvProfileDescription?.text = user.description
    }

    override fun onDialogDataSaved(user: User) {
        val tvProfileName = view?.findViewById<TextView>(R.id.tvProfileName)
        val informationName = view?.findViewById<Button>(R.id.btName)
        val informationBirthday = view?.findViewById<Button>(R.id.btAge)
        val informationPhone = view?.findViewById<Button>(R.id.btPhone)

        tvProfileName?.text = user.name
        informationName?.text = user.name
        informationBirthday?.text = user.birthdate
        informationPhone?.text = user.phone
    }
}