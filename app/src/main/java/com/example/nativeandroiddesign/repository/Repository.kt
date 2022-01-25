package com.example.nativeandroiddesign.repository

import androidx.lifecycle.LiveData
import com.example.nativeandroiddesign.dataModule.UserClass
import com.example.nativeandroiddesign.dataModule.UserClassItem
import com.example.nativeandroiddesign.networkCall.UserAPI
import com.example.nativeandroiddesign.offlineDataBase.UsersDao
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(val dao: UsersDao, val api: UserAPI) {

    suspend fun getUsers(): Response<UserClass> {
        return api.getUsers()
    }

    suspend fun Upsert(userClassItem: UserClassItem){
        dao.Upsert(userClassItem)
    }
    suspend fun delete(userClassItem: UserClassItem){
        dao.delete(userClassItem)
    }

    fun getAllFromDB(): LiveData<List<UserClassItem>> {
        return dao.getFromDB()
    }

    fun getSearchFromDB(title: String): List<UserClassItem> {
        return dao.searchByName(title)
    }



}