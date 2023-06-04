package com.fastporte.helpers

enum class BaseURL {
    BASE_URL {
        override fun toString(): String {
            return "https://api-fastporte.azurewebsites.net/"
            //return "http://localhost:8080/"
        }
    }
}