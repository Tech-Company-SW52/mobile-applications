package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.Components

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.fastporte.models.Experience

import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.CarrierExperienceProfileFragment
import com.fastporte.databinding.FragmentAddExperienceBinding
import com.fastporte.helpers.BaseURL
import com.fastporte.helpers.SharedPreferences
import com.fastporte.network.ProfileService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 * Use the [AddVehicle.newInstance] factory method to
 * create an instance of this fragment.
 */

class AddExperience() : DialogFragment() {
    private lateinit var binding: FragmentAddExperienceBinding
    val bundle = Bundle()
    lateinit var experience: Experience
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BaseURL.BASE_URL.toString())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val experienceService: ProfileService = retrofit.create(ProfileService::class.java)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //var experience: Experience? = null
        binding = FragmentAddExperienceBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        builder.setPositiveButton("Add") { _, _ ->
            val jobExpe = binding.etExperienceJob.text.toString()
            val years = binding.etExperienceTime.text.toString().toInt()
            //experience?.id = 4
            experience = Experience(0, jobExpe, years)
            postExperience()
            //listener?.onAddExperienceFormulary(jobExpe, years)
            dismiss()
        }
        val dialog = builder.create()
        dialog.show()
        return dialog
    }

    private fun postExperience() {
        val request = experienceService.postExperienceDriver(
            SharedPreferences(this.requireContext()).getValue("id")!!.toInt(), experience
        )
        request.enqueue(object : Callback<Experience> {
            override fun onFailure(call: Call<Experience>, t: Throwable) {
                Log.d("Activity fail", t.toString())
            }

            override fun onResponse(call: Call<Experience>, response: Response<Experience>) {
                if (response.isSuccessful) {
                    Log.d("Activity success", response.body().toString())
                } else {
                    Log.d("Activity fail", response.toString())
                }
            }
        })
    }
}