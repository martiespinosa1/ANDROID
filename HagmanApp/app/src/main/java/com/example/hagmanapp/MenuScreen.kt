package com.example.hagmanapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun Menu(navController: NavController) {
    var firstOptionSelected by remember { mutableStateOf(false) }
    var secondOptionSelected by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.08f)
                .align(Alignment.TopCenter)
                .background(Color.DarkGray)) {
            Text(
                text = "Hangman App",
                modifier = Modifier
                    .padding(start = 15.dp, top = 16.dp),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo")
            }

        }



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.08f)
                .align(Alignment.BottomCenter)
                .clickable { navController.navigate(Routes.Game.route) }
                .background(Color.DarkGray)) {
            Text(
                text = "NEXT STEP",
                modifier = Modifier
                    .align(Alignment.Center),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    HagmanAppTheme {
        Menu(navController = rememberNavController())
    }
}
