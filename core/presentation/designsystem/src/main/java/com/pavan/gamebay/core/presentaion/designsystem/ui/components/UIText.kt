package com.pavan.gamebay.core.presentaion.designsystem.ui.components

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource


/**
 * Represents a text that can be either static or dynamic.
 * Static text is a simple string, while dynamic text is a string resource with format arguments.
 * This class is useful for localizing text in a composable function.
 */
sealed interface UIText {
    /**
     * Represents a static text.
     *
     * @property text The static text.
     */
    data class Static(val text: String) : UIText

    /**
     * Represents a dynamic text that can be formatted.
     *
     * @property textRes The resource ID of the text.
     * @property formatArgs The arguments to format the text.
     */
    class Dynamic(
        @StringRes val textRes: Int,
        vararg val formatArgs: Any
    ) : UIText

    /**
     * Returns the text as a string.
     *
     * @param context The context to use for retrieving the string resource.
     * @return The text as a string.
     */
    fun asString(context: Context): String = when (this) {
        is Static -> text
        is Dynamic -> context.getString(textRes, *formatArgs)
    }

    /**
     * Returns the text as a string in a composable function.
     *
     * @return The text as a string.
     */
    @Composable
    fun asString(): String = when (this) {
        is Static -> text
        is Dynamic -> stringResource(textRes, *formatArgs)
    }
}