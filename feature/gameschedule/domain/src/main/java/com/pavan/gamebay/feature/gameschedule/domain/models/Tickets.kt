package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Data class representing Tickets.
 *
 * @property hasLink Indicates if there is a link associated with the ticket.
 * @property label The label of the ticket.
 * @property link The URL link of the ticket.
 */
data class Tickets(
    val hasLink: Boolean,
    val label: String,
    val link: String
)