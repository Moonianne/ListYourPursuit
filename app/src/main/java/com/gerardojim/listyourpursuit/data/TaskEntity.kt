package com.gerardojim.listyourpursuit.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@androidx.room.Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uuid")
    val uuid: Int? = null,
    @ColumnInfo(name = "isComplete")
    val isComplete: Boolean,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "details")
    val details: String,
)
