package com.example.nativeandroiddesign.dataModule


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "UsersDataBase")
data class UserClassItem(
    val email: String? = null, // Rey.Padberg@karina.biz
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null, // 10
    val name: String? = null, // Clementina DuBuque
    val phone: String? = null, // 024-648-3804
    val username: String? = null, // Moriah.Stanton
    val website: String? = null // ambrose.net
):Serializable