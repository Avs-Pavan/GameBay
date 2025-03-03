package com.pavan.gamebay.feature.gameschedule.presentation.screens.components.toolbar

/**
 * Represents UI events for the App Toolbar.
 */
sealed interface AppToolBarUIEvents {
    /**
     * Event triggered when the menu is clicked.
     */
    data object OnMenuClick : AppToolBarUIEvents
}