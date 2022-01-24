package com.example.nativeandroiddesign.di

import com.example.nativeandroiddesign.networkCall.UserAPI
import com.example.nativeandroiddesign.repository.Repository
import com.example.nativeandroiddesign.utill.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UserModule {

    @Singleton
    @Provides
    fun provideCurrencyApi(): UserAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserAPI::class.java)

    @Singleton
    @Provides
    fun provideRepository(
        api: UserAPI
    ) = Repository(api)
}