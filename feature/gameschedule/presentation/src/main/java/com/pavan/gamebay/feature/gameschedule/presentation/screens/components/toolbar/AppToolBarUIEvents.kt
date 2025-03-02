package com.pavan.gamebay.feature.gameschedule.presentation.screens.components.toolbar

sealed interface AppToolBarUIEvents {
    data object OnMenuClick : AppToolBarUIEvents
}