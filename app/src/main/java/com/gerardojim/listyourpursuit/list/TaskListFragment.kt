package com.gerardojim.listyourpursuit.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.gerardojim.listyourpursuit.databinding.FragmentTaskListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskListFragment : Fragment() {
    private val viewModel: TaskListViewModel by viewModels()
    private lateinit var binding: FragmentTaskListBinding
    private lateinit var adapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTaskListBinding.inflate(inflater, container, false)
        adapter = TasksAdapter()
        binding.taskEntryRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getState().collectLatest { state ->
                    adapter.setTasks(state.tasks)
                    state.currentTaskUuid?.let {
                        navigateToDetails(it)
                        viewModel.onNavigated()
                    }
                    binding.fab.setOnClickListener {
                        state.onAddNewTaskClicked()
                        navigateToDetails(null)
                    }
                }
            }
        }
        return binding.root
    }

    private fun navigateToDetails(uuid: Int?) {
        findNavController().navigate(
            uuid?.let { TaskListFragmentDirections.actionTaskListFragmentToEntryFragment(uuid) }
                ?: TaskListFragmentDirections.actionTaskListFragmentToEntryFragment(),
        )
    }
}
