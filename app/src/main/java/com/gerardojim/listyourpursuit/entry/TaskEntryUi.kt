package com.gerardojim.listyourpursuit.entry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TaskEntryScreen(
    entry: TaskEntry,
    onGoToTaskList: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(4.dp),
    ) {
        var title by remember(key1 = entry.uuid, key2 = entry.title) { mutableStateOf(entry.title) }
        var details by remember(key1 = entry.uuid, key2 = entry.details) { mutableStateOf(entry.details) }
        TitleField(
            value = title,
            onValueChange = { title = it },
        )
        NotesField(
            value = details,
            onValueChange = { details = it },
            modifier = Modifier.weight(1f),
        )
        SaveButton(
            onClick = {
                entry.onSaveClicked(title, details)
                onGoToTaskList()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
fun TitleField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(
                onClick = {},
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = null,
                        tint = Color.DarkGray,
                    )
                },
            )
        },
        singleLine = true,
    )
}

@Composable
fun NotesField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        maxLines = 20,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
    )
}

@Composable
fun SaveButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier,
        content = { Text(text = "Save") },
    )
}

@Preview
@Composable
fun PreviewTaskEntry() {
//    TaskEntryScreen(
//        MutableStateFlow(
//            TaskEntry(
//                uuid = null,
//                isComplete = false,
//                title = "Hello, world!",
//                onSaveClicked = { _, _ -> },
//                details = "Goodbye, world!",
//            ),
//        ).collectAsState(),
//    ) {}
}
