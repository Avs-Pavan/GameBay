package com.pavan.gamebay.feature.gameschedule.data.remote.api

import com.pavan.gamebay.feature.gameschedule.data.remote.model.ScheduleResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * GameScheduleAPI is a Retrofit interface for fetching the game schedule.
 */
fun interface GameScheduleAPI {
    /**
     * Fetches the game schedule from the remote server.
     *
     * @return Response containing the ScheduleResponse.
     */
    @GET("iOS/interviews/ScheduleExercise/schedule.json")
    suspend fun getGameSchedule(): Response<ScheduleResponse>
}