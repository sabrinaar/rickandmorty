package com.sabrina.rickAndMorty.ui.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabrina.rickAndMorty.domain.RepoCharacters
import com.sabrina.rickAndMorty.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private var repoCharacters: RepoCharacters,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailState(isLoading = true))
    val uiState: StateFlow<DetailState> = _uiState.asStateFlow()

    fun getCharacter(id: Int) {
        viewModelScope.launch {
        repoCharacters.getCharacter(id).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        _uiState.value = DetailState(character = result.data, isLoading = false)

                    }
                    is Resource.Error -> {
                        _uiState.value = DetailState(character = result.data, isLoading = false)
                    }

                    is Resource.Loading -> {
                        _uiState.value = DetailState(isLoading = true)
                    }
                }
            }.launchIn(this)
        }
    }
}

