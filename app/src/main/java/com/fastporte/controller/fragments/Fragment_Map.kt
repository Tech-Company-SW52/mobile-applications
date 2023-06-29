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
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Fragment_Map : Fragment() {


    private lateinit var mapView: MapView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val _context = requireContext()
        val sharedPreferences = SharedPreferences(_context)
        val to = sharedPreferences.getValue("to")
        val from = sharedPreferences.getValue("from")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.opencagedata.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val GeoCodeService = retrofit.create(GeoCodeService::class.java)

        val query = to
        val apiKey = "4f3e2e9c5cce427694e5cbb203ccfb4e"
        val language = "es"
        val pretty = 1
        val replacedQuery = query.replace(" ", "+")
        val call =
            GeoCodeService.getFormattedResults(replacedQuery, apiKey, language, pretty)
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
                        formattedResults = jsonResponse.results?.mapNotNull { it.formatted }
                        if (formattedResults != null) {
                            for (result in formattedResults) {
                                options.add(result)
                            }
                            val filteredOptions =
                                options.filter { it.contains(newText, true) }.toTypedArray()
                            adapter.clear()
                            adapter.addAll(*filteredOptions)
                            adapter.notifyDataSetChanged()
                        }
                    } else {
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment__map, container, false)

        // Configuración de OpenStreetMap
        val context = requireContext().applicationContext
        Configuration.getInstance().load(context, context.getSharedPreferences("OpenStreetMapPrefs", 0))

        mapView = view.findViewById(R.id.mapView)

        // Configuración del mapa
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
        mapView.setMultiTouchControls(true)

        // Ubicaciones por latitud y longitud
        val location1 = GeoPoint(-12.0791137, -77.0947866) // Ejemplo: San miguel
        val location2 = GeoPoint(-12.121498, -77.0259064) // Ejemplo: Miraflores

        // Mostrar ubicaciones en el mapa
        mapView.controller.setZoom(15.0)
        mapView.controller.setCenter(location1)

        // Agregar marcadores para las ubicaciones
        val marker1 = Marker(mapView)
        marker1.position = location1
        marker1.title = "Ubicación 1"
        mapView.overlays.add(marker1)

        val marker2 = Marker(mapView)
        marker2.position = location2
        marker2.title = "Ubicación 2"
        mapView.overlays.add(marker2)

        return view
    }



}