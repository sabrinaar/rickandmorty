package com.sabrina.rickAndMorty.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sabrina.rickAndMorty.R
import com.sabrina.rickAndMorty.data.dto.Character
import com.sabrina.rickAndMorty.ui.home.components.CharacterItem
import com.sabrina.rickAndMorty.ui.home.components.FullScreenLoading
import com.sabrina.rickAndMorty.ui.home.viewmodel.MainViewModel
import com.sabrina.rickAndMorty.ui.theme.ColorPrimary
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    onItemClicked: (Int) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val eventFlow = viewModel.charactersFlow
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is MainViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeTopBar()
        },
        bottomBar = {
            HomeBottomBar(
                showPrevious = state.showPrevious,
                showNext = state.showNext,
                onPreviousPressed = { viewModel.getCharacters(false) },
                onNextPressed = { viewModel.getCharacters(true) }
            )
        }
    ) { innerPadding ->
        HomeContent(
            onItemClicked = { onItemClicked(it) },
            modifier = Modifier.padding(innerPadding),
            isLoading = state.isLoading,
            characters = state.characters
        )
    }
}

@Composable
private fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.home_title),
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = ColorPrimary
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeContent(
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    characters: List<Character> = emptyList()
) {
    if (isLoading) FullScreenLoading() else {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = ColorPrimary
        ) {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 128.dp),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(characters.size) { index ->
                    CharacterItem(
                        modifier = Modifier.fillMaxWidth(),
                        item = characters[index],
                        onItemClicked = { onItemClicked(it) })
                }

            }
        }
    }
}

@Composable
private fun HomeBottomBar(
    showPrevious: Boolean,
    showNext: Boolean,
    onPreviousPressed: () -> Unit,
    onNextPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Botón de "Anterior"
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                enabled = showPrevious,
                onClick = onPreviousPressed
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.back_button),
                    modifier = Modifier.size(24.dp)
                )
            }

            // Botón de "Siguiente"
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                enabled = showNext,
                onClick = onNextPressed
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = stringResource(id = R.string.next_button),
                    modifier = Modifier.size(24.dp) // Tamaño del ícono
                )
            }
        }
    }
}
