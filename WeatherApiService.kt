package com.weather.app
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(@Query("q") city: String, @Query("appid") apiKey: String, @Query("units") units: String = "metric"): WeatherResponse
}

object RetrofitInstance {
    val api: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
}
