package com.example.hagmanapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hagmanapp.ui.theme.HagmanAppTheme

@Composable
fun Game(navController: NavController, selectedDifficulty: String) {
    val abcdario = remember { mutableStateListOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z") }
    val attempts = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Text(text = selectedDifficulty)

        if (selectedDifficulty.equals("Easy")) {
            Text(
                text = "_ _ _",
                fontSize = 80.sp,
                modifier = Modifier.padding(bottom = 30.dp)
            )
        } else if(selectedDifficulty.equals("Medium")) {
            Text(
                text = "_ _ _ _",
                fontSize = 80.sp,
                modifier = Modifier.padding(bottom = 30.dp)
            )
        } else {
            Text(
                text = "_ _ _ _ _",
                fontSize = 80.sp,
                modifier = Modifier.padding(bottom = 30.dp)
            )
        }



        Image(
            painter = painterResource(id = R.drawable.persona),
            contentDescription = "persona",
            modifier = Modifier.requiredSize(200.dp)
        )

        var index = -1
        for (i in 1..5) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (i < 5) {
                    for (j in 1..6) {
                        index++
                        OutlinedButton(
                            onClick = { attempts.value++ },
                            modifier = Modifier.size(55.dp)
                        ) {
                            Text(text = abcdario[index], fontSize = 15.sp)
                        }
                    }
                } else {
                    for (j in 1..3) {
                        index++
                        OutlinedButton(
                            onClick = { navController.navigate(Routes.Result.route) },
                            modifier = Modifier.size(55.dp)
                        ) {
                            Text(text = abcdario[index], fontSize = 15.sp)
                        }
                    }
                }
            }
        }

        Text(
            text = "ATTEMPTS: ${attempts.value}",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 15.dp)
        )

    }
}





