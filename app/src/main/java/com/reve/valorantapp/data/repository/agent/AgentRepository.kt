package com.reve.valorantapp.data.repository.agent

import com.reve.valorantapp.common.UiState
import com.reve.valorantapp.domain.model.AgentUIModel
import kotlinx.coroutines.flow.Flow

interface AgentRepository {

    suspend fun getAgents(): Flow<UiState<List<AgentUIModel>>>
}