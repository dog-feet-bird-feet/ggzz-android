package com.analysis.presentation.feature.verify.component

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.analysis.presentation.R
import com.analysis.presentation.feature.verify.model.ImageValidator
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Purple200
import com.analysis.presentation.theme.Purple500
import com.analysis.presentation.theme.Purple700

@Composable
internal fun PhotoPickerCard(
    modifier: Modifier = Modifier,
    maxSelectable: Int = 1,
    pickedPhotoCount: Int = 0,
    showErrorSnackBar: (Throwable) -> Unit,
    onPickPhoto: (Uri) -> Unit = {},
    onPickPhotos: (List<Uri>) -> Unit = {},
) {
    val context = LocalContext.current
    val contentResolver = context.contentResolver

    val pickMediaLauncher = rememberLauncherForActivityResult(
        contract = if (maxSelectable == 1) {
            PickVisualMedia()
        } else {
            PickMultipleVisualMedia(
                maxSelectable,
            )
        },
    ) { result ->
        handlePickResult(
            result = result,
            maxSelectable = maxSelectable,
            context = context,
            contentResolver = contentResolver,
            showError = showErrorSnackBar,
            onPickPhoto = onPickPhoto,
            onPickPhotos = onPickPhotos,
        )
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Purple200),
        border = BorderStroke(1.dp, Purple500),
        onClick = {
            pickMediaLauncher.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_camera),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .padding(horizontal = 45.dp),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.verify_pick_photo_message),
                style = GgzzTheme.typography.pretendardRegular14.copy(color = Purple700),
            )

            if (maxSelectable > 1) {
                Text(
                    text = stringResource(R.string.photo_count, pickedPhotoCount, maxSelectable),
                    style = GgzzTheme.typography.pretendardRegular10.copy(color = Purple700),
                )
            }
        }
    }
}

private fun handlePickResult(
    result: Any?,
    maxSelectable: Int,
    context: Context,
    contentResolver: ContentResolver,
    showError: (Throwable) -> Unit,
    onPickPhoto: (Uri) -> Unit,
    onPickPhotos: (List<Uri>) -> Unit,
) {
    if (pickSinglePhotoAvailable(maxSelectable, result)) {
        val uri = result as Uri
        if (ImageValidator.isValid(uri, contentResolver)) {
            onPickPhoto(uri)
        } else {
            showError(IllegalArgumentException(context.getString(R.string.verify_invalid_photo)))
        }
    } else if (pickMultiPhotosAvailable(maxSelectable, result)) {
        val uris = (result as List<*>).filterIsInstance<Uri>()
        val validUris = uris.filter { ImageValidator.isValid(it, contentResolver) }

        if (validUris.size != uris.size) {
            showError(IllegalArgumentException(context.getString(R.string.verify_invalid_photo)))
        }
        onPickPhotos(validUris)
    }
}

private fun pickMultiPhotosAvailable(
    maxSelectable: Int,
    result: Any?,
) = maxSelectable > 1 && result is List<*> && result.isNotEmpty()

private fun pickSinglePhotoAvailable(
    maxSelectable: Int,
    result: Any?,
) = maxSelectable == 1 && result is Uri && result != Uri.EMPTY

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PhotoPickerCardPreview(modifier: Modifier = Modifier) {
    PhotoPickerCard(
        showErrorSnackBar = {},
        maxSelectable = 5,
        pickedPhotoCount = 1,
    )
}
