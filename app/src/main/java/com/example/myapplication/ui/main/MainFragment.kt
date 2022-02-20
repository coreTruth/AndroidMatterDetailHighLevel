package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.Event
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.ui.EventAdapter
import com.example.myapplication.util.disableUI
import com.example.myapplication.util.setMarginTop
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var eventAdapter: EventAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { _, insets ->
            binding.toolbar.setMarginTop(insets.getInsets(WindowInsetsCompat.Type.systemBars()).top)
            WindowInsetsCompat.CONSUMED
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        eventAdapter = EventAdapter(::onMainBreedClick)
        rvEvents.adapter = eventAdapter
    }

    private fun initViewModel() = with(viewModel) {
        loading.observe(viewLifecycleOwner, ::showProgressBar)
        events.observe(viewLifecycleOwner, eventAdapter::submitList)
    }

    private fun onMainBreedClick(event: Event) =
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(event)
        )

    private fun showProgressBar(show: Boolean) {
        binding.progressBar.isVisible = show
        disableUI(show)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}