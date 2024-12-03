package com.sabrina.rickAndMorty.ui.home.viewmodel

import com.sabrina.rickAndMorty.data.dto.Character


data class HomeState(
    val characters: List<Character> = emptyList(),
    val showPrevious: Boolean = false,
    val showNext: Boolean = false,
    val isLoading: Boolean = false
)
