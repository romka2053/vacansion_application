package com.roman.core_db_room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.roman.core.entity.Vacancy

@Entity(tableName = "vacancies")
class VacanciesRoom(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "appliedNumber")
    val appliedNumber: Int,
    @ColumnInfo(name = "lookingNumber")
    val lookingNumber: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "company")
    val company: String,
    @ColumnInfo(name = "experiencePrev")
    val experiencePrev: String,
    @ColumnInfo(name = "experience")
    val experience: String,
    @ColumnInfo(name = "publishedDate")
    val publishedDate: String,
    @ColumnInfo(name = "schedules")
    val schedules: String,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean,
    @ColumnInfo(name = "salaryPrev")
    val salaryPrev: String,
    @ColumnInfo(name = "salary")
    val salary: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "responsibilities")
    val responsibilities: String,
    @ColumnInfo(name = "questions")
    val questions: List<String>
) {

    constructor(vacancy: Vacancy) : this(
        vacancy.id,
        vacancy.appliedNumber,
        vacancy.lookingNumber,
        vacancy.title,
        vacancy.address,
        vacancy.company,
        vacancy.experiencePrev,
        vacancy.experience,
        vacancy.publishedDate,
        vacancy.schedules,
        vacancy.isFavorite,
        vacancy.salaryPrev,
        vacancy.salary,
        vacancy.description,
        vacancy.responsibilities,
        vacancy.questions
    )

    fun mapToVacancy(): Vacancy {
        return Vacancy(
            id,
            appliedNumber,
            lookingNumber,
            title,
            address,
            company,
            experiencePrev,
            experience,
            publishedDate,
            schedules,
            isFavorite,
            salaryPrev,
            salary,
            description,
            responsibilities,
            questions
        )
    }
}