package com.reve.valorantapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reve.valorantapp.common.UiState
import com.reve.valorantapp.data.repository.agent.AgentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val agentRepository: AgentRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(AgentState())
    val state: StateFlow<AgentState> = _state

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        viewModelScope.launch {
            agentRepository.getAgents().collect { result ->
                when (result) {
                    UiState.Loading -> {
                        _state.value = AgentState(isLoading = true)
                    }

                    is UiState.Success -> {
                        result.data.let { agents ->
                            _state.value = AgentState(
                                agents = agents,
                                allAgents = agents
                            )
                        }
                    }

                    is UiState.Error -> {
                        _state.value = AgentState(error = result.errorMessage)
                    }
                }
            }
        }

        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    searchAgent(query)
                }
        }
    }

    private fun searchAgent(searchInput: String) {
        val currentState = _state.value
        val allAgents = currentState.allAgents

        if (searchInput.isNotEmpty()) {
            val filteredList = allAgents.filter { agent ->
                agent.displayName.contains(searchInput, ignoreCase = true)
            }
            _state.value = currentState.copy(agents = filteredList)
        } else {
            _state.value = currentState.copy(agents = allAgents)
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

}