package com.pavan.gamebay.feature.gameschedule.data.remote.mapper

import com.pavan.gamebay.core.domain.DefaultDispatcher
import com.pavan.gamebay.core.domain.IMapper
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ButtonEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.CardDataEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.FilterEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.FilterItemEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.FilterWithItems
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameDateEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameSectionEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameSectionWithGames
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameWithButtons
import com.pavan.gamebay.feature.gameschedule.data.local.entities.OpponentEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleWithRelations
import com.pavan.gamebay.feature.gameschedule.data.local.entities.TeamEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.TicketsEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.YinzNodeEntity
import com.pavan.gamebay.feature.gameschedule.data.remote.model.CardDataResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.FilterItemResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.FilterResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.GameDateResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.GameResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.GameSectionResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.OpponentResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.ScheduleResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.TeamResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.TicketsResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.YinzNodeResponse
import com.pavan.gamebay.feature.gameschedule.data.repo.GameScheduleRepo.Companion.SCHEDULE_ID
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameScheduleRemoteDataMapper @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : IMapper<ScheduleResponse, ScheduleWithRelations> {
    override suspend fun map(input: ScheduleResponse): ScheduleWithRelations {
        return withContext(defaultDispatcher) {
            ScheduleWithRelations(
                schedule = input.toEntity(SCHEDULE_ID),
                gameSections = input.gameSection.map {
                    GameSectionWithGames(
                        section = it.toEntity(SCHEDULE_ID),
                        games = it.game.map { game ->
                            GameWithButtons(
                                game = game.toEntity(it.heading),
                                buttons = game.toButtonEntities()
                            )
                        }
                    )
                },
                filters = input.filters.map {
                    FilterWithItems(
                        filter = it.toEntity(SCHEDULE_ID),
                        items = it.items.map { item ->
                            item.toEntity(it.queryParameter)
                        }
                    )
                }
            )
        }
    }

    // Mappers from API response to entities
    private fun ScheduleResponse.toEntity(scheduleId: Long) = ScheduleEntity(
        scheduleId = scheduleId,
        team = team.toEntity(),
        defaultGameId = defaultGameId,
        yinzNode = yinzNode.toEntity()
    )

    private fun TeamResponse.toEntity() = TeamEntity(
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

    private fun GameSectionResponse.toEntity(scheduleId: Long) = GameSectionEntity(
        heading = heading,
        scheduleId = scheduleId
    )

    private fun GameResponse.toEntity(sectionHeading: String) = GameEntity(
        id = id,
        type = type,
        week = week,
        label = label,
        gameState = gameState,
        clock = clock,
        quarterShorthand = quarterShorthand,
        quarter = quarter,
        scheduleHeader = scheduleHeader,
        cardData = cardData.toEntity(),
        tv = tv,
        radio = radio,
        venue = venue,
        wlt = wlt,
        awayScore = awayScore,
        homeScore = homeScore,
        down = down,
        isHome = isHome,
        isSuperStadium = isSuperStadium,
        date = date?.toEntity(),
        opponent = opponent?.toEntity(),
        tickets = tickets?.toEntity(),
        result = result,
        home = home,
        sectionHeading = sectionHeading
    )

    private fun CardDataResponse.toEntity() = CardDataEntity(
        clickThroughURL = clickThroughURL,
        isDefault = isDefault == "True"
    )

    private fun GameDateResponse.toEntity() = GameDateEntity(
        numeric = numeric,
        text = text,
        time = time,
        timestamp = timestamp,
        isTBA = isTBA == "true",
        isTba = isTba == "true"
    )

    private fun OpponentResponse.toEntity() = OpponentEntity(
        triCode = triCode,
        fullName = fullName,
        name = name,
        city = city,
        record = record
    )

    private fun TicketsResponse.toEntity() = TicketsEntity(
        hasLink = hasLink,
        label = label,
        link = link
    )

    private fun GameResponse.toButtonEntities(): List<ButtonEntity> {
        val primaryButtons = buttons.map { button ->
            ButtonEntity(
                title = button.title,
                url = button.url,
                gameId = id,
                isSecondary = false
            )
        }
        val secondaryButtons = secondaryButtons.map { button ->
            ButtonEntity(
                title = button.title,
                url = button.url,
                gameId = id,
                isSecondary = true
            )
        }
        return primaryButtons + secondaryButtons
    }

    private fun FilterResponse.toEntity(scheduleId: Long) = FilterEntity(
        queryParameter = queryParameter,
        name = name,
        placeholder = placeholder,
        type = type,
        current = current,
        scheduleId = scheduleId
    )

    private fun FilterItemResponse.toEntity(filterQueryParameter: String) = FilterItemEntity(
        id = id,
        name = name,
        filterQueryParameter = filterQueryParameter
    )

    private fun YinzNodeResponse.toEntity() = YinzNodeEntity(
        inVenue = inVenue,
        venueStatus = venueStatus,
        carrier = carrier,
        ads = ads,
        generated = generated
    )

}