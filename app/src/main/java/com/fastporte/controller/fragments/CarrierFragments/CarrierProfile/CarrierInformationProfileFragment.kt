package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.fastporte.helpers.BaseURL
import com.fastporte.models.Information
import com.fastporte.R
import com.fastporte.network.ProfileService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarrierInformationProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarrierInformationProfileFragment : Fragment() {
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
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_carrier_information_profile, container, false)

        loadData()

        return view
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL.BASE_URL.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val profileService: ProfileService
        profileService = retrofit.create(ProfileService::class.java)

        val request = profileService.getProfile(1,"json")

        request.enqueue(object : Callback<Information> {
            override fun onFailure(call: Call<Information>, t: Throwable) {
                Log.d("profileInformationFragment", t.toString())
            }

            override fun onResponse(call: Call<Information>, response: Response<Information>) {
                if (response.isSuccessful) {
                    showData(response.body()!!)
                }
            }
        })
    }

    private fun showData(information: Information) {

        val btName = view?.findViewById<TextView>(R.id.btName)
        val btAge = view?.findViewById<TextView>(R.id.btAge)
        val btEmail = view?.findViewById<TextView>(R.id.btEmail)
        val btPhone = view?.findViewById<TextView>(R.id.btPhone)

        btName?.text = information.name
        btAge?.text = information.birthdate
        btEmail?.text = information.email
        btPhone?.text = information.phone

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarrierInformationProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarrierInformationProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}