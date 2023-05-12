package com.example.cps_pong

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        linearLayout = findViewById(R.id.idRLView)
        val pongSurface = PongSurface(this,this);
        linearLayout.addView(pongSurface)
    }
}

