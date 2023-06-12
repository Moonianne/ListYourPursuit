package com.gerardojim.listyourpursuit.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerardojim.listyourpursuit.data.TaskEntity
import com.gerardojim.listyourpursuit.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<TaskListState> =
        MutableStateFlow(TaskListState(emptyList(), null) { })
    private val state: StateFlow<TaskListState> = _state.asStateFlow()

    fun getState(): StateFlow<TaskListState> {
        viewModelScope.launch {
            repository.getAllTasks().collect { entities ->
                handleUpdatedTaskList(entities)
            }
        }
        return state
    }
    fun onNavigated() {
        _state.update { it.copy(currentTaskUuid = null) }
    }

    private fun handleUpdatedTaskList(entities: List<TaskEntity>) {
        _state.update { state ->
            state.copy(
                tasks = entities.map { it.toTask() },
            )
        }
    }

    private fun handleOnTaskCompleted(isComplete: Boolean, taskEntity: TaskEntity) {
        viewModelScope.launch {
            repository.saveTaskEntry(taskEntity.copy(isComplete = isComplete))
        }
    }

    private fun handleOnTaskClicked(uuid: Int) {
        _state.update {
            it.copy(currentTaskUuid = uuid)
        }
    }

    private fun TaskEntity.toTask(): Task = Task(
        uuid = uuid!!,
        isComplete = isComplete,
        title = title,
        details = details,
        onIsCompleteClicked = { handleOnTaskCompleted(it, this) },
        onTitleClicked = { handleOnTaskClicked(uuid) },
    )

    data class TaskListState(
        val tasks: List<Task>,
        val currentTaskUuid: Int?,
        val onAddNewTaskClicked: () -> Unit,
    )
}
