package com.reve.valorantapp.ui.home

import com.reve.valorantapp.domain.model.AgentUIModel

data class AgentState(
    val isLoading: Boolean = false,
    val agents: List<AgentUIModel> = emptyList(),
    val allAgents: List<AgentUIModel> = emptyList(),
    val error: String? = null
    )
