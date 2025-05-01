package com.analysis.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.analysis.presentation.R
import com.analysis.presentation.model.DropMenuItem
import com.analysis.presentation.theme.Black
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray300
import com.analysis.presentation.theme.White

@Composable
fun GgzzDropMenuButton(
    items: List<DropMenuItem>,
    modifier: Modifier = Modifier,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = expanded.not() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_kebab_menu),
                contentDescription = null,
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset((-20).dp, (-8).dp),
            modifier = modifier.background(White, shape = RoundedCornerShape(5.dp)),
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = item.label,
                                style = GgzzTheme.typography.pretendardRegular14.copy(color = Black),
                            )
                        }
                    },
                    onClick = {
                        expanded = false
                        item.onClick()
                    },
                )

                if (index != items.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .height(1.dp),
                        color = Gray300,
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun GgzzDropMenuPreview(modifier: Modifier = Modifier) {
    val items = listOf(DropMenuItem("수정하기", {}), DropMenuItem("삭제하기", {}))

    Column(modifier = Modifier.fillMaxSize()) {
        GgzzDropMenuButton(items = items)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            GgzzDropMenuButton(items = items)
        }
    }
}
