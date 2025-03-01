package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * YinzNode model representing the details of a game schedule node.
 *
 * @property inVenue Indicates if the event is in the venue.
 * @property venueStatus The status of the venue.
 * @property carrier The carrier information.
 * @property ads The advertisement details.
 * @property generated The generated timestamp.
 */
data class YinzNode(
    val inVenue: Boolean,
    val venueStatus: String,
    val carrier: String,
    val ads: String,
    val generated: String
)