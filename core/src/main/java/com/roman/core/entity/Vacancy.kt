package com.roman.core.entity


data class Vacancy(
    val id: String,
    val appliedNumber: Int,
    val lookingNumber: Int,
    val title: String,
    val address: String,
    val company: String,
    val experiencePrev: String,
    val experience: String,
    val publishedDate: String,
    val schedules: String,
    val isFavorite: Boolean,
    val salaryPrev: String,
    val salary: String,
    val description: String,
    val responsibilities: String,
    val questions: List<String>

)


