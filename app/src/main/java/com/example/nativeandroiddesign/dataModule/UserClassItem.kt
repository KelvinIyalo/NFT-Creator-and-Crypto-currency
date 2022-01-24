package com.example.nativeandroiddesign.dataModule


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserClassItem(
    val address: Address? = null,
    val company: Company? = null,
    val email: String? = null, // Rey.Padberg@karina.biz
    val id: Int? = null, // 10
    val name: String? = null, // Clementina DuBuque
    val phone: String? = null, // 024-648-3804
    val username: String? = null, // Moriah.Stanton
    val website: String? = null // ambrose.net
):Serializable