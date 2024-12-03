package com.sabrina.rickAndMorty.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.sabrina.rickAndMorty.R
import com.sabrina.rickAndMorty.data.dto.Character


@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    item: Character,
    onItemClicked: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .padding(start = 6.dp, top = 12.dp, bottom = 12.dp)
            .fillMaxWidth()
            .clickable { onItemClicked(item.id) }

    ) {

        CharacterImageContainer(modifier = Modifier.size(128.dp)) {
            CharacterImage(item)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White
            )

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = item.species,
                    style = MaterialTheme.typography.caption,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun CharacterImage(item: Character?) {
    Box {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item?.image)
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.image_loading)
        )

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))

        )
    }
}

@Composable
fun CharacterImageContainer(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Surface(modifier.aspectRatio(1f), shape = RoundedCornerShape(12.dp)) {
        content()
    }
}
