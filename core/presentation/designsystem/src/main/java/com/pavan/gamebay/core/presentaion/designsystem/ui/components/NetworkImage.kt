package com.pavan.gamebay.core.presentaion.designsystem.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.imageLoader
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.pavan.gamebay.core.presentaion.designsystem.R
import com.pavan.gamebay.core.presentaion.designsystem.ui.theme.BackgroundColor

@Composable
fun NetworkImage(
    url: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    alignment: Alignment = Alignment.Center,
    placeHolderPainter: Painter? = null,
    errorPainter: Painter? = null
) {
    if (LocalInspectionMode.current) {
        placeHolderPainter?.let {
            Image(
                painter = it,
                contentDescription = contentDescription,
                modifier = modifier,
                contentScale = contentScale,
                alignment = alignment
            )
        }
    } else {
        if (url.isEmpty())
            return

        AsyncImage(
            imageLoader = LocalContext.current.imageLoader.newBuilder()
                .logger(DebugLogger())
                .build(),
            model = ImageRequest.Builder(LocalContext.current)

                .data(url)
                .memoryCacheKey(url)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCacheKey(url)
                .diskCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale,
            alignment = alignment,
            placeholder = placeHolderPainter,
            fallback = placeHolderPainter,
            error = errorPainter
        )
    }
}


@Composable
@Preview
fun NetworkImagePreview() {
    NetworkImage(
        url = "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_phi_light.png",
        contentDescription = "Google Logo",
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .size(100.dp)
            .background(BackgroundColor),
        placeHolderPainter = painterResource(R.drawable.baseline_sports_football_24)
    )
}