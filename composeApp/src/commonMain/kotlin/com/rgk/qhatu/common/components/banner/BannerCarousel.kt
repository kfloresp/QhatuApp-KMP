package com.rgk.qhatu.common.components.banner

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.image_place_holder

@Composable
fun BannerCarousel(
    banners: List<String>,
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16,
    onBannerClick: (Int) -> Unit = {},
) {
    if (banners.isEmpty()) return

    if (banners.size == 1) {
        AsyncImage(
            model = banners.first(),
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
    } else {
        val pagerState = rememberPagerState { banners.size }
        val scope = rememberCoroutineScope()

        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                pageSpacing = 12.dp,
            ) { page ->
                AsyncImage(
                    model = banners.first(),
                    contentDescription = "Banner",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(cornerRadius.dp))
                        .clickable { onBannerClick(page) },
                    placeholder = painterResource(resource = Res.drawable.image_place_holder),
                    error = painterResource(resource = Res.drawable.image_place_holder)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(banners.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (isSelected) 10.dp else 8.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                            )
                            .clickable {
                                scope.launch { pagerState.scrollToPage(index) }
                            }
                    )
                }
            }
        }
    }
}
