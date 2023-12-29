package com.example.hagmanapp


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hagmanapp.ui.theme.HagmanAppTheme

@Composable
fun Result(navController: NavController, hasWon: Boolean, palabra: String, fallos: Int, diff: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (hasWon) {
            Text(
                text = "¡Has ganado!",
                fontSize = 24.sp,
                color = Color.Green,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Con $fallos intentos",
                fontSize = 18.sp,
                color = Color.Green,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        } else {
            Text(
                "¡Has perdido!",
                fontSize = 24.sp,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                "La palabra era: ${palabra.uppercase()}",
                fontSize = 18.sp,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 90.dp, bottom = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = { navController.navigate(Routes.Game.createRoute(diff)) },
                modifier = Modifier.requiredWidth(280.dp)
            ) {
                Text(text = "Play again", fontSize = 20.sp)
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
                modifier = Modifier.requiredWidth(280.dp)
            ) {
                Text(text = "Menu", fontSize = 20.sp)
            }
        }
    }
}
