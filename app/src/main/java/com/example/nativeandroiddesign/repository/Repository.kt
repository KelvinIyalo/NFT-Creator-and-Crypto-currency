package com.example.nativeandroiddesign.repository

import com.example.nativeandroiddesign.dataModule.UserClass
import com.example.nativeandroiddesign.dataModule.UserClassItem
import com.example.nativeandroiddesign.networkCall.UserAPI
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(val api: UserAPI) {

    suspend fun getUsers(): Response<UserClass> {
        return api.getUsers()
    }
}