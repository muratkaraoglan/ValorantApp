package com.reve.valorantapp.ui.adapter

import com.reve.valorantapp.domain.model.AgentUIModel

interface OnAgentClickListener {
    fun onAgentClick(agent: AgentUIModel)
}