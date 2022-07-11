package ua.oshevchuk.api.api

import android.telecom.Call
import retrofit2.Response
import retrofit2.http.GET
import ua.oshevchuk.api.model.ResutModelItem

interface GameApi {
    @GET("./api/games")
    suspend fun getGamesList():Response<List<ResutModelItem>>
}