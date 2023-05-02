package com.fastport.CarrierFragments.CarrierProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastport.CarrierClasses.Experience
import com.fastport.CarrierFragments.CarrierProfile.RecyclerViewProfile.ExperienceProfileAdapter
import com.fastport.R

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

        val view: View = inflater.inflate(R.layout.fragment_carrier_experience_profile, container, false)

        loadExperiences()
        initView(view)

        return view
    }

    private fun initView(view: View){
        val rvExperience = view.findViewById<RecyclerView>(R.id.rvCommentsCarrier)

        rvExperience.adapter = experienceAdapter
        rvExperience.layoutManager = LinearLayoutManager(view.context)
    }

    private fun loadExperiences(){
        experiences.add(Experience(0, "Experience 1", "Tiempo 1"))
        experiences.add(Experience(1, "Experience 2", "Tiempo 2"))
        experiences.add(Experience(2, "Experience 3", "Tiempo 3"))
        experiences.add(Experience(3, "Experience 4", "Tiempo 4"))
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