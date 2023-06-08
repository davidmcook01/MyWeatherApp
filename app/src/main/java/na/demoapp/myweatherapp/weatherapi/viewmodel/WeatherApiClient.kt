package na.demoapp.myweatherapp.weatherapi.viewmodel

import na.demoapp.myweatherapp.weatherapi.model.WeatherResponse
import na.demoapp.myweatherapp.weatherapi.model.WeatherService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherApiService: WeatherService = retrofit.create(WeatherService::class.java)

    fun getWeather(city: String, apiKey: String): Call<WeatherResponse> {
        return weatherApiService.getWeatherData(city, apiKey)
    }
}
