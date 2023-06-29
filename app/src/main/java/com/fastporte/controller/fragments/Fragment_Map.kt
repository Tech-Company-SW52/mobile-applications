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
import org.osmdroid.views.MapView

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker


class Fragment_Map : Fragment() {

    private lateinit var mapView: MapView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment__map, container, false)
        // Configuración de OpenStreetMap
        val context = requireContext().applicationContext
        Configuration.getInstance().load(context, context.getSharedPreferences("OpenStreetMapPrefs", 0))

        mapView = view.findViewById(R.id.mapView)

        // Configuración del mapa
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
        mapView.setMultiTouchControls(true)

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
                            Map1(mapView,FirstLocationLat,FirstLocationLng)
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
                            Map2(mapView,SecondLocationLat,SecondLocationLng)
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







        return view
    }
    private fun Map1(mapView : MapView, FirstLocationLat: Double, FirstLocationLng: Double){

        // Ubicaciones por latitud y longitud
        val location1 = GeoPoint(FirstLocationLat, FirstLocationLng) // Ejemplo: San miguel


        // Mostrar ubicaciones en el mapa
        mapView.controller.setZoom(15.0)
        mapView.controller.setCenter(location1)

        // Agregar marcadores para las ubicaciones
        val marker1 = Marker(mapView)
        marker1.position = location1
        marker1.title = "Ubicación 1"
        mapView.overlays.add(marker1)

    }
    private fun Map2(mapView : MapView, SecondLocationLat: Double, SecondLocationLng: Double){

        val location2 = GeoPoint(SecondLocationLat, SecondLocationLng) // Ejemplo: Miraflores


        val marker2 = Marker(mapView)
        marker2.position = location2
        marker2.title = "Ubicación 2"
        mapView.overlays.add(marker2)
    }

}