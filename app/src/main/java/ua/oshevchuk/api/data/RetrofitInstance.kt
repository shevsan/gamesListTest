package ua.oshevchuk.api.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ua.oshevchuk.api.api.GameApi

object RetrofitInstance {

    val api:GameApi by lazy {
        Retrofit.Builder().
        baseUrl("https://www.freetogame.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GameApi::class.java)
    }

}