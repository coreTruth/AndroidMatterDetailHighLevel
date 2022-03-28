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
import com.example.myapplication.data.entity.BusinessItem
import com.example.myapplication.data.entity.BusinessResponse
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.ui.BusinessAdapter
import com.example.myapplication.util.disableUI
import com.example.myapplication.util.setMarginTop
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var businessAdapter: BusinessAdapter

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
        businessAdapter = BusinessAdapter(::onMainBreedClick)
        rvEvents.adapter = businessAdapter
        btPizza.setOnClickListener {
            viewModel.getBusiness("pizza", "111 Sutter st, San Francisco")
        }

        btBeer.setOnClickListener {
            viewModel.getBusiness("beer", "111 Sutter st, San Francisco")
        }
    }

    private fun initViewModel() = with(viewModel) {
        loading.observe(viewLifecycleOwner, ::showProgressBar)
        business.observe(viewLifecycleOwner, businessAdapter::submitList)
        getBusiness("pizza", "111 Sutter st, San Francisco")
    }

    private fun onMainBreedClick(business: BusinessItem) =
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(business)
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
