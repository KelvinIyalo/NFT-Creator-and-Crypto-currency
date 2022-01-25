package com.example.nativeandroiddesign.di

import android.content.*
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.UserHandle
import android.view.Display
import androidx.room.Room
import com.example.nativeandroiddesign.networkCall.UserAPI
import com.example.nativeandroiddesign.offlineDataBase.UsersDao
import com.example.nativeandroiddesign.offlineDataBase.UsersDataBase
import com.example.nativeandroiddesign.repository.Repository
import com.example.nativeandroiddesign.utill.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UserModule {

    @Singleton
    @Provides
    fun ProvidesDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        UsersDataBase::class.java, "news_db"
    ).build()


    @Singleton
    @Provides
    fun provideShoppingDao(database: UsersDataBase) = database.usersDao()

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
        dao: UsersDao,
        api: UserAPI
    ) = Repository(dao,api)






}
