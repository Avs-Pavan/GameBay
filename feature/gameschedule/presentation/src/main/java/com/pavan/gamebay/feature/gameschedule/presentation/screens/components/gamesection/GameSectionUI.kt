package com.pavan.gamebay.feature.gameschedule.presentation.screens.components.gamesection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pavan.gamebay.core.presentaion.designsystem.ui.theme.GameBayTheme
import com.pavan.gamebay.core.presentaion.designsystem.ui.theme.regular
import com.pavan.gamebay.feature.gameschedule.presentation.screens.PreviewConstants.SAMPLE_GAME_SECTION_UI
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game.GameCardUI
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game.GameCardUIEvents

@Composable
fun GameSectionUI(
    modifier: Modifier = Modifier,
    gameSectionUIModel: GameSectionUIModel,
    onEvent: (GameCardUIEvents) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = gameSectionUIModel.heading,
                style = MaterialTheme.typography.bodyMedium.regular(),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
        gameSectionUIModel.games.forEachIndexed { index, game ->
            GameCardUI(
                gameModel = game,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) { gameCardUIEvents ->
                onEvent(gameCardUIEvents)
            }
            if (index != gameSectionUIModel.games.size - 1)
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}


@Preview
@Composable
fun GameSectionUIPreview() {
    GameBayTheme {
        GameSectionUI(
            gameSectionUIModel = SAMPLE_GAME_SECTION_UI
        )
    }
}