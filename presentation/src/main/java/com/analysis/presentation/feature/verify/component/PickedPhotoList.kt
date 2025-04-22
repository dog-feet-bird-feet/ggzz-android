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
import com.analysis.presentation.feature.verify.model.ComparisonPhotoItem

@Composable
internal fun PickedPhotoList(
    showErrorSnackBar: (Throwable) -> Unit,
    selectedComparisonUris: List<Uri>,
    updatePickedComparisonUris: (List<Uri>) -> Unit,
    removeComparisonUri: (Uri) -> Unit,
    modifier: Modifier = Modifier,
    columnCount: Int = 2,
) {
    val items = listOf(ComparisonPhotoItem.Picker) +
        selectedComparisonUris.map { ComparisonPhotoItem.Image(it) }

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(columnCount),
        state = rememberLazyGridState(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(top = 30.dp, bottom = 30.dp),
    ) {
        items(
            count = items.size,
            span = { GridItemSpan(1) },
            key = { index ->
                when (val item: ComparisonPhotoItem = items[index]) {
                    is ComparisonPhotoItem.Picker -> item.hashCode()
                    is ComparisonPhotoItem.Image -> item.uri
                }
            },
        ) { index ->
            when (val item = items[index]) {
                is ComparisonPhotoItem.Picker -> {
                    PhotoPickerCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        maxSelectable = 5,
                        showErrorSnackBar = showErrorSnackBar,
                        pickedPhotoCount = selectedComparisonUris.size,
                        onPickPhotos = updatePickedComparisonUris,
                    )
                }

                is ComparisonPhotoItem.Image -> {
                    HandWritingImageItemCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(1f)
                            .animateItem(),
                        uri = item.uri,
                        onClickCancelButton = { removeComparisonUri(item.uri) },
                    )
                }
            }
        }
    }
}
