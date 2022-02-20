package com.example.myapplication.data

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.R
import java.io.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "events")
data class Event constructor(
    @PrimaryKey
    val id: Int,
    val description: String,
    val title: String,
    val timestamp: String?,
    val image: String?,
    val date: String?,
    val locationline1: String,
    val locationline2: String
): Serializable {
    fun getFullLocation() = "$locationline1 $locationline2"

    fun getTimeStringByLocalTimeZone(): String {
        if (timestamp == null) return ""

        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputDateFormat.timeZone = TimeZone.getTimeZone("GMT")
        return try {
            val parseDate = inputDateFormat.parse(timestamp)
            val outputTimeFormat = SimpleDateFormat("MMM d, yyyy 'at' h:mm a", Locale.getDefault())
            outputTimeFormat.format(parseDate!!)
        } catch (e: ParseException) {
            ""
        }
    }

    fun getMessageBody(context: Context): String = String.format(
        "%s%s\n%s%s",
        context.getString(R.string.title),
        title,
        context.getString(R.string.description),
        description
    )
}