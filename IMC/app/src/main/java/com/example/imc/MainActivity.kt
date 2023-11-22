package com.example.imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc.ui.theme.IMCTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Body()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Body() {
    // declara variables
    var name by rememberSaveable {mutableStateOf("")}
    var year by rememberSaveable {mutableStateOf("")}
    var height by rememberSaveable {mutableStateOf("")}
    var weight by rememberSaveable {mutableStateOf("")}
    var show by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "IMC calculator", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        TextField(value = name, onValueChange = {name = it}, label = { Text("Name") })
        TextField(value = year, onValueChange = {year = it}, label = { Text("Year of birth") })
        TextField(value = height, onValueChange = {height = it}, label = { Text("Height") })
        TextField(value = weight, onValueChange = {weight = it}, label = { Text("Weight") })
        Button(onClick = {show = true}) {
            Text(text = "Calculate")
        }
        if (show) {
            val yearsOld = calculateAge(year) // llama a la funcion
            val imc = calculateIMC(height, weight) // llama a la función

            var imcValoration = ""
            when {
                imc < 18.5 -> imcValoration = "insuficient"
                imc in 18.5..24.99 -> imcValoration = "normal"
                imc in 25.0..49.99-> imcValoration = "sobrepes"
                imc >= 50 -> imcValoration = "obesitat"
            }

            Text (text = "Your age: $yearsOld", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Green)
            Text (text = "Your IMC: $imc", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Green)
            Text (text = "Your IMC valoration: $imcValoration", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Green)
        }
    }
}

// Función que calcula la edad del usuario
fun calculateAge(year: String): Int {
    val calendar = Calendar.getInstance()
    val actualYear = calendar.get(Calendar.YEAR)
    return actualYear - year.toInt()
}

// Función que calcula el IMC del usuario
fun calculateIMC(height: String, weight: String): Double {
    return Math.round(weight.toDouble() / ((height.toDouble() / 100.0) * (height.toDouble() / 100.0)) * 100.0) / 100.0
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    IMCTheme {
        Body()
    }
}