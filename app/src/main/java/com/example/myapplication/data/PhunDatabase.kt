package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class PhunDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao
}