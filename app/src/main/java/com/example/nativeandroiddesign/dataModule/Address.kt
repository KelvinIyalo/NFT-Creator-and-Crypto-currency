package com.example.nativeandroiddesign.dataModule


import com.google.gson.annotations.SerializedName

data class Address(
    val city: String? = null, // Lebsackbury
    val geo: Geo? = null,
    val street: String? = null, // Kattie Turnpike
    val suite: String? = null, // Suite 198
    val zipcode: String? = null // 31428-2261
)