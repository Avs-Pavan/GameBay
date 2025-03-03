package com.pavan.gamebay.feature.gameschedule.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response for a date.
 *
 * @property numeric The numeric representation of the date.
 * @property text The textual representation of the date.
 * @property time The time associated with the date.
 * @property timestamp The timestamp of the date.
 * @property isTBA Indicates if the date is to be announced.
 */
data class MDateResponse(
    @SerializedName("Numeric")
    val numeric: String? = null,
    @SerializedName("Text")
    val text: String? = null,
    @SerializedName("Time")
    val time: String? = null,
    @SerializedName("Timestamp")
    val timestamp: String? = null,
    @SerializedName("IsTBA")
    val isTBA: String? = null,
)