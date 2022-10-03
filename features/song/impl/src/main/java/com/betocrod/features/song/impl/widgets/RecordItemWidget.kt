package com.betocrod.features.song.impl.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.betocrod.designsystem.DSDrawable
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.designsystem.space.SmallSpace
import com.betocrod.features.audios.api.models.Record
import com.betocrod.features.song.impl.widgets.previewparameters.SampleRecordProvider

@Composable
fun RecordItemWidget(
    record: Record,
    onMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = record.title
            )
            SmallSpace()
            IconButton(onClick = { onMoreClick() }) {
                Icon(
                    painter = painterResource(id = DSDrawable.ic_more),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview("Record Item Widget")
@Composable
fun PreviewRecordItemWidget(@PreviewParameter(SampleRecordProvider::class) record: Record) {
    SongRecordsTheme {
        RecordItemWidget(record = record, modifier = Modifier.fillMaxWidth(), onMoreClick = {})
    }
}
