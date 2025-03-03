package com.pavan.gamebay.feature.gameschedule.presentation.screens.components.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pavan.gamebay.core.presentaion.designsystem.ui.components.LocalizedPreview
import com.pavan.gamebay.core.presentaion.designsystem.ui.theme.GameBayTheme
import com.pavan.gamebay.core.presentaion.designsystem.ui.theme.bold
import com.pavan.gamebay.feature.gameschedule.presentation.R

/**
 * A composable function that represents the top app bar UI.
 *
 * @param onEvent A lambda function that handles events from the toolbar.
 * @see AppToolBarUIEvents for the different events that can be emitted.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBarUI(
    onEvent: (AppToolBarUIEvents) -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        navigationIcon = {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_menu_24),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .clickable {
                            onEvent(AppToolBarUIEvents.OnMenuClick)
                        }
                )
            }
        },
        title = {
            Text(
                text = stringResource(R.string.title_schedule),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 48.dp)
            )
        }
    )
}

/**
 * A preview composable function for the AppToolBarUI.
 */
@LocalizedPreview
@Composable
fun AppToolBarUIPreview() {
    GameBayTheme {
        AppToolBarUI()
    }
}