package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Represents a filter item with an ID and a name.
 *
 * @property id The unique identifier for the filter item.
 * @property name The name of the filter item.
 */
data class FilterItem(
    val id: String,
    val name: String
)