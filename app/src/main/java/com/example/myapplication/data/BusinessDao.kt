package com.example.myapplication.data

import androidx.room.*

@Dao
interface BusinessDao {

    @Query("SELECT * FROM business")
    suspend fun getAllBusiness(): List<Business>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(businesses: List<Business>)

    @Transaction
    fun deleteAndInsertAll(businesses: List<Business>) {
        deleteAll()
        insertAll(businesses)
    }

    @Query("DELETE FROM business")
    fun deleteAll()
}
