package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Business::class], version = 1, exportSchema = false)
abstract class TuroDatabase : RoomDatabase() {

    abstract fun businessDao(): BusinessDao
}
