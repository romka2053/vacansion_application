package com.roman.core_db_room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.roman.core.entity.Button
import com.roman.core.entity.Recommendation
import com.roman.core.entity.Vacancy

@Entity(tableName = "recommendation")
class RecommendationRoom(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "link")
    val link: String,
    @ColumnInfo(name = "button")
    val button: String?,
) {
    constructor(recommendation: Recommendation) : this(
        recommendation.id,
        recommendation.title,
        recommendation.link,
        recommendation.button?.text
    )

    fun mapToRecommendation(): Recommendation {
        return Recommendation(
            id,
            title,
            link,
            Button(button)
        )
    }
}