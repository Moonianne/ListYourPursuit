package com.gerardojim.listyourpursuit.data

import androidx.room.Database
import androidx.room.RoomDatabase

private const val DB_VERSION: Int = 1

@Database(entities = [TaskEntity::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}
