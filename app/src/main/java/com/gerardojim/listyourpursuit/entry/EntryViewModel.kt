package com.gerardojim.listyourpursuit.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerardojim.listyourpursuit.data.TaskEntity
import com.gerardojim.listyourpursuit.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val NEW_TASK = -1

@HiltViewModel
class EntryViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    private val _state = MutableStateFlow(TaskEntry(onSaveClicked = ::onSave))
    val state = _state.asStateFlow()

    fun setTask(taskUuid: Int) {
        if (taskUuid != NEW_TASK) {
            viewModelScope.launch {
                _state.update {
                    repository.getTask(taskUuid).run {
                        TaskEntry(
                            uuid = checkNotNull(uuid),
                            isComplete = isComplete,
                            title = title,
                            details = details,
                            onSaveClicked = ::onSave,
                        )
                    }
                }
            }
        }
    }

    private fun onSave(title: String, details: String) {
        viewModelScope.launch {
            repository.saveTaskEntry(
                state.value.run {
                    TaskEntity(
                        uuid = if (uuid != NEW_TASK) uuid else null,
                        isComplete = isComplete,
                        title = title,
                        details = details,
                    )
                },
            )
        }
    }
}

data class TaskEntry(
    val uuid: Int = NEW_TASK,
    val isComplete: Boolean = false,
    val title: String = "",
    val details: String = "",
    val onSaveClicked: (title: String, details: String) -> Unit,
)
