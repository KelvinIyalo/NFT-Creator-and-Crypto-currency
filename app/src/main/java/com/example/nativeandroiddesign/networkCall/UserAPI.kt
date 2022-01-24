package com.example.nativeandroiddesign.networkCall

import com.example.nativeandroiddesign.dataModule.UserClass
import com.example.nativeandroiddesign.dataModule.UserClassItem
import retrofit2.Response
import retrofit2.http.GET

interface UserAPI {
    @GET("users")
    suspend fun getUsers(): Response<UserClass>
}