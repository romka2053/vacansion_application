package com.roman.feature_search.domain



internal interface SetIsFavoriteUseCaseApi {
   suspend fun execute(id:String)
}