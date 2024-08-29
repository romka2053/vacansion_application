package com.roman.core.entity

import com.roman.core.DateToDayAndMonth

data class VacanciesPreview(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val town: String,
    val company: String,
    val experience: String,
    override val publishedDate: String,
    val isFavorite: Boolean,
    val salary: String,
) : DateToDayAndMonth {

    constructor(vacancy: Vacancy) : this(
        vacancy.id,
        vacancy.lookingNumber,
        vacancy.title,
        vacancy.address.substringBefore(","),
        vacancy.company,
        vacancy.experiencePrev,
        vacancy.publishedDate,
        vacancy.isFavorite,
        vacancy.salaryPrev,
    )
}

