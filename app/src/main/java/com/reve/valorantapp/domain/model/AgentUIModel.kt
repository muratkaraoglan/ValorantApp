package com.reve.valorantapp.domain.model

import android.os.Parcelable
import com.reve.valorantapp.data.model.Ability
import com.reve.valorantapp.data.model.Role
import kotlinx.parcelize.Parcelize

@Parcelize
data class AgentUIModel(
    val abilities: List<Ability>,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val fullPortrait: String,
    val background: String,
    val role: Role?,
    val uuid: String
):Parcelable   {
}