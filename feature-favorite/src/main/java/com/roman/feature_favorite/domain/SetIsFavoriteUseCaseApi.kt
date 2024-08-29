package com.roman.feature_favorite.domain


internal interface SetIsFavoriteUseCaseApi {
    suspend fun execute(id: String)
}