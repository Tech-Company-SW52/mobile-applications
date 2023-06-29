package com.fastporte.controller.fragments.ClientFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.controller.fragments.ClientFragments.ClientHome.ClientHomeHistoryAdapter
import com.fastporte.controller.fragments.ClientFragments.ClientHome.ClientHomePopularAdapter
import com.fastporte.helpers.BaseURL
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.Contract
import com.fastporte.models.Driver
import com.fastporte.models.User
import com.fastporte.network.HomeService
import com.fastporte.network.ProfileService
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientHomeFragment : Fragment() {

    lateinit var popularRecyclerView: RecyclerView
    lateinit var recentRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_client_home, container, false)
        val btnViewHistory = view.findViewById<Button>(R.id.btnViewHistoryClient)
        val btnViewProfile = view.findViewById<Button>(R.id.btnViewProfileClient)
        btnViewHistory.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
            Navigation.findNavController(view).navigate(R.id.id_client_contracts_fragment)
        }
        btnViewProfile.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
            Navigation.findNavController(view).navigate(R.id.id_client_profile_fragment)
        }

        loadData(view)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularRecyclerView = view.findViewById(R.id.populaRVclient)
        recentRecyclerView = view.findViewById(R.id.contractsRVclient)
        loadPopular(view)
    }

    private fun loadPopular(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL.BASE_URL.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val homeService: HomeService = retrofit.create(HomeService::class.java)

        val request2 = homeService.getDriver("json")

        request2.enqueue(object : Callback<List<Driver>> {
            override fun onResponse(call: Call<List<Driver>>, response: Response<List<Driver>>) {
                if (response.isSuccessful) {
                    val driverList: List<Driver> = response.body()!!
                    popularRecyclerView.layoutManager =
                        LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
                    popularRecyclerView.adapter = ClientHomePopularAdapter(driverList, view.context)
                }
            }

            override fun onFailure(call: Call<List<Driver>>, t: Throwable) {
                Log.d("profileInformationFragment", t.toString())
            }

        })

        val request3 = homeService.getHistoryContractsByUserAndId(
            SharedPreferences(view.context).getValue("id")!!.toInt(), "client", "json"
        )

        request3.enqueue(object : Callback<List<Contract>> {
            override fun onResponse(
                call: Call<List<Contract>>,
                response: Response<List<Contract>>
            ) {
                if (response.isSuccessful) {
                    if (response.message() == "No Content") {
                        Toast.makeText(
                            view.context,
                            "No hay contratos recientes",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val contractList: List<Contract> = response.body()!!
                        recentRecyclerView.layoutManager =
                            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
                        recentRecyclerView.adapter =
                            ClientHomeHistoryAdapter(contractList, view.context)
                    }

                }
            }

            override fun onFailure(call: Call<List<Contract>>, t: Throwable) {
                Log.d("profileInformationFragment", t.toString())
            }
        })
    }

    private fun loadData(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL.BASE_URL.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val profileService: ProfileService = retrofit.create(ProfileService::class.java)

        val request = profileService.getClientProfile(
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
        val civUserProfile = view?.findViewById<CircleImageView>(R.id.civDriverProfile)
        val tvNameProfile = view?.findViewById<TextView>(R.id.tvNameProfile)

        Picasso.get().load(user.photo)
            .error(R.drawable.ic_launcher_background)
            .into(civUserProfile)

        tvNameProfile?.text = "Hi, " + user.name + "!"
    }
}