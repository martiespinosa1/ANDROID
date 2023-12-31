package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
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

@Composable
fun Body() {
    var show by rememberSaveable { mutableStateOf(false) }

    Image(
        painter = painterResource(id = R.drawable.tapestry),
        contentDescription = "fondo",
        contentScale = ContentScale.FillBounds
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(
            painter = painterResource(id = R.drawable.title),
            contentDescription = "titulo",
            modifier = Modifier
                .padding(top = 50.dp)
                .size(width = 200.dp, height = 200.dp)
        )

        Button(
            onClick = { show = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .padding(top = 100.dp)
                .size(width = 350.dp, height = 50.dp)
        ) {
            Text(
                text = "ROLL THE DICE",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

        }

        if (show) {
            val diceArray = arrayOf(R.drawable.dice_1, R.drawable.dice_2, R.drawable.dice_3, R.drawable.dice_4, R.drawable.dice_5, R.drawable.dice_6)

            val random = Random()

            val randomIndex1 = random.nextInt(diceArray.size)
            val randomImage1 = diceArray[randomIndex1]

            val randomIndex2 = random.nextInt(diceArray.size)
            val randomImage2 = diceArray[randomIndex2]

            R.drawable.dice_1 = randomImage1
            R.drawable.dice_2 = randomImage2

            show = false
        }

        Row () {
            Image(
                painter = painterResource(id = R.drawable.dice_1),
                contentDescription = "dado1",
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 75.dp)
                    .clickable { }
            )

            Image(
                painter = painterResource(id = R.drawable.dice_2),
                contentDescription = "dado2",
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 75.dp)
                    .clickable { }
            )
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    DiceRollerTheme {
        Body()
    }
}