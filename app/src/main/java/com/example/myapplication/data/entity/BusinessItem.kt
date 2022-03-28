package com.example.myapplication.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.StringBuilder

data class BusinessItem(
    val id: String,
    val alias: String?,
    val name: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("is_closed")
    val isClosed: Boolean?,
    val url: String?,
    @SerializedName("review_count")
    val reviewCount: Int?,
    val categories: List<Category> = emptyList(),
    val rating: Double?,
    val coordinates: Coordinates?,
    val transactions: List<String> = emptyList(),
    val price: String?,
    val location: Location,
    val phone: String?,
    @SerializedName("display_phone")
    val displayPhone: String?,
    val distance: Double?
): Serializable {
    fun getLocation(): String {
        val disAddress = StringBuilder()
        location.displayAddress.forEach { address -> disAddress.append(address)}
        return disAddress.toString()
    }
}
