package com.roman.core_network_impl.di

import com.roman.core_network_api.DataNetworkApi
import com.roman.core_network_impl.DataNetworkImpl
import com.roman.core_network_impl.RetrofitSrc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {
    @Provides
    @Named("network_url")
    fun providesBaseUrl(): String = "https://drive.usercontent.google.com"

    @Provides
    @Singleton
    fun getNetwork(@Named("network_url") baseUrl: String): RetrofitSrc = RetrofitSrc(baseUrl)

    @Provides
    fun getDataNetworkApi(retrofit: RetrofitSrc): DataNetworkApi = DataNetworkImpl(retrofit)

}