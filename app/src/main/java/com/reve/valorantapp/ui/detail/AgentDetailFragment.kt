package com.reve.valorantapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.reve.valorantapp.R
import com.reve.valorantapp.databinding.FragmentAgentDetailBinding
import com.reve.valorantapp.domain.model.AgentUIModel
import com.reve.valorantapp.ui.adapter.ability.AbilityAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentDetailFragment : Fragment() {

    private var _binding: FragmentAgentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: AgentDetailFragmentArgs by navArgs()
    private val agent: AgentUIModel by lazy { args.agent }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.agentDetailAbilitiesRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        loadAgentDetail()
        initAbilities()
    }

    private fun loadAgentDetail() {
        Glide.with(requireContext()).load(agent.background)
            .into(binding.agentDetailBackgroundImage)

        Glide.with(requireContext())
            .load(agent.fullPortrait)
            .into(binding.agentDetailFullPortraitImage)

        binding.agentDetailNameText.text = agent.displayName
        binding.agentDetailRoleText.text = agent.role?.displayName
    }

    private fun initAbilities() {

        val adapter = AbilityAdapter(requireContext(),agent.abilities)
        binding.agentDetailAbilitiesRV.adapter = adapter
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}