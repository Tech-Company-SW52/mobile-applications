package com.fastporte.models

class JsonResponse(
    val results: List<Result>,
    // otros campos de la respuesta si los hay
) {
    class Result(
        val formatted: String,
        // otros campos de cada resultado si los hay
    )
}