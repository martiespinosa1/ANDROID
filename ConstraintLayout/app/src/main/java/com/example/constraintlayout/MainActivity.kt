package com.example.constraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.constraintlayout.ui.theme.ConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayoutTheme {
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    )

    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {

        ConstraintLayout {
            val (boxRed, boxBlue, logo) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.android_logo),
                contentDescription = "android logo",
                modifier = Modifier
                    .size(100.dp)
                    .constrainAs(logo) {}
            )

            Box(modifier = Modifier
                .size(250.dp, 20.dp)
                .background(Color.Red)
                .constrainAs(boxRed) { start.linkTo(logo.end) }) { Text(text = "Hello world!") }
            Box(modifier = Modifier
                .size(250.dp, 50.dp)
                .background(Color.Blue)
                .constrainAs(boxBlue) {
                    start.linkTo(logo.end)
                    top.linkTo(boxRed.bottom)
                }
            )
        }

        ConstraintLayout {
            Box(modifier = Modifier
                .size(250.dp, 100.dp)
                .background(Color.Green))
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ConstraintLayoutTheme {
        Body()
    }
}