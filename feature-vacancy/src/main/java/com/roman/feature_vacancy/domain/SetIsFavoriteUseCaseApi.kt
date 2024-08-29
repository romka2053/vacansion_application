package com.roman.feature_vacancy.domain

internal interface SetIsFavoriteUseCaseApi {
   suspend fun execute(id:String)
}