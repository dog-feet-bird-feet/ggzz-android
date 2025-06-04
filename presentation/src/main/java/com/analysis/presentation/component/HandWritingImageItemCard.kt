package com.analysis.presentation.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.analysis.presentation.R

@Composable
internal fun HandWritingImageItemCard(
    uri: Uri,
    onClickCancelButton: (Uri) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(8.dp),
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(uri),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }

        IconButton(
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.TopEnd)
                .offset(x = 9.dp, y = (-9).dp)
                .testTag("cancelButton"),
            onClick = { onClickCancelButton(uri) },
        ) {
            Image(
                painter = painterResource(R.drawable.ic_cancel),
                contentDescription = null,
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HandWritingImageItemCardPreview(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.padding(30.dp),
    ) {
        HandWritingImageItemCard(uri = Uri.EMPTY, onClickCancelButton = {})
    }
}
