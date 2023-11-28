package com.example.calculadora

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraTheme {
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
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var show by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "Calculadora\n", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        TextField(value = num1, onValueChange = { num1 = it }, label = { Text("Num 1") })
        TextField(value = num2, onValueChange = { num2 = it }, label = { Text("Num 2") })

        Text(text = "", fontSize = 20.sp)
        val op = myCalculator()
        Text(text = "", fontSize = 20.sp)

        Button(onClick = { show = true }) {
            Text(text = "Calcular", fontSize = 18.sp)
        }

        if (show) {
            var result = 0
            if (op == "División" && num2 == "0") Toast.makeText(LocalContext.current, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show()
            else {
                when (op) {
                    "Suma" -> result = num1.toInt() + num2.toInt()
                    "Resta" -> result = num1.toInt() - num2.toInt()
                    "Multiplicación" -> result = num1.toInt() * num2.toInt()
                    "División" -> result = num1.toInt() / num2.toInt()
                }

                Text (text = "\nResultado: $result", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Green)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myCalculator(): String {
    var operacion by remember { mutableStateOf("Suma") }
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Suma", "Resta", "Multiplicación", "División")

    OutlinedTextField(
        value = operacion,
        onValueChange = { operacion = it },
        enabled = false,
        readOnly = true,
        modifier = Modifier
            .clickable { expanded = true }
            .width(280.dp)
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        options.forEach {
            DropdownMenuItem(text = { Text(text = it) }, onClick = {
                expanded = false
                operacion = it
            })
        }
    }
    return operacion
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    CalculadoraTheme {
        Body()
    }
}