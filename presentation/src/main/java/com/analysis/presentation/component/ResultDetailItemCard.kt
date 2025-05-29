package com.analysis.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.analysis.presentation.R
import com.analysis.presentation.model.ResultIndicator
import com.analysis.presentation.theme.Blue200
import com.analysis.presentation.theme.Blue300
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray200
import com.analysis.presentation.theme.White
import com.analysis.presentation.util.modifier.dropShadow

@Composable
fun ResultDetailItemCard(resultIndicator: ResultIndicator) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(15.dp),
                color = Color.Black.copy(0.05f),
                blur = 10.dp,
                offsetY = 2.dp,
                offsetX = 2.dp,
            ),
        colors = CardDefaults.cardColors().copy(containerColor = White),
        shape = RoundedCornerShape(15.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp)
                .padding(horizontal = 10.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(resultIndicator.title),
                    style = GgzzTheme.typography.pretendardBold24.copy(color = Blue300),
                )

                Text(
                    text = stringResource(R.string.percent, resultIndicator.percentage.toInt()),
                    style = GgzzTheme.typography.pretendardSemiBold30.copy(color = Blue300),
                )
            }

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = resultIndicator.description,
                style = GgzzTheme.typography.pretendardMedium16.copy(color = Blue200),
            )

            Spacer(modifier = Modifier.height(14.dp))

            GgzzLinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = resultIndicator.percentage/100,
                progressColor = resultIndicator.progressColor,
                backgroundColor = Gray200,
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ResultDetailItemCardPreview(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        val uiModel = ResultIndicator.Similarity(0.43f)
        ResultDetailItemCard(uiModel)
    }
}
