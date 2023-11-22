package com.example.propina

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
import com.example.propina.ui.theme.PropinaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PropinaTheme {
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
    var basePrice by rememberSaveable {mutableStateOf("")}
    var tip by rememberSaveable {mutableStateOf("")}
    var show by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "Tip calculator", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        TextField(value = basePrice, onValueChange = {basePrice = it}, label = { Text("Base price") })
        TextField(value = tip, onValueChange = {tip = it}, label = { Text("Tip percentage") })
        Button(onClick = {show = true}) {
            Text(text = "Calculate")
        }
        if (show) {
            val finalPrice = calculateFinalPrice(basePrice, tip) // llama a la función
            Text (text = "Final price: $$finalPrice", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Green)
        }
    }
}

// Función que calcula el precio final
fun calculateFinalPrice(basePrice: String, tip: String): Double {
    val basePriceDouble = basePrice.toDouble()
    val tipDouble = tip.toDouble()
    val result = basePriceDouble + basePriceDouble * tipDouble / 100
    return result
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    PropinaTheme {
        Body()
    }
}