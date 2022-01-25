package com.example.nativeandroiddesign.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.ContactsContract
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nativeandroiddesign.dataModule.UserClass
import com.example.nativeandroiddesign.dataModule.UserClassItem
import com.example.nativeandroiddesign.di.UserApplication
import com.example.nativeandroiddesign.repository.Repository
import com.example.nativeandroiddesign.utill.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MyViewModel @ViewModelInject constructor(
    private val app: Application,
    private val repository: Repository
) :
    AndroidViewModel(app)  {

    val userList: MutableLiveData<Resource<UserClass>> = MutableLiveData()

    init {
        getUsersList()
    }


    fun getUsersList(){
        viewModelScope.launch {
            userList.postValue(Resource.Loading())
            safeUsersListcall()
        }
    }


    //DataBase setup

    fun Updert(userClassItem: UserClassItem) = viewModelScope.launch {
        repository.Upsert(userClassItem)
    }

    fun delete(userClassItem: UserClassItem) = viewModelScope.launch {
        repository.delete(userClassItem)
    }

    fun getAllFromDB(): LiveData<List<UserClassItem>> = repository.getAllFromDB()

    fun getSearchFromDB(title:String): List<UserClassItem> = repository.getSearchFromDB(title)





    private  fun handleUsersList(response: Response<UserClass>):Resource<UserClass>{
        if (response.isSuccessful){
            response.body()?.let { ResultResponse ->
                return Resource.Success(ResultResponse)
            }
        }
        return Resource.Error(response.message(),data = null)
    }

    suspend fun safeUsersListcall(){
        userList.postValue(Resource.Loading())
        try {
            if (isInternetAvailable()) {
                val userResponse = repository.getUsers()
                userList.postValue(handleUsersList(userResponse))
            }else{
                userList.postValue(Resource.Error("No Internet Connection", null))
            }

        }catch (t:Throwable){
            when(t){
                is IOException -> userList.postValue(Resource.Error("Network Failure", null))
                else -> userList.postValue(Resource.Error("Conversion Error:$t", null))
            }

        }

    }

    //Network Check
    fun isInternetAvailable():Boolean{

        val connectivityManager = getApplication<UserApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false

            }

        } else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    ConnectivityManager.TYPE_WIFI -> true
                    ContactsContract.CommonDataKinds.Email.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false

                }
            }
        }
        return false

    }

}