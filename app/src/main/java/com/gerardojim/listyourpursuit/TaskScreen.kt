package com.gerardojim.listyourpursuit

sealed interface TaskScreen {
    val key: String

    object TaskList : TaskScreen {
        const val KEY: String = "TaskList"
        override val key: String = KEY
    }

    data class TaskEntry(val currentTaskUuid: Int = -1) : TaskScreen {
        override val key: String = KEY
        companion object {
            const val KEY: String = "TaskEntry"
            const val ARG: String = "taskUuid"
        }
    }

    companion object {
        fun getScreen(key: String): TaskScreen = when (key) {
            TaskList.KEY -> TaskList
            TaskEntry.KEY -> TaskEntry()
            else -> error("Unknown screen key `$key` cannot be evaluated.")
        }
    }
}
