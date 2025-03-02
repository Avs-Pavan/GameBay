package com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game

import androidx.compose.runtime.Immutable

@Immutable
data class TeamUIModel(
    val triCode: String,
    val name: String,
    val record: String,
    val wins: Int,
    val losses: Int,
    val teamLogoUrl: String,
)