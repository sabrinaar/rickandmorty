package com.sabrina.rickAndMorty.ui.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabrina.rickAndMorty.domain.RepoCharacters
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.sabrina.rickAndMorty.vo.Resource
import kotlinx.coroutines.flow.launchIn
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (private var repoCharacters: RepoCharacters) : ViewModel() {

    var state by mutableStateOf(HomeState(isLoading = true))
        private set

    private val _charactersFlow = MutableSharedFlow<UIEvent>()
    val charactersFlow = _charactersFlow.asSharedFlow()

    private var currentPage = 1

    init {
        getCharacters(increment = false)
    }

    fun getCharacters(increment: Boolean) {
        viewModelScope.launch {
            if (increment) currentPage++ else if (currentPage > 1) currentPage--
            val showPrev = currentPage > 1
            val showNext = currentPage < 42
            repoCharacters.getCharacters(currentPage).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state = state.copy(
                            characters = result.data?.list ?: emptyList(),
                            isLoading = false,
                            showPrevious = showPrev,
                            showNext = showNext
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                        _charactersFlow.emit(
                            UIEvent.ShowSnackBar(
                                result.message ?: "Se ha producido un error"
                            )
                        )
                    }

                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}
