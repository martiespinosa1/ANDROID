package com.example.hagmanapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.Random

@Composable
fun Game(navController: NavController, selectedDifficulty: String) {
    val abcdario = remember {
        listOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N",
            "Ñ",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z"
        )
    }

    val palabras3Letras = remember {
        listOf(
            "sol",
            "pan",
            "luz",
            "mar",
            "sur",
            "uva",
            "rio",
            "png",
            "zip",
            "pie"
        )
    }
    val palabras4Letras = remember {
        listOf(
            "casa",
            "auto",
            "nube",
            "pino",
            "vino",
            "moto",
            "azul",
            "rojo",
            "flor",
            "piso"
        )
    }
    val palabras5Letras = remember {
        listOf(
            "valor",
            "libro",
            "cielo",
            "perro",
            "tigre",
            "pilar",
            "silla",
            "yegua",
            "nieve",
            "casco"
        )
    }

    val randomWord = remember { mutableStateOf("") }
    val palabraOculta = remember { mutableStateOf(generarPalabraOculta(selectedDifficulty)) }
    val random = Random()
    val attempts = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val randomIndex: Int
        val randomWordInitialized = remember { mutableStateOf(false) }

        if (!randomWordInitialized.value) {
            when (selectedDifficulty) {
                "Easy" -> {
                    randomIndex = random.nextInt(palabras3Letras.size)
                    randomWord.value = palabras3Letras[randomIndex]
                }
                "Medium" -> {
                    randomIndex = random.nextInt(palabras4Letras.size)
                    randomWord.value = palabras4Letras[randomIndex]
                }
                "Hard" -> {
                    randomIndex = random.nextInt(palabras5Letras.size)
                    randomWord.value = palabras5Letras[randomIndex]
                }
            }
            randomWordInitialized.value = true
        }

        Text(
            text = palabraOculta.value,
            fontSize = 80.sp,
            modifier = Modifier.padding(bottom = 30.dp)
        )


        Image(
            painter = painterResource(id = R.drawable.persona),
            contentDescription = "persona",
            modifier = Modifier.requiredSize(200.dp)
        )

        // CREACION DE LOS BOTONES (TECLAS)
        var index = -1
        for (i in 1..26) {
            if (i % 6 == 1) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (j in 1..6) {
                        index++
                        if (index < abcdario.size) {
                            val letra = abcdario[index]
                            OutlinedButton(
                                onClick = {
                                    attempts.value++
                                    val letraEnPalabra = LetraEnPalabra(randomWord.value, letra)
                                    if (letraEnPalabra) {
                                        palabraOculta.value = ponerLetras(randomWord.value, palabraOculta.value, letra)
                                    }
                                },
                                modifier = Modifier.size(55.dp)
                            ) {
                                Text(text = letra, fontSize = 15.sp)
                            }
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


fun generarPalabraOculta(selectedDifficulty: String): String {
    return when (selectedDifficulty) {
        "Easy" -> "_ _ _"
        "Medium" -> "_ _ _ _"
        "Hard" -> "_ _ _ _ _"
        else -> ""
    }
}

fun LetraEnPalabra(palabraRandom: String, letra: String): Boolean {
    println(palabraRandom)
    println(letra)
    println(palabraRandom.contains(letra.lowercase()))
    return palabraRandom.contains(letra.lowercase())
}

fun ponerLetras(palabra: String, palabraOculta: String, letra: String): String {
    val nuevaPalabraOculta = StringBuilder(palabraOculta)
    for (i in palabra.indices) {
        if (palabra[i].uppercaseChar() == letra[0]) {
            nuevaPalabraOculta.setCharAt(i * 2, letra[0])
        }
    }
    println("palabra: $palabra")
    println("letra: $letra")
    println("palabraOculta actualizada: ${nuevaPalabraOculta}")
    return nuevaPalabraOculta.toString()
}



