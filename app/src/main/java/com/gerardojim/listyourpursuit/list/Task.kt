package com.gerardojim.listyourpursuit.list

data class Task(
    val uuid: Int = -1,
    val isComplete: Boolean,
    val title: String,
    val details: String,
    val onIsCompleteClicked: (Boolean) -> Unit,
    val onTitleClicked: () -> Unit,
)
