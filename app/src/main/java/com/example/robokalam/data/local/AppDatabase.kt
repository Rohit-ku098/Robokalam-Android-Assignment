package com.example.robokalam.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.robokalam.model.Converters
import com.example.robokalam.model.Portfolio

@Database(entities = [Portfolio::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun portfolioDao(): PortfolioDao
}