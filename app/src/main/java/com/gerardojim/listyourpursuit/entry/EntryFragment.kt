package com.gerardojim.listyourpursuit.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gerardojim.listyourpursuit.databinding.FragmentEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EntryFragment : Fragment() {
    private val viewModel: EntryViewModel by viewModels()
    private val args: EntryFragmentArgs by navArgs()
    private lateinit var binding: FragmentEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setTask(args.taskUuid)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.state.collectLatest { entry ->
                with(binding) {
                    entryName.setText(entry.title)
                    entryNotes.setText(entry.details)
                    saveButton.setOnClickListener {
                        entry.onSaveClicked(
                            entryName.text.toString(),
                            entryNotes.text.toString(),
                        )
                        findNavController()
                            .navigate(EntryFragmentDirections.actionEntryFragmentToTaskListFragment())
                    }
                }
            }
        }
    }
}
