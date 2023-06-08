package na.demoapp
import android.annotation.SuppressLint
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import na.demoapp.myweatherapp.weatherapi.model.WeatherResponse
import na.demoapp.myweatherapp.weatherapi.viewmodel.WeatherApiClient
import na.demoapp.myweatherapp.weatherapi.viewmodel.WeatherViewModel
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherViewModel: WeatherViewModel
    private val weatherApiClient: WeatherApiClient = mock(Call::class.java) as WeatherApiClient

    @Before
    fun setup() {
        weatherViewModel = WeatherViewModel(weatherApiClient)
    }

    @SuppressLint("CheckResult")
    @Test
    fun fetchWeather_success_response() {
        val city = "London"
        val apiKey = "59b353c223d864fdcec4bff713f06c45"
        val call: Call<WeatherResponse> = mock(Call::class.java) as Call<WeatherResponse>
        `when`(weatherApiClient.getWeather(city, apiKey)).thenReturn(call)
        object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                // Do nothing
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Do nothing
            }
        }
    }

    @Test
    fun fetchWeather_failure_response() {
        val city = "London"
        val apiKey = "1234567890"
        val call: Call<WeatherResponse> = mock(Call::class.java) as Call<WeatherResponse>
        val response: Response<WeatherResponse> = Response.error(400, ResponseBody.create(null, "Error"))

        `when`(weatherApiClient.getWeather(city, apiKey)).thenReturn(call)
        `when`(call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                // Do nothing
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Do nothing
            }
        })).thenAnswer {
            (it.arguments[0] as Callback<WeatherResponse>).onResponse(call, response)
        }

        weatherViewModel.fetchWeather(city, apiKey)

        val captor = ArgumentCaptor.forClass(String::class.java)
        captor.run {
            assertEquals("Error fetching weather data", value)
        }
    }
}
