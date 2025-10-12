package com.rgk.qhatu.common.components.imagepicker

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.common.components.dialog.ContentDialog
import com.rgk.qhatu.shared.PermissionCallback
import com.rgk.qhatu.shared.PermissionStatus
import com.rgk.qhatu.shared.PermissionType
import com.rgk.qhatu.shared.SharedImage
import com.rgk.qhatu.shared.createPermissionsManager
import com.rgk.qhatu.shared.rememberCameraManager
import com.rgk.qhatu.shared.rememberGalleryManager
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_image_picker_add_photos
import qhatuapp.composeapp.generated.resources.tx_image_picker_camera
import qhatuapp.composeapp.generated.resources.tx_image_picker_cancel
import qhatuapp.composeapp.generated.resources.tx_image_picker_choose_option
import qhatuapp.composeapp.generated.resources.tx_image_picker_gallery
import qhatuapp.composeapp.generated.resources.tx_image_picker_permission_description
import qhatuapp.composeapp.generated.resources.tx_image_picker_permission_required
import qhatuapp.composeapp.generated.resources.tx_image_picker_settings

@Composable
fun ImagePickerHandler(
    showSourceOptionDialog: Boolean,
    onImageCaptured: (SharedImage) -> Unit,
    onDismiss: () -> Unit,
) {
    var launchCamera by remember { mutableStateOf(false) }
    var launchGallery by remember { mutableStateOf(false) }
    var launchSetting by remember { mutableStateOf(false) }
    var permissionRationalDialog by remember { mutableStateOf(false) }

    val permissionsManager = createPermissionsManager(object : PermissionCallback {
        override fun onPermissionStatus(
            permissionType: PermissionType,
            status: PermissionStatus,
        ) {
            when (status) {
                PermissionStatus.GRANTED -> {
                    when (permissionType) {
                        PermissionType.CAMERA -> launchCamera = true
                        PermissionType.GALLERY -> launchGallery = true
                    }
                }

                else -> permissionRationalDialog = true
            }
        }
    })

    val cameraManager = rememberCameraManager { result ->
        result?.let(onImageCaptured)
    }

    val galleryManager = rememberGalleryManager { result ->
        result?.let(onImageCaptured)
    }

    if (showSourceOptionDialog) {
        ContentDialog(stringResource(Res.string.tx_image_picker_add_photos), onDismiss = {
            onDismiss()
        }) {
            Text(stringResource(Res.string.tx_image_picker_choose_option))
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Button(onClick = {
                    onDismiss()
                    launchGallery = true
                }) { Text(stringResource(Res.string.tx_image_picker_gallery)) }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    onDismiss()
                    launchCamera = true
                }) { Text(stringResource(Res.string.tx_image_picker_camera)) }
            }
        }
    }

    if (launchGallery) {
        if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
            galleryManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.GALLERY)
        }
        launchGallery = false
    }

    if (launchCamera) {
        if (permissionsManager.isPermissionGranted(PermissionType.CAMERA)) {
            cameraManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.CAMERA)
        }
        launchCamera = false
    }

    if (launchSetting) {
        permissionsManager.launchSettings()
        launchSetting = false
    }

    if (permissionRationalDialog) {
        ConfirmDialog(
            title = stringResource(Res.string.tx_image_picker_permission_required),
            description = stringResource(Res.string.tx_image_picker_permission_description),
            primaryButtonText = stringResource(Res.string.tx_image_picker_settings),
            secondaryButtonText = stringResource(Res.string.tx_image_picker_cancel),
            onPrimaryClick = {
                permissionRationalDialog = false
                launchSetting = true
            },
            onSecondaryClick = {
                permissionRationalDialog = false
            }
        )
    }
}
