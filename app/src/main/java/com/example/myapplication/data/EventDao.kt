package com.example.myapplication.data

import androidx.room.*

@Dao
interface EventDao {

    @Query("SELECT * FROM events")
    suspend fun getEvents(): List<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(events: List<Event>)

    @Transaction
    fun deleteAndInsertAll(events: List<Event>) {
        deleteAll()
        insertAll(events)
    }

    @Query("DELETE FROM events")
    fun deleteAll()
}