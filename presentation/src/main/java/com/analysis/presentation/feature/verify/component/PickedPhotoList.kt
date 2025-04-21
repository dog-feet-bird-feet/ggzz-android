package com.analysis.presentation.feature.verify.component

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun PickedPhotoList(
    selectedComparisonUris: List<Uri>,
    updatePickedComparisonUris: (List<Uri>) -> Unit,
    removeComparisonUri: (Uri) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
        state = rememberLazyGridState(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(top = 20.dp)
    ) {
        items(
            selectedComparisonUris.size + 1,
            span = { GridItemSpan(1) }) { index ->
            when (index) {
                0 -> PhotoPickerCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    maxSelectable = 5,
                    pickedPhotoCount = selectedComparisonUris.size,
                    onPickPhotos = {
                        updatePickedComparisonUris(it)
                    },
                )

                else -> HandWritingImageItemCard(
                    selectedComparisonUris[index - 1]
                ) { uri ->
                    removeComparisonUri(uri)
                }
            }
        }
    }
}
