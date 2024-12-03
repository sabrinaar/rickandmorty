package com.sabrina.rickAndMorty.ui.details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sabrina.rickAndMorty.ui.theme.RickAndMortyTheme
import com.sabrina.rickAndMorty.util.getColorByStatus

@Composable
fun CharacterStatusComponent(characterStatus: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = color,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Status: $characterStatus",
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun CharacterStatusComponentPreviewAlive() {
    RickAndMortyTheme {
        CharacterStatusComponent(characterStatus = "Alive", getColorByStatus("Alive"))
    }
}