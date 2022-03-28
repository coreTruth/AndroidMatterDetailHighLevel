package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "business")
data class Business constructor(
    @PrimaryKey
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
    val categories: String,
    val rating: Double?,
    val coordinates: String,
    val transactions: String,
    val price: String?,
    val location: String,
    val phone: String?,
    @SerializedName("display_phone")
    val displayPhone: String?,
    val distance: Double?
): Serializable
