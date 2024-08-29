package com.roman.core_network_impl.dto

import com.roman.core.entity.Vacancy
import com.roman.core.firstUpperCase
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class VacanciesDto(
    val vacancies: List<VacancyDto>
) {
    fun mapToListVacancies() = List(vacancies.size) {
        vacancies[it].mapToVacancy()
    }
}

@JsonClass(generateAdapter = true)
class VacancyDto(
    val id: String,
    val appliedNumber: Int?,
    val lookingNumber: Int?,
    val title: String?,
    val address: AddressDto,
    val company: String?,
    val experience: ExperienceDto,
    val publishedDate: String?,
    val schedules: List<String>,
    val isFavorite: Boolean,
    val salary: SalaryDto,
    val description: String?,
    val responsibilities: String?,
    val questions: List<String>?
) {
    private fun mapToSchedules(): String {
        var text = ""
        schedules.forEachIndexed { index, item ->
            text += if (index != 0) {
                ", $item"
            } else item.firstUpperCase()
        }
        return text
    }

    fun mapToVacancy() = Vacancy(
        id ?: "",
        appliedNumber ?: 0,
        lookingNumber ?: 0,
        title ?: "",
        address.mapToAddress(),
        company ?: "",
        experience.previewText ?: "",
        experience.text ?: "",
        publishedDate ?: "",
        mapToSchedules(),
        isFavorite,
        salary.salary ?: "",
        salary.full ?: "",
        description ?: "",
        responsibilities ?: "",
        questions ?: emptyList()
    )

}

@JsonClass(generateAdapter = true)
class AddressDto(
    val town: String?,
    val street: String?,
    val house: String?,
) {
    fun mapToAddress(): String {
        val text = (town?.let { "$it, " } ?: "") + (street?.let { "$it, " } ?: "") + (house ?: "")
        return text
    }
}

@JsonClass(generateAdapter = true)
class ExperienceDto(
    val previewText: String?,
    val text: String?
)

@JsonClass(generateAdapter = true)
class SalaryDto(
    val salary: String?,
    val full: String?
)