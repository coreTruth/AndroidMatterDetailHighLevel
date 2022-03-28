package com.example.myapplication.data.entity

import com.google.gson.annotations.SerializedName

data class Location(
    val address1: String,
    val address2: String,
    val address3: String,
    val city: String,
    @SerializedName("zip_code")
    val zipCode: String,
    val country: String,
    val state: String,
    @SerializedName("display_address")
    val displayAddress: List<String> = emptyList()
)
