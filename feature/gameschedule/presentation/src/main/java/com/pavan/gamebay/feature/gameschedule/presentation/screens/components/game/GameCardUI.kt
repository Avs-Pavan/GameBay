package com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pavan.gamebay.core.presentaion.designsystem.ui.theme.GameBayTheme
import com.pavan.gamebay.core.presentaion.designsystem.ui.theme.bold
import com.pavan.gamebay.core.presentaion.designsystem.ui.theme.regular
import com.pavan.gamebay.feature.gameschedule.domain.models.GameType
import com.pavan.gamebay.feature.gameschedule.presentation.R
import com.pavan.gamebay.feature.gameschedule.presentation.screens.PreviewConstants.SAMPLE_GAME_UI_MODEL


@Composable
fun GameCardUI(
    modifier: Modifier = Modifier,
    gameModel: GameUIModel,
    onEvent: (GameCardUIEvents) -> Unit = {}
) {
    if (gameModel.gameType == GameType.BYE) {
        Row(
            modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .heightIn(min = 200.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.lable_bye),
                style = MaterialTheme.typography.titleLarge.bold(),
                modifier = modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    } else {

        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .clickable {
                    onEvent(GameCardUIEvents.OnGameClick(gameModel))
                }) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = gameModel.homeTeam.name,
                    style = MaterialTheme.typography.bodyLarge.regular(),
                    modifier = Modifier.align(Alignment.TopStart)
                )

                Text(
                    text = gameModel.opponentTeam.name,
                    style = MaterialTheme.typography.bodyLarge.regular(),
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (gameModel.gameType == GameType.FINAL) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = gameModel.homeScore.toString(),
                        style = MaterialTheme.typography.titleMedium.bold(),
                        modifier = Modifier.align(Alignment.TopStart)
                    )

                    Row {

                    }

                    Text(
                        text = gameModel.awayScore.toString(),
                        style = MaterialTheme.typography.titleMedium.bold(),
                        modifier = Modifier.align(Alignment.TopEnd)
                    )
                }
            } else {

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = gameModel.homeTeam.record,
                        style = MaterialTheme.typography.bodySmall.regular(),
                        modifier = Modifier.align(Alignment.TopStart),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Row {

                    }

                    Text(
                        text = gameModel.opponentTeam.record,
                        style = MaterialTheme.typography.bodySmall.regular(),
                        modifier = Modifier.align(Alignment.TopEnd),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = gameModel.gameDate.dateText,
                    style = MaterialTheme.typography.bodySmall.regular(),
                    modifier = Modifier.align(Alignment.TopStart)
                )
                Text(
                    text = gameModel.week,
                    style = MaterialTheme.typography.bodySmall.regular(),
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = if (gameModel.gameType == GameType.FINAL) gameModel.gameState else gameModel.gameDate.dateTime,
                    style = MaterialTheme.typography.bodySmall.regular(),
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
            if (gameModel.tv.isNotEmpty() || gameModel.radio.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = if (gameModel.tv.isNotEmpty()) gameModel.tv else gameModel.radio,
                        style = MaterialTheme.typography.bodySmall.regular(),
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun GameCardUIScheduledPreview() {
    GameBayTheme {
        GameCardUI(
            gameModel = SAMPLE_GAME_UI_MODEL
        )
    }
}

@Composable
@Preview
fun GameCardUIFinalPreview() {
    GameBayTheme {
        GameCardUI(
            gameModel = SAMPLE_GAME_UI_MODEL.copy(
                gameType = GameType.FINAL
            )
        )
    }
}

@Composable
@Preview
fun GameCardUIByePreview() {
    GameBayTheme {
        GameCardUI(
            gameModel = SAMPLE_GAME_UI_MODEL.copy(
                gameType = GameType.BYE
            )
        )
    }
}

