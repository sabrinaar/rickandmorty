package com.sabrina.rickAndMorty.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.sabrina.rickAndMorty.ui.theme.RickAndMortyTheme


@Composable
fun RickAndMortyApp() {

    RickAndMortyTheme {
        val navController = rememberNavController()

        RickAndMortyNavGraph(
            navController = navController
        )
    }



}