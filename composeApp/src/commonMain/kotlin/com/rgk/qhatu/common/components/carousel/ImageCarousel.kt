package com.rgk.qhatu.common.components.carousel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rgk.qhatu.common.theme.QhatuTheme
import com.rgk.qhatu.feature.product.domain.model.ImageProduct
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.image_place_holder


@Composable
fun ImageCarousel(
    imagesFromUrl: List<ImageProduct>? = null,
) {
    if (imagesFromUrl.isNullOrEmpty()) return
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                        items(imagesFromUrl.size) { index ->
                            AsyncImage(
                                model = imagesFromUrl[index].filename,
                                contentDescription = imagesFromUrl[index].productId,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxWidth()
                                    .height(300.dp)
                                    .padding(4.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                placeholder = painterResource(resource = Res.drawable.image_place_holder),
                                error = painterResource(resource = Res.drawable.image_place_holder)
                            )
                        }
                }
            }
        }
    }
}


@Preview()
@Composable
fun ImagePickerCardPreviewEmpty() {
    QhatuTheme {
        ImageCarousel(
            imagesFromUrl = emptyList(),
        )
    }
}
