package com.reve.valorantapp.data.api

import com.reve.valorantapp.data.model.Agents
import retrofit2.Response
import retrofit2.http.GET

interface AgentService {

    @GET("agents")
    suspend fun getAgents() :Response<Agents>
}