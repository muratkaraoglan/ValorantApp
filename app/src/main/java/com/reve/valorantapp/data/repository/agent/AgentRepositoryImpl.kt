package com.reve.valorantapp.data.repository.agent

import com.reve.valorantapp.common.UiState
import com.reve.valorantapp.data.api.AgentService
import com.reve.valorantapp.data.model.toAgentUIModel
import com.reve.valorantapp.domain.model.AgentUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AgentRepositoryImpl @Inject constructor(private val agentService: AgentService):AgentRepository {
    override suspend fun getAgents(): Flow<UiState<List<AgentUIModel>>> = flow {
        try {
            emit(UiState.Loading)
            val result = agentService.getAgents()
            when(result.code()) {
                200 -> {
                    val mappedData = result.body()?.data?.filter { it.isPlayableCharacter }
                        ?.map { it.toAgentUIModel() }
                        ?.sortedBy { it.displayName }
                    emit(UiState.Success(mappedData as List<AgentUIModel>))
                }

                else -> {
                    emit(UiState.Error(result.message()))
                }
            }
        }
        catch (e: Exception) {
            emit(UiState.Error(e.localizedMessage))
        }
    }
}