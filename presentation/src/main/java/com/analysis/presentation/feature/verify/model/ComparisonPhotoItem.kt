
package com.analysis.presentation.feature.verify.model

import android.net.Uri

sealed interface ComparisonPhotoItem {
    data object Picker : ComparisonPhotoItem

    data class Image(val uri: Uri) : ComparisonPhotoItem
}
