package com.example.cps_pong

import android.graphics.Canvas
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    private lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        linearLayout = findViewById(R.id.idRLView)
        val customView = MyCustomView(this)
        linearLayout.addView(customView)
//        setContent {
//            CPS_PongTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    Canvas()
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CPS_PongTheme {
//        Greeting("Android")
//    }
//}
