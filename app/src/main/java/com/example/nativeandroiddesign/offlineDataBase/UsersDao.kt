package com.example.nativeandroiddesign.offlineDataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nativeandroiddesign.dataModule.UserClassItem

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Upsert(userClassItem: UserClassItem)

    @Delete
    suspend fun delete(userClassItem: UserClassItem)

    @Query("Select * From UsersDataBase")
    fun getFromDB(): LiveData<List<UserClassItem>>

    @Query("SELECT * FROM UsersDataBase WHERE  '%' || :search || '%'")
    fun searchByName(search: String): List<UserClassItem>

}