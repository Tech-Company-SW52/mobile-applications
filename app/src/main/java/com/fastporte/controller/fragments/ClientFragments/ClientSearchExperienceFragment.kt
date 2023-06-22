package com.fastporte.controller.fragments.ClientFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.adapter.SearchClientExperienceAdapter
import com.fastporte.adapter.SearchClientVehicleAdapter
import com.fastporte.models.Experience
import com.fastporte.models.User
import com.fastporte.network.ExperienceService
import com.fastporte.network.VehicleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientSearchExperienceFragment : Fragment() {
    private var user: User? = null;

    lateinit var experienceRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =
            inflater.inflate(R.layout.fragment_client_search_experience, container, false)
        experienceRecyclerView = view.findViewById(R.id.rv_client_search_experience)
        loadExperience(view)
        return view
    }

    fun setUser(user: User) {
        this.user = user
    }

    private fun loadExperience(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-fastporte.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val experienceService: ExperienceService = retrofit.create(ExperienceService::class.java)
        val listExperience = experienceService.getExperienceByDriverID(user?.id ?: 0)
        listExperience.enqueue(object : Callback<List<Experience>> {
            override fun onResponse(
                call: Call<List<Experience>>,
                response: Response<List<Experience>>
            ) {
                val experienceList = response.body()
                if (experienceList != null) {
                    experienceRecyclerView.adapter =
                        SearchClientExperienceAdapter(experienceList, requireContext())
                    experienceRecyclerView.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<List<Experience>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


}