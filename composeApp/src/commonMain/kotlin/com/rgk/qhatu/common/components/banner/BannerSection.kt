package com.rgk.qhatu.common.components.banner

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.image_place_holder

@Composable
fun BannerSection(
    path: Any?,
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16,
    onBannerClick: (Int) -> Unit = {},
) {
    AsyncImage(
        model = path,
        contentDescription = "Banner",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(cornerRadius.dp))
            .clickable { onBannerClick(0) },
        placeholder = painterResource(resource = Res.drawable.image_place_holder),
        error = painterResource(resource = Res.drawable.image_place_holder)
    )
}