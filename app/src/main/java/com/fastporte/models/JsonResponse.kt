package com.fastporte.models

class JsonResponse(
    val results: List<Result>,
    // otros campos de la respuesta si los hay
) {
    class Result(
        val formatted: String,
        val geometry: Geometry,
        // otros campos de cada resultado si los hay
    )
    class Geometry(
        val lat: Double,
        val lng: Double
    )
}