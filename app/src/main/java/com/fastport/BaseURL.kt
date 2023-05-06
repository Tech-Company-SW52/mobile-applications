package com.fastport

enum class BaseURL {
    BASE_URL {
        override fun toString(): String {
            return "https://api-fastporte.azurewebsites.net/"
        }
    }
}