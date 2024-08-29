package com.roman.core_network_impl.dto

import com.roman.core.entity.Button
import com.roman.core.entity.Recommendation
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RecommendationsDto(
    val offers: List<OfferDto>
) {
    fun mapToListRecommendation() = List(offers.size) {
        offers[it].mapToOffer()
    }

}

@JsonClass(generateAdapter = true)
class OfferDto(
    val id: String?,
    val title: String?,
    val link: String?,
    val button: ButtonDto?
) {
    fun mapToOffer() = Recommendation(
        id ?: "",
        title ?: "",
        link ?: "",
        button?.mapToButton()
    )
}

@JsonClass(generateAdapter = true)
class ButtonDto(
    val text: String
) {
    fun mapToButton() = Button(text)
}