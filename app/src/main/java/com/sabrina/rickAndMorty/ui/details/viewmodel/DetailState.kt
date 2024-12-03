package com.sabrina.rickAndMorty.ui.details.viewmodel
import com.sabrina.rickAndMorty.data.dto.Character

data class DetailState(
    val character: Character? = null,
    val isLoading: Boolean = false
)