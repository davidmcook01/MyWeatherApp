package na.demoapp.myweatherapp.weatherapi.view

import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import na.demoapp.myweatherapp.R
import na.demoapp.myweatherapp.weatherapi.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var cityEditText: EditText
    private lateinit var fetchWeatherButton: Button
    private lateinit var weatherTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var geocoder: Geocoder

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityEditText = findViewById(R.id.cityEditText)
        fetchWeatherButton = findViewById(R.id.WeatherButton)
        weatherTextView = findViewById(R.id.weatherTextView)
        cityTextView = findViewById(R.id.cityTextView)

        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        geocoder = Geocoder(this)

        fetchWeatherButton.setOnClickListener {
            val city = cityEditText.text.toString().trim()
            val apiKey = "59b353c223d864fdcec4bff713f06c45"

            if (city.isNotEmpty()) {
                val addresses = geocoder.getFromLocationName(city, 1)
                if (!addresses.isNullOrEmpty()) {
                    for(i in addresses){
                    weatherViewModel.fetchWeather(i.locality.toString(), apiKey) }
                }
            }
        }

        weatherViewModel.weatherData.observe(this) { weatherResponse ->
            weatherResponse?.let {
                weatherTextView.text = "Temperature: ${it.currentWeather.temperature}\n Weather: ${it.currentWeather.weatherConditions} ${it.weather.icon}"
                cityTextView.text = "City: ${it.city}"
            }
        }
    }
}