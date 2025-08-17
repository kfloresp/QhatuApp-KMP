package com.rgk.qhatu.common.components.imagepicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.theme.QhatuTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ImagePicker(
    images: List<ImageBitmap>,
    modifier: Modifier = Modifier,
    onUploadClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (images.isEmpty()) {
            DottedBox {
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
                            Image(
                                painter = BitmapPainter(images[index]),
                                contentDescription = "Imagen $index",
                                modifier = Modifier
                                    .size(150.dp)
                                    .background(Color.Gray, RoundedCornerShape(8.dp))
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(onClick = onUploadClick) {
                        Text(text = "Añadir fotos")
                    }
                }
            }
        }
    }
}

@Composable
internal fun DottedBox(onUploadClick: () -> Unit) {
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
            Text(text = "Agregar fotos", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "Muestra tu producto desde diferentes ángulos.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onUploadClick) {
                Text(text = "Añadir fotos")
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
            onUploadClick = {}
        )
    }
}

@Preview()
@Composable
fun ImagePickerCardPreviewWithImages() {
    QhatuTheme {
        ImagePicker(
            images = listOf(
            ),
            onUploadClick = {}
        )
    }
}