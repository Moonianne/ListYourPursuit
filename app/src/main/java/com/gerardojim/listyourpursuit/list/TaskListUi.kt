package com.gerardojim.listyourpursuit.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gerardojim.listyourpursuit.list.TaskListViewModel.TaskListState

private const val NEW_TASK: Int = -1

@Composable
fun TaskListScreen(
    list: TaskListState,
    onGoToTaskEntry: (Int) -> Unit,
) {
    Scaffold(
        topBar = {},
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AddTasksButton {
                onGoToTaskEntry(NEW_TASK)
                list.onAddNewTaskClicked()
            }
        },
    ) {
        LazyColumn(
            Modifier
                .padding(it)
                .fillMaxWidth(),
        ) {
            items(list.tasks) { task ->
                TaskRow(
                    task = task,
                    onClick = onGoToTaskEntry,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskRow(
    task: Task,
    onClick: (Int) -> Unit,
) {
    ListItem(Modifier.padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(task.uuid) },
        ) {
            Checkbox(
                checked = task.isComplete,
                onCheckedChange = task.onIsCompleteClicked,
            )
            Text(
                text = task.title,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }
    }
}

@Composable
fun AddTasksButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Default.Add, "New Task")
    }
}

@Preview
@Composable
fun Preview() {
    val state = TaskListState(
        tasks = listOf(
            Task(
                isComplete = true,
                title = "Hello, World!",
                details = "",
                onIsCompleteClicked = {},
                onTitleClicked = {},
            ),
            Task(
                isComplete = true,
                title = "Hello, World!",
                details = "",
                onIsCompleteClicked = {},
                onTitleClicked = {},
            ),
            Task(
                isComplete = true,
                title = "Hello, World!",
                details = "",
                onIsCompleteClicked = {},
                onTitleClicked = {},
            ),
            Task(
                isComplete = true,
                title = "Hello, World!",
                details = "",
                onIsCompleteClicked = {},
                onTitleClicked = {},
            ),
            Task(
                isComplete = true,
                title = "Hello, World!",
                details = "",
                onIsCompleteClicked = {},
                onTitleClicked = {},
            ),
            Task(
                isComplete = true,
                title = "Hello, World!",
                details = "",
                onIsCompleteClicked = {},
                onTitleClicked = {},
            ),
            Task(
                isComplete = true,
                title = "Hello, World!",
                details = "",
                onIsCompleteClicked = {},
                onTitleClicked = {},
            ),
            Task(
                isComplete = true,
                title = "Hello, World!",
                details = "",
                onIsCompleteClicked = {},
                onTitleClicked = {},
            ),
        ),
        currentTaskUuid = null,
        onAddNewTaskClicked = {},
    )
    TaskListScreen(state) {}
}
