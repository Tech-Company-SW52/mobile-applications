package com.fastporte.models

class Comment(
    val id: Int,
    val comment: String,
    val star: Int,
    val client: User
)