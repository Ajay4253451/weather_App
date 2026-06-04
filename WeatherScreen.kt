package com.weather.app
import androidx.compose.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var city by remember { mutableStateOf("") }
    
    val apiKey = "bac150da34cf85e097b132b302b2f838" 

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(value = city, onValueChange = { city = it }, label = { Text("Enter City") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.fetchWeather(city, apiKey) }, modifier = Modifier.fillMaxWidth()) {
            Text("Get Weather")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (viewModel.isLoading.value) { CircularProgressIndicator() }
        viewModel.weatherData.value?.let { 
            Text("City: ${it.name}", style = MaterialTheme.typography.headlineMedium)
            Text("Temp: ${it.main.temp}°C", style = MaterialTheme.typography.bodyLarge)
            Text("Humidity: ${it.main.humidity}%", style = MaterialTheme.typography.bodyLarge)
        }
        if (viewModel.errorMessage.value.isNotEmpty()) { Text("Error: ${viewModel.errorMessage.value}", color = MaterialTheme.colorScheme.error) }
    }
}
