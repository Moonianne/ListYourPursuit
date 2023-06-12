package com.gerardojim.listyourpursuit.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllEntities(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntity(taskEntity: TaskEntity)

    @Query("SELECT * FROM tasks WHERE uuid = :taskUuid ")
    suspend fun getTask(taskUuid: Int): TaskEntity
}
