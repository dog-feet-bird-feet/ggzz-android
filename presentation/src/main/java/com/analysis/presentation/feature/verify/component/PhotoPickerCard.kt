package com.analysis.presentation.feature.verify.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.analysis.presentation.R
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Purple200
import com.analysis.presentation.theme.Purple500
import com.analysis.presentation.theme.Purple700

@Composable
internal fun PhotoPickerCard(
    pickedPhotoCount: Int,
    pickPhotos: (List<Uri>) -> Unit,
) {

    val pickMultipleMedia =
        rememberLauncherForActivityResult(PickMultipleVisualMedia(5)) { uris ->
            pickPhotos(uris)
        }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Purple200),
        border = BorderStroke(1.dp, Purple500),
        onClick = {
            pickMultipleMedia.launch(
                PickVisualMediaRequest.Builder()
                    .setMediaType(PickVisualMedia.ImageOnly)
                    .build()
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_camera),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .padding(horizontal = 45.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "사진을 첨부해주세요",
                style = GgzzTheme.typography.pretendardRegular14.copy(color = Purple700)
            )
            Text(
                text = "(${pickedPhotoCount} / 5)",
                style = GgzzTheme.typography.pretendardRegular10.copy(color = Purple700)
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PhotoPickerCardPreview(modifier: Modifier = Modifier) {
    PhotoPickerCard(1,{})
}
