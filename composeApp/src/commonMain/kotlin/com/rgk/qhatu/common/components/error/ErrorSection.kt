package com.rgk.qhatu.common.components.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rgk.qhatu.common.components.button.CustomButton
import com.rgk.qhatu.common.components.lottie.LottieAnimation
import com.rgk.qhatu.common.components.lottie.LottieResource
import com.rgk.qhatu.common.theme.QhatuTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_confirm_new
import qhatuapp.composeapp.generated.resources.tx_global_error_result

@Composable
fun ErrorSection(
    messageError: String,
    onClick: (() -> Unit)? = null,
    buttonPrimary: StringResource? = null,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LottieResource(
            LottieAnimation.ERROR_CAT,
            modifier = Modifier.width(400.dp).height(400.dp)
        )
        Text(
            text = stringResource(Res.string.tx_global_error_result, messageError),
            fontSize = 18.sp,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        if (onClick != null && buttonPrimary != null) {
            CustomButton(text = stringResource(buttonPrimary),
                onClick = { onClick.invoke() }, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
        }
    }
}

@Preview
@Composable
fun ErrorSectionPreview() {
    QhatuTheme {
        Column(modifier = Modifier.background(Color.White)) {
            ErrorSection("Error de prueba", onClick = {}, buttonPrimary = Res.string.tx_global_confirm_new)
        }
    }
}