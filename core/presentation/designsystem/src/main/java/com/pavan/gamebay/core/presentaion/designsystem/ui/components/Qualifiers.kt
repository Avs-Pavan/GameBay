package com.pavan.gamebay.core.presentaion.designsystem.ui.components

import androidx.compose.ui.tooling.preview.Preview


/**
 * Preview annotation for displaying localized previews in English and Spanish.
 * Supports the following locales:
 * - English
 * - Spanish
 */
@Preview(name = "Localized Preview - English", group = "Localized", locale = "en")
@Preview(name = "Localized Preview - Spanish", group = "Localized", locale = "es")
annotation class LocalizedPreview