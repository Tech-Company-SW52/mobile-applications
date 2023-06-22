package com.fastporte.models

class Comment(
    val id: Int,
    var comment: String,
    var star: Float,
    var client: User
)