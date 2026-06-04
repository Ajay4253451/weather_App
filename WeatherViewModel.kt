package com.weather.app
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    val weatherData = mutableStateOf<WeatherResponse?>(null)
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf("")

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = RetrofitInstance.api.getWeather(city, apiKey)
                weatherData.value = response
                errorMessage.value = ""
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage ?: "Error Occurred"
            } finally {
                isLoading.value = false
            }
        }
    }
}
