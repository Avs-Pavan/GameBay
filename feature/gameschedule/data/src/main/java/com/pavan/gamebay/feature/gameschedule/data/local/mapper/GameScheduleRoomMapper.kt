package com.pavan.gamebay.feature.gameschedule.data.local.mapper

import com.pavan.gamebay.core.domain.DefaultDispatcher
import com.pavan.gamebay.core.domain.IMapper
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ButtonEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.CardDataEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.FilterWithItems
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameDateEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameSectionWithGames
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameWithButtons
import com.pavan.gamebay.feature.gameschedule.data.local.entities.OpponentEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleWithRelations
import com.pavan.gamebay.feature.gameschedule.data.local.entities.TeamEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.TicketsEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.YinzNodeEntity
import com.pavan.gamebay.feature.gameschedule.domain.models.ByeGame
import com.pavan.gamebay.feature.gameschedule.domain.models.CardData
import com.pavan.gamebay.feature.gameschedule.domain.models.Filter
import com.pavan.gamebay.feature.gameschedule.domain.models.FilterItem
import com.pavan.gamebay.feature.gameschedule.domain.models.FinalGame
import com.pavan.gamebay.feature.gameschedule.domain.models.Game
import com.pavan.gamebay.feature.gameschedule.domain.models.GameDate
import com.pavan.gamebay.feature.gameschedule.domain.models.GameOutcome
import com.pavan.gamebay.feature.gameschedule.domain.models.GameSection
import com.pavan.gamebay.feature.gameschedule.domain.models.MButton
import com.pavan.gamebay.feature.gameschedule.domain.models.Opponent
import com.pavan.gamebay.feature.gameschedule.domain.models.Schedule
import com.pavan.gamebay.feature.gameschedule.domain.models.ScheduledGame
import com.pavan.gamebay.feature.gameschedule.domain.models.Team
import com.pavan.gamebay.feature.gameschedule.domain.models.Tickets
import com.pavan.gamebay.feature.gameschedule.domain.models.YinzNode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameScheduleRoomMapper @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : IMapper<ScheduleWithRelations, Schedule> {
    override suspend fun map(input: ScheduleWithRelations): Schedule {
        return withContext(defaultDispatcher) {
            input.toDomain()
        }
    }

    private fun ScheduleWithRelations.toDomain(): Schedule = Schedule(
        team = schedule.team.toDomain(),
        defaultGameId = schedule.defaultGameId,
        gameSection = gameSections.map { it.toDomain() },
        filters = filters.map { it.toDomain() },
        yinzNode = schedule.yinzNode.toDomain()
    )

    private fun TeamEntity.toDomain() = Team(
        triCode = triCode,
        fullName = fullName,
        name = name,
        city = city,
        record = record,
        wins = wins,
        losses = losses,
        winPercentage = winPercentage,
        primaryColor = primaryColor
    )

    private fun GameSectionWithGames.toDomain() = GameSection(
        heading = section.heading,
        game = games.map { it.toDomain() }
    )

    private fun GameWithButtons.toDomain(): Game = when (game.type) {
        "F" -> FinalGame(
            week = game.week,
            label = game.label,
            tv = game.tv!!,
            radio = game.radio!!,
            venue = game.venue!!,
            gameOutcome = when (game.wlt) {
                "W" -> GameOutcome.WIN
                "L" -> GameOutcome.LOSS
                "T" -> GameOutcome.TIE
                else -> throw IllegalStateException("Invalid WLT: ${game.wlt}")
            },
            gameState = game.gameState,
            clock = game.clock,
            quarterShorthand = game.quarterShorthand,
            quarter = game.quarter,
            awayScore = game.awayScore!!,
            homeScore = game.homeScore!!,
            down = game.down!!,
            isHome = game.isHome!!,
            scheduleHeader = game.scheduleHeader,
            isSuperStadium = game.isSuperStadium!!,
            cardData = game.cardData.toDomain(),
            id = game.id,
            date = game.date!!.toDomain(),
            opponent = game.opponent!!.toDomain(),
            tickets = game.tickets!!.toDomain(),
            result = game.result!!,
            home = game.home!!,
            buttons = buttons.filter { !it.isSecondary }.map { it.toDomain() },
            secondaryButtons = buttons.filter { it.isSecondary }.map { it.toDomain() }
        )

        "B" -> ByeGame(
            week = game.week,
            label = game.label,
            gameState = game.gameState,
            clock = game.clock,
            quarterShorthand = game.quarterShorthand,
            quarter = game.quarter,
            scheduleHeader = game.scheduleHeader,
            cardData = game.cardData.toDomain(),
            id = game.id
        )

        "S" -> ScheduledGame(
            week = game.week,
            label = game.label,
            tv = game.tv!!,
            radio = game.radio!!,
            venue = game.venue!!,
            gameState = game.gameState,
            clock = game.clock,
            quarterShorthand = game.quarterShorthand,
            quarter = game.quarter,
            isHome = game.isHome!!,
            scheduleHeader = game.scheduleHeader,
            isSuperStadium = game.isSuperStadium!!,
            cardData = game.cardData.toDomain(),
            id = game.id,
            date = game.date!!.toDomain(),
            opponent = game.opponent!!.toDomain(),
            tickets = game.tickets!!.toDomain(),
            home = game.home!!,
            buttons = buttons.filter { !it.isSecondary }.map { it.toDomain() },
            secondaryButtons = buttons.filter { it.isSecondary }.map { it.toDomain() }
        )

        else -> throw IllegalStateException("Unknown game type: ${game.type}")
    }

    private fun CardDataEntity.toDomain(): CardData {
        return CardData(clickThroughURL, isDefault)
    }

    private fun GameDateEntity.toDomain(): GameDate {
        return GameDate(numeric, text, time, timestamp, isTBA, isTba)
    }

    private fun OpponentEntity.toDomain(): Opponent {
        return Opponent(triCode, fullName, name, city, record)
    }

    private fun TicketsEntity.toDomain() = Tickets(hasLink, label, link)
    private fun ButtonEntity.toDomain() = MButton(title, url)
    private fun FilterWithItems.toDomain() = Filter(
        name = filter.name,
        queryParameter = filter.queryParameter,
        placeholder = filter.placeholder,
        type = filter.type,
        current = filter.current,
        items = items.map { FilterItem(it.id, it.name) }
    )

    private fun YinzNodeEntity.toDomain() = YinzNode(inVenue, venueStatus, carrier, ads, generated)
}