package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.models.Experience
import com.fastporte.R
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.Components.AddExperience
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.RecyclerViewProfile.ExperienceProfileAdapter
import com.fastporte.helpers.BaseURL
import com.fastporte.helpers.SharedPreferences
import com.fastporte.network.ProfileService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//var jobExperience: String = ""
//var timeExperience: Int = 0

class CarrierExperienceProfileFragment : Fragment()
//,AddExperienceInterface
{
    lateinit var experienceRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View =
            inflater.inflate(R.layout.fragment_carrier_experience_profile, container, false)
        val addExperience = AddExperience()

        //addExperience.setAddExperienceListener(this)

        val btnAddExperience = view.findViewById<Button>(R.id.btAddExperience)

        btnAddExperience?.setOnClickListener {
            addExperience.show(parentFragmentManager, "AddExperience")
            loadExperiences(view)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        experienceRecyclerView = view.findViewById(R.id.rvExperienceCarrier)
        loadExperiences(view)
    }

    private fun loadExperiences(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL.BASE_URL.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val experienceService: ProfileService = retrofit.create(ProfileService::class.java)

        val request = experienceService.getDriverExperience(
            SharedPreferences(view.context).getValue("id")!!.toInt(), "json"
        )

        //Recibir la lista de experiencias
        request.enqueue(object : Callback<List<Experience>> {
            override fun onResponse(
                call: Call<List<Experience>>,
                response: Response<List<Experience>>
            ) {
                if (response.isSuccessful) {
                    val experienceList: List<Experience> = response.body()!!
                    experienceRecyclerView.layoutManager = LinearLayoutManager(view.context)
                    experienceRecyclerView.adapter =
                        ExperienceProfileAdapter(experienceList, view.context)

                }
            }

            override fun onFailure(call: Call<List<Experience>>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    //override fun onAddExperienceFormulary(experienceJob: String, experienceTime: Int) {
    //    jobExperience = experienceJob
    //    timeExperience = experienceTime
    //    Toast.makeText(context, "datos $jobExperience $timeExperience", Toast.LENGTH_SHORT).show()
//
    //    //experience?.job = jobExperience
    //    //experience?.years = timeExperience
    //    //postExperience()
    //}
}
