package com.reve.valorantapp.di

import com.reve.valorantapp.data.api.AgentService
import com.reve.valorantapp.data.repository.agent.AgentRepository
import com.reve.valorantapp.data.repository.agent.AgentRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun  provideAgentService(retrofit: Retrofit): AgentService {
        return retrofit.create(AgentService::class.java)
    }

    @Provides
    @Singleton
    fun provideAgentRepository(agentService: AgentService) : AgentRepository {
        return AgentRepositoryImpl(agentService)
    }
}