package com.example.hagmanapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color

@Composable
fun Result(navController: NavController, hasWon: Boolean, palabra: String, intentos: Int, fallos: Int, diff: String) {
    val colorRojo = Color(1f, .25f, .25f) // Rojo
    val colorVerde = Color(.25f, 1f, .25f) // Verde
    val colorAzul = Color(.25f, .25f, 1f) // Azul

    Image(
        painter = painterResource(id = R.drawable.gris),
        contentDescription = "fondo",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val hangmanArray = arrayOf(R.drawable.hangman0, R.drawable.hangman1, R.drawable.hangman2, R.drawable.hangman3, R.drawable.hangman4, R.drawable.hangman5, R.drawable.hangman6, R.drawable.hangman7, R.drawable.hangman8, R.drawable.hangman9)

        Image(
            painter = painterResource(hangmanArray[fallos]),
            contentDescription = "hangman",
            modifier = Modifier.requiredSize(200.dp)
        )

        if (hasWon) {
            Text(
                text = "¡Has ganado!",
                fontSize = 40.sp,
                fontFamily = customFontFamily1,
                color = colorVerde,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Con $intentos intentos",
                fontSize = 25.sp,
                fontFamily = customFontFamily1,
                color = colorVerde,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        } else {
            Text(
                "¡Has perdido!",
                fontSize = 40.sp,
                fontFamily = customFontFamily1,
                color = colorRojo,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                "La palabra era: ",
                fontSize = 25.sp,
                fontFamily = customFontFamily1,
                color = colorRojo,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                "${palabra.uppercase()}",
                fontSize = 65.sp,
                fontFamily = customFontFamily1,
                color = colorRojo,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        val buttonColor = ButtonDefaults.buttonColors(
            containerColor = Color(.15f, .15f, .15f).copy(alpha = 0.8f),
            //contentColor = MaterialTheme.colorScheme.surface
            contentColor = Color(.85f, .85f, .85f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 90.dp, bottom = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = { navController.navigate(Routes.Game.createRoute(diff)) },
                modifier = Modifier.requiredWidth(200.dp),
                colors = buttonColor
            ) {
                Text(text = "P L A Y   A G A I N", fontSize = 20.sp, fontFamily = customFontFamily1, fontWeight = FontWeight.Bold)
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = { navController.navigate(Routes.Menu.route) },
                modifier = Modifier.requiredWidth(200.dp),
                colors = buttonColor
            ) {
                Text(text = "M E N U", fontSize = 20.sp, fontFamily = customFontFamily1, fontWeight = FontWeight.Bold)
            }
        }
    }
}
