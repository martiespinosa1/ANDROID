package com.example.hagmanapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(navController: NavController) {
    val colorGrisOscuro = Color(.15f, .15f, .15f) // Gris oscuro
    val colorGrisClaro = Color(.85f, .85f, .85f) // Gris claro

    var expanded by remember { mutableStateOf(false) }
    val difficulty = listOf("F Á C I L", "M E D I O", "D I F Í C I L")
    var selectedDifficulty by remember { mutableStateOf(difficulty[0]) }

    var showDialog by remember { mutableStateOf(false) }

    Image(
        painter = painterResource(id = R.drawable.gris),
        contentDescription = "fondo",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.hangman9),
                contentDescription = "logo",
                modifier = Modifier
                    .requiredSize(200.dp)
                    .clip(shape = RoundedCornerShape(30.dp))
            )
        }

        val buttonColor = ButtonDefaults.buttonColors(
            containerColor = Color(.15f, 0.15f, 0.15f), // Gris oscuro
            //contentColor = MaterialTheme.colorScheme.surface
            contentColor = Color(.85f, 0.85f, 0.85f) // Gris claro
        )



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = "M O D O:  $selectedDifficulty",
                textStyle = TextStyle(fontFamily = customFontFamily1, fontSize = 20.sp, color = Color.LightGray, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
                onValueChange = { selectedDifficulty = it },
                enabled = false,
                readOnly = true,
                modifier = Modifier
                    .clickable { expanded = true }
                    .background(colorGrisOscuro.copy(alpha = 0.8f), shape = RoundedCornerShape(50.dp))
                    .requiredWidth(220.dp)
                    .requiredHeight(60.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(16.dp))
            ) {
                difficulty.forEach {
                    DropdownMenuItem(text = { Text(text = it, fontFamily = customFontFamily1)},
                        onClick = {
                            expanded = false
                            selectedDifficulty = it
                        })
                }
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
                onClick = { navController.navigate(Routes.Game.createRoute(selectedDifficulty)) },
                modifier = Modifier.requiredWidth(200.dp),
                colors = buttonColor
            ){
                Text(text = "P L A Y", fontSize = 20.sp, fontFamily = customFontFamily1, fontWeight = FontWeight.Bold)
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
                onClick = { showDialog = true },
                modifier = Modifier.requiredWidth(200.dp),
                colors = buttonColor
            ){
                Text(text = "H E L P", fontSize = 20.sp, fontFamily = customFontFamily1, fontWeight = FontWeight.Bold)
            }
            MyDialog(showDialog, { showDialog = false }) { showDialog = false}

        }
    }
}


@Composable
fun MyDialog(show: Boolean, onDismiss: () -> Unit, function: () -> Unit) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false
            )
        ) {
            Column(
                Modifier
                    .background(Color.DarkGray.copy(alpha = 0.8f))
                    .padding(24.dp)
            ) {
                Text(text = "Selecciona la dificultad y dale a play", color = Color.LightGray)
            }
        }
    }
}






