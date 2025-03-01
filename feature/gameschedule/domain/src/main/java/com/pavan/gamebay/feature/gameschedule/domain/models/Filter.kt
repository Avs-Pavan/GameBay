package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Represents a filter model used in the game schedule feature.
 *
 * @property name The name of the filter.
 * @property queryParameter The query parameter associated with the filter.
 * @property placeholder The placeholder text for the filter input.
 * @property type The type of the filter.
 * @property current The current value of the filter.
 * @property items The list of filter items.
 */
data class Filter(
    val name: String,
    val queryParameter: String,
    val placeholder: String,
    val type: String,
    val current: String,
    val items: List<FilterItem>
)