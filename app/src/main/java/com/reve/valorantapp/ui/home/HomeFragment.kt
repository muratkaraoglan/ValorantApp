package com.reve.valorantapp.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.reve.valorantapp.R
import com.reve.valorantapp.common.AgentRecyclerViewItemPadding
import com.reve.valorantapp.databinding.FragmentHomeBinding
import com.reve.valorantapp.domain.model.AgentUIModel
import com.reve.valorantapp.ui.adapter.AgentAdapter
import com.reve.valorantapp.ui.adapter.OnAgentClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.log

@AndroidEntryPoint
class HomeFragment : Fragment(), OnAgentClickListener {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var isDataLoaded = false
    private var isSplashAnimationFinished = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAgentRecyclerView()
        initSearch()
    }

    private fun initAgentRecyclerView() {
        binding.agentsRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    handleState(state)
                }
            }
        }

        val paddingPx = resources.getDimensionPixelSize(R.dimen.horizontal_rView_margin)
        binding.agentsRecyclerView.addItemDecoration(AgentRecyclerViewItemPadding(paddingPx))
    }

    private fun handleState(state: AgentState) {
        when {
            state.isLoading -> {
                loadSplashLogo()
            }

            state.error != null -> {

            }

            state.agents.isNotEmpty() -> {
                if (!isDataLoaded) {
                    isDataLoaded = true
                    if (isSplashAnimationFinished)
                        binding.splashLogoLayout.visibility = View.GONE
                }
                val adapter = AgentAdapter(requireContext(), state.agents, this)
                binding.agentsRecyclerView.adapter = adapter
            }
        }
    }

    private fun initSearch() {
        binding.searchEditText.doOnTextChanged { inputText, _, _, _ ->
            viewModel.onSearchQueryChanged(inputText.toString())
        }
    }

    private fun loadSplashLogo() {
        binding.splashLogoLayout.visibility = View.VISIBLE
        binding.splashLogo.animate()
            .alpha(1f)
            .setDuration(2000)
            .withEndAction({
                isSplashAnimationFinished = true
                Handler(Looper.getMainLooper()).postDelayed({
                    if (isDataLoaded) {
                        binding.splashLogoLayout.visibility = View.GONE
                    }
                }, 1000)
            })
    }

    override fun onAgentClick(agent: AgentUIModel) {
        val toAgent = HomeFragmentDirections.toAgentDetail(agent)
        findNavController().navigate(toAgent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}