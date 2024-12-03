package com.sabrina.rickAndMorty.ui.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.sabrina.rickAndMorty.ui.theme.ColorTextPrimary

@Composable
fun CharacterDescriptionComponent(title: String, subtitle: String?) {
    Column() {
        Text(
            text = title,
            color = ColorTextPrimary,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
        )
        subtitle?.let {
            Text(
                text = it,
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}
