package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Data class representing the card data.
 *
 * @property clickThroughURL The URL to navigate to when the card is clicked.
 * @property isDefault Indicates if the card is the default one. JSON uses "True"/"False", handled by adapter.
 */
data class CardData(
    val clickThroughURL: String,
    val isDefault: Boolean // JSON uses "True"/"False", handled by adapter
)