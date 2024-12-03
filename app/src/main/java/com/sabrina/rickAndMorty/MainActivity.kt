package com.sabrina.rickAndMorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sabrina.rickAndMorty.ui.RickAndMortyApp
import dagger.hilt.android.AndroidEntryPoint


    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                RickAndMortyApp()
            }
        }
    }

