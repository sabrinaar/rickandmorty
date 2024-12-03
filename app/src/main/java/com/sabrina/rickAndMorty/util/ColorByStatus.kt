package com.sabrina.rickAndMorty.util

import androidx.compose.ui.graphics.Color


fun getColorByStatus(status: String): Color {
    return when (status) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        "Unknown" -> Color.Yellow
        else -> Color.White
    }
}