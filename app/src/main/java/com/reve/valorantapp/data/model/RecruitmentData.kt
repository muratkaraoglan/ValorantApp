package com.reve.valorantapp.data.model

data class RecruitmentData(
    val counterId: String,
    val milestoneId: String,
    val milestoneThreshold: Long,
    val useLevelVpCostOverride: Boolean,
    val levelVpCostOverride: Long,
    val startDate: String,
    val endDate: String,
)
