package com.fastporte.controller.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fastporte.R
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.JsonResponse
import com.fastporte.network.GeoCodeService

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Fragment_Map : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val _context = requireContext()
        val sharedPreferences = SharedPreferences(_context)
        val to = sharedPreferences.getValue("to")
        val from = sharedPreferences.getValue("from")

        var FirstLocationLat=1.1
        var FirstLocationLng=1.1
        var SecondLocationLat=1.1
        var SecondLocationLng=1.1


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.opencagedata.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val GeoCodeService = retrofit.create(GeoCodeService::class.java)

        val query = from.toString()
        val apiKey = "4f3e2e9c5cce427694e5cbb203ccfb4e"
        val language = "es"
        val pretty = 1
        val replacedQuery = query.replace(" ", "+")
        val call = GeoCodeService.getFormattedResults(replacedQuery, apiKey, language, pretty)
        Log.d("TAG", "My query es: " + query)
        call.enqueue(object : retrofit2.Callback<JsonResponse> {
            override fun onResponse(
                call: Call<JsonResponse>,
                response: Response<JsonResponse>
            ) {
                if (response.isSuccessful) {
                    val jsonResponse = response.body()
                    var formattedResults: List<String>? = null

                    if (jsonResponse != null) {
                        val firstGeometry = jsonResponse.results?.firstOrNull()?.geometry
                        if (firstGeometry != null) {
                            FirstLocationLat = firstGeometry.lat
                            FirstLocationLng = firstGeometry.lng
                            // Utiliza los valores de lat y lng como desees
                            Log.d("TAG", "lat1 y lng1: " + FirstLocationLat+" -  "+FirstLocationLng )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        val query2 = to.toString()
        val apiKey2 = "4f3e2e9c5cce427694e5cbb203ccfb4e"
        val language2 = "es"
        val pretty2 = 1
        val replacedQuery2 = query2.replace(" ", "+")
        val call2 = GeoCodeService.getFormattedResults(replacedQuery2, apiKey2, language2, pretty2)
        Log.d("TAG", "My query es: " + query2)
        call2.enqueue(object : retrofit2.Callback<JsonResponse> {
            override fun onResponse(
                call: Call<JsonResponse>,
                response: Response<JsonResponse>
            ) {
                if (response.isSuccessful) {
                    val jsonResponse = response.body()
                    var formattedResults: List<String>? = null

                    if (jsonResponse != null) {
                        val firstGeometry = jsonResponse.results?.firstOrNull()?.geometry
                        if (firstGeometry != null) {
                            SecondLocationLat = firstGeometry.lat
                            SecondLocationLng = firstGeometry.lng
                            Log.d("TAG", "lat2 y lng2: " + SecondLocationLat+" -  "+SecondLocationLng )

                            // Utiliza los valores de lat y lng como desees
                        }
                    }
                }
            }

            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })




        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment__map, container, false)



        return view
    }

}