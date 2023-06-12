package com.gerardojim.listyourpursuit.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao,
) {
    fun getAllTasks(): Flow<List<TaskEntity>> = taskDao.getAllEntities()

    suspend fun saveTaskEntry(taskEntity: TaskEntity) {
        taskDao.insertEntity(taskEntity)
    }

    suspend fun getTask(taskUuid: Int): TaskEntity = taskDao.getTask(taskUuid)
}
