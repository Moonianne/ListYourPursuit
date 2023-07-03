package com.gerardojim.listyourpursuit

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableStateFlow<TaskScreen> = MutableStateFlow(TaskScreen.TaskList)
    val state: StateFlow<TaskScreen> = _state.asStateFlow()

    fun onGoToTaskList() = _state.update { TaskScreen.TaskList }
    fun onGoToTaskEntry(uuid: Int) = _state.update { TaskScreen.TaskEntry(uuid) }
    fun onSystemScreenChange(screenKey: String) = _state.update { TaskScreen.getScreen(screenKey) }
}
