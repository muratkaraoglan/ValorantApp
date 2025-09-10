package com.reve.valorantapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ability(
    val slot: String,
    val displayName: String,
    val description: String,
    val displayIcon: String?,
): Parcelable
