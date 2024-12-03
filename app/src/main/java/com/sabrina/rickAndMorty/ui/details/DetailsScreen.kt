package com.sabrina.rickAndMorty.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sabrina.rickAndMorty.ui.details.components.CharacterDescriptionComponent
import com.sabrina.rickAndMorty.ui.details.components.CharacterNameComponent
import com.sabrina.rickAndMorty.ui.details.components.CharacterStatusComponent
import com.sabrina.rickAndMorty.ui.details.viewmodel.DetailState
import com.sabrina.rickAndMorty.ui.details.viewmodel.DetailsViewModel
import com.sabrina.rickAndMorty.ui.home.components.CharacterImage
import com.sabrina.rickAndMorty.ui.home.components.FullScreenLoading
import com.sabrina.rickAndMorty.ui.theme.ColorPrimary
import com.sabrina.rickAndMorty.util.getColorByStatus

@Composable
fun DetailsScreen(
    idCharacter: Int,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    viewModel.getCharacter(idCharacter)
    val detailUiState by viewModel.uiState.collectAsState()
    Content(detailUiState)
}


@Composable
fun Content(detailState: DetailState) {
    if (detailState.isLoading) {
        FullScreenLoading()
    } else {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorPrimary),
            contentPadding = PaddingValues(all = 16.dp)
        ) {
            item {
                CharacterNameComponent(detailState.character?.name ?: "")
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                CharacterImage(detailState.character)
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            item {
                val status = detailState.character?.status ?: ""
                CharacterStatusComponent(characterStatus = status, getColorByStatus(status))
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }


            item {
                CharacterDescriptionComponent("Specie", detailState.character?.species)
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }

            item {
                CharacterDescriptionComponent("Gender", detailState.character?.gender)
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }

            item {
                CharacterDescriptionComponent("Origin", detailState.character?.origin?.name)
            }
        }
    }
}

