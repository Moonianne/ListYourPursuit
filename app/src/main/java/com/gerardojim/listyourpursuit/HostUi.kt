package com.gerardojim.listyourpursuit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.gerardojim.listyourpursuit.entry.EntryViewModel
import com.gerardojim.listyourpursuit.list.TaskListViewModel
import com.gerardojim.listyourpursuit.entry.TaskEntryScreen
import com.gerardojim.listyourpursuit.list.TaskListScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ScreenHost() {
    val viewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    LaunchedEffect(
        key1 = "mainStateChangeHandler",
    ) {
        viewModel.state.collectLatest { screen ->
            navController.currentDestination?.let {
                if (it.route != screen.key) {
                    navController.navigate(
                        route = screen.getRoute(),
                        navOptions = navOptions {
                            popUpTo(TaskScreen.TaskList.KEY)
                            launchSingleTop = true
                        },
                    )
                }
            }
        }
    }
    viewModel.syncWithNavigation(navController)
    MainNavHost(navController = navController, mainViewModel = viewModel)
}

@Composable
private fun MainNavHost(
    navController: NavHostController,
    mainViewModel: MainViewModel,
) {
    NavHost(navController = navController, startDestination = TaskScreen.TaskList.KEY) {
        composable(TaskScreen.TaskList.KEY) {
            TaskListScreen(
                list = hiltViewModel<TaskListViewModel>().getState().collectAsState().value,
            ) { mainViewModel.onGoToTaskEntry(it) }
        }
        composable(
            route = "${TaskScreen.TaskEntry.KEY}/{${TaskScreen.TaskEntry.ARG}}",
            arguments = listOf(navArgument(TaskScreen.TaskEntry.ARG) { type = NavType.IntType }),
        ) {
            val uuid = checkNotNull(it.arguments).getInt(TaskScreen.TaskEntry.ARG)
            val state = hiltViewModel<EntryViewModel>()
                .apply { setTask(uuid) }.state.collectAsState()
            TaskEntryScreen(
                entry = state.value,
                onGoToTaskList = { mainViewModel.onGoToTaskList() },
            )
        }
    }
}

private fun MainViewModel.syncWithNavigation(navController: NavHostController) {
    navController.addOnDestinationChangedListener { _, destination, _ ->
        destination.route?.let {
            if (!it.contains(state.value.key)) {
                onSystemScreenChange(it.substringBefore("/"))
            }
        }
    }
}

private fun TaskScreen.getRoute(): String = when (this) {
    is TaskScreen.TaskEntry -> "$key/$currentTaskUuid"
    is TaskScreen.TaskList -> key
}
