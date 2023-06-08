package na.demoapp.myweatherapp.weatherapi.model


import com.google.gson.annotations.SerializedName
import java.util.TimeZone

data class WeatherResponse(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: List<Weather>,
    @SerializedName("timezone") val timezone: List<TimeZone>,
    @SerializedName("timezone_offset") val timezoneOffset: Int,
    @SerializedName("city") val city: String,
    @SerializedName("weather") val weather: Weather,
    @SerializedName("current") val currentWeather: CurrentWeather,
    @SerializedName("minutely") val minutelyForecast: MinutelyForecast,
    @SerializedName("hourly") val hourlyForecast: HourlyForecast,
    @SerializedName("daily") val dailyForecast: DailyForecast,
    @SerializedName("alerts") val alerts: Alert
)

data class CurrentWeather(
    @SerializedName("dt") val dateTime: Long,
    @SerializedName("sunrise") val sunriseTime: Long,
    @SerializedName("sunset") val sunsetTime: Long,
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val feelsLikeTemperature: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("dew_point") val dewPoint: Double,
    @SerializedName("uvi") val uvIndex: Double,
    @SerializedName("clouds") val cloudiness: Int,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_deg") val windDegree: Int,
    @SerializedName("wind_gust") val windGust: Double,
    @SerializedName("weather") val weatherConditions: List<Weather>
)

data class MinutelyForecast(
    @SerializedName("dt") val dateTime: Long,
    @SerializedName("precipitation") val precipitation: Double
)

data class HourlyForecast(
    @SerializedName("dt") val dateTime: Long,
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val feelsLikeTemperature: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("dew_point") val dewPoint: Double,
    @SerializedName("uvi") val uvIndex: Double,
    @SerializedName("clouds") val cloudiness: Int,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_deg") val windDegree: Int,
    @SerializedName("wind_gust") val windGust: Double,
    @SerializedName("weather") val weatherConditions: List<Weather>,
    @SerializedName("pop") val precipitationProbability: Double
)

data class DailyForecast(
    @SerializedName("dt") val dateTime: Long,
    @SerializedName("sunrise") val sunriseTime: Long,
    @SerializedName("sunset") val sunsetTime: Long,
    @SerializedName("moonrise") val moonriseTime: Long,
    @SerializedName("moonset") val moonsetTime: Long,
    @SerializedName("moon_phase") val moonPhase: Double,
    @SerializedName("summary") val summary: String,
    @SerializedName("temp") val temperature: Temperature,
    @SerializedName("feels_like") val feelsLikeTemperature: FeelsLikeTemperature,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("dew_point") val dewPoint: Double,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_deg") val windDegree: Int,
    @SerializedName("wind_gust") val windGust: Double,
    @SerializedName("weather") val weatherConditions: List<Weather>,
    @SerializedName("pop") val precipitationProbability: Double
)

data class Temperature(
    @SerializedName("day") val day: Double,
    @SerializedName("min") val min: Double,
    @SerializedName("max") val max: Double,
    @SerializedName("night") val night: Double,
    @SerializedName("eve") val evening: Double,
    @SerializedName("morn") val morning: Double
)

data class FeelsLikeTemperature(
    @SerializedName("day") val day: Double,
    @SerializedName("night") val night: Double,
    @SerializedName("eve") val evening: Double,
    @SerializedName("morn") val morning: Double
)

data class Weather(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class Alert(
    @SerializedName("sender_name") val senderName: String,
    @SerializedName("event") val event: String,
    @SerializedName("start") val startTime: Long,
    @SerializedName("end") val endTime: Long,
    @SerializedName("description") val description: String,
    @SerializedName("tags") val tags: List<String>
)

