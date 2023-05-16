package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.models.Experience
import com.fastporte.R
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.RecyclerViewProfile.ExperienceProfileAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarrierExperienceProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarrierExperienceProfileFragment : Fragment() {

    var experiences = ArrayList<Experience>()
    var experienceAdapter = ExperienceProfileAdapter(experiences)

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View =
            inflater.inflate(R.layout.fragment_carrier_experience_profile, container, false)

        loadExperiences()
        initView(view)

        return view
    }

    private fun initView(view: View) {
        val rvExperience = view.findViewById<RecyclerView>(R.id.rvExperienceCarrier)

        rvExperience.adapter = experienceAdapter
        rvExperience.layoutManager = LinearLayoutManager(view.context)
    }

    private fun loadExperiences() {
        /*val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL.BASE_URL.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val experienceService: ProfileService = retrofit.create(ProfileService::class.java)

        val request = experienceService.getExperience("json")

        //Recibir la lista de experiencias
        request.enqueue(object : Callback<ArrayList<Experience>> {
            override fun onResponse(call: Call<ArrayList<Experience>>, response: Response<ArrayList<Experience>>) {
                if(response.isSuccessful){
                    val experienceList = response.body()!!
                    //experiences.addAll(experienceList)

                    for (experience in experienceList){
                        experiences.add(Experience(experience.id, experience.job, experience.time) )
                    }

                    experiences.add(Experience(1, "Programador", "2 años"))

                }
            }

            override fun onFailure(call: Call<ArrayList<Experience>>, t: Throwable) {
                t.printStackTrace()
            }
        })*/

        experiences.add(Experience(1, "ANITA Tourism", "5 años"))
        experiences.add(Experience(2, "PERU Tourism SAC", "7 años"))
        experiences.add(Experience(3, "Independiente", "3 años"))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarrierExperienceProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarrierExperienceProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
