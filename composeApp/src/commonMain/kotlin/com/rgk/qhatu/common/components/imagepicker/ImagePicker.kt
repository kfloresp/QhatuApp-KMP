package com.rgk.qhatu.common.components.imagepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
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
fun ImagePicker(
    images: List<ImageProduct>,
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    buttonText: String,
    limitImages: Int = 5,
    onDeleteClick: (ImageProduct) -> Unit,
    onUploadClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (images.isEmpty()) {
            DottedBox(
                title = title,
                description = description,
                buttonText = buttonText,
            ) {
                onUploadClick()
            }
        } else {
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
                        items(images.size) { index ->
                            val image = images[index]
                            Box(
                                modifier = Modifier
                                    .size(150.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            ) {
                                if (image.isLoading) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.LightGray.copy(alpha = 0.4f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                } else {
                                    AsyncImage(
                                        model = image.filename,
                                        contentDescription = image.productId,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        placeholder = painterResource(resource = Res.drawable.image_place_holder),
                                        error = painterResource(resource = Res.drawable.image_place_holder)
                                    )

                                    IconButton(
                                        onClick = { onDeleteClick(image) },
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .padding(end = 8.dp, top = 8.dp)
                                            .background(
                                                color = Color.Black.copy(alpha = 0.4f),
                                                shape = CircleShape
                                            )
                                            .size(28.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(onClick = onUploadClick, enabled = images.size < limitImages) {
                        Text(text = buttonText)
                    }
                }
            }
        }
    }
}

@Composable
internal fun DottedBox(
    title: String,
    description: String,
    buttonText: String,
    buttonEnabled: Boolean = true,
    onUploadClick: () -> Unit,
) {
    val borderColor = MaterialTheme.colorScheme.onSurface
    val borderWidth = 2.dp
    val cornerRadius = 12.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .drawBehind {
                val strokeWidthPx = borderWidth.toPx()
                val cornerRadiusPx = cornerRadius.toPx()
                val pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(20f, 12f),
                    0f
                )

                drawRoundRect(
                    color = borderColor.copy(alpha = 0.7f),
                    size = size,
                    style = Stroke(
                        width = strokeWidthPx,
                        pathEffect = pathEffect
                    ),
                    cornerRadius = CornerRadius(cornerRadiusPx, cornerRadiusPx)
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onUploadClick, enabled = buttonEnabled) {
                Text(text = buttonText)
            }
        }
    }
}

@Preview()
@Composable
fun ImagePickerCardPreviewEmpty() {
    QhatuTheme {
        ImagePicker(
            images = emptyList(),
            title = "Agregar fotos del producto",
            description = "Puedes agregar hasta 5 fotos",
            buttonText = "Añadir fotos",
            onUploadClick = {},
            onDeleteClick = {},
        )
    }
}

@Preview()
@Composable
fun ImagePickerCardPreviewWithImages() {
    QhatuTheme {
        ImagePicker(
            images = listOf(
                ImageProduct(
                    productId = "P0001",
                    filename = "/data/user/0/com.rgk.ingenieros/files/P0001_0.png"
                ),
                ImageProduct(
                    productId = "P0002",
                    filename = "/data/user/0/com.rgk.ingenieros/files/P0002_0.png"
                ),
            ),
            title = "Agregar fotos del producto",
            description = "Puedes agregar hasta 5 fotos",
            buttonText = "Añadir fotos",
            onUploadClick = {},
            onDeleteClick = {}
        )
    }
}