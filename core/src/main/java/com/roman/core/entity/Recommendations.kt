package com.roman.core.entity

data class Recommendation(
    val id: String,
    val title: String,
    val link: String,
    val button: Button?
)

data class Button(
    val text: String?
)