package com.reve.valorantapp.data.model

import com.reve.valorantapp.domain.model.AgentUIModel

data class Agent(
    val abilities: List<Ability>,
    val assetPath: String,
    val background: String,
    val backgroundGradientColors: List<String>,
    val bustPortrait: String,
    val characterTags: List<String>,
    val description: String,
    val developerName: String,
    val displayIcon: String,
    val displayIconSmall: String,
    val displayName: String,
    val fullPortrait: String,
    val fullPortraitV2: String,
    val isAvailableForTest: Boolean,
    val isBaseContent: Boolean,
    val isFullPortraitRightFacing: Boolean,
    val isPlayableCharacter: Boolean,
    val killfeedPortrait: String,
    val recruitmentData: RecruitmentData,
    val role: Role,
    val uuid: String,
    val voiceLine: VoiceLine
)

fun Agent.toAgentUIModel() = AgentUIModel(
    uuid = uuid,
    displayName = displayName,
    description = description,
    displayIcon = displayIcon,
    fullPortrait = fullPortrait,
    background = background,
    role = role,
    abilities = abilities,
)