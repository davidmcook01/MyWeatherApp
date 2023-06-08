package na.demoapp.myweatherapp.weatherapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import na.demoapp.myweatherapp.weatherapi.model.WeatherResponse
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(private val weatherApiClient: WeatherApiClient) : ViewModel() {
    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> = _weatherData

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    constructor() : this(WeatherApiClient())

    fun fetchWeather(city: String, apiKey: String) {
        weatherApiClient.getWeather(city, apiKey).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    _weatherData.postValue(weatherResponse)
                } else {
                    _errorMessage.postValue("Error fetching weather data")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                _errorMessage.postValue("Error fetching weather data")
            }
        })
    }
}
