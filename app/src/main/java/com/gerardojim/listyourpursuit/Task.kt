package com.gerardojim.listyourpursuit

data class Task(
    val isComplete: Boolean,
    val title: String,
    val details: String,
    val onIsCompleteClicked: (Boolean) -> Unit,
    val onTitleClicked: () -> Unit,
)
