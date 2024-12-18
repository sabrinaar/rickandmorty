package com.sabrina.rickAndMorty.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sabrina.rickAndMorty.ui.theme.ColorPrimary

@Composable
fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPrimary)
    ) {
        CircularProgressIndicator(color = Color.White, modifier = Modifier.align(Alignment.Center))
    }
}