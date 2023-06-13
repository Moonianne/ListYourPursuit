package com.gerardojim.listyourpursuit.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gerardojim.listyourpursuit.Task

@Composable
fun TaskListScreen(tasks: List<Task>) {
    MaterialTheme {
        LazyColumn(Modifier.fillMaxWidth()) {
            this.items(tasks) {
                TaskRow(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskRow(task: Task) {
    ListItem(Modifier.padding(vertical = 4.dp)) {
        Row {
            Checkbox(
                checked = task.isComplete,
                onCheckedChange = task.onIsCompleteClicked,
            )
            Text(
                text = task.title,
                modifier = Modifier.clickable { task.onTitleClicked },
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    TaskListScreen(
        listOf(
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
    )
}
