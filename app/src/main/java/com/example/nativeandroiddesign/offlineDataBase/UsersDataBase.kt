package com.example.nativeandroiddesign.offlineDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nativeandroiddesign.dataModule.UserClassItem

@Database(entities = [UserClassItem::class], version = 1)

abstract class UsersDataBase:RoomDatabase() {

    abstract fun usersDao():UsersDao

}