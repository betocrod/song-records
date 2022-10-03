package com.betocrod.features.song.impl.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.audios.api.models.Record
import com.betocrod.features.song.impl.R
import com.betocrod.features.song.impl.widgets.previewparameters.SampleRecordListProvider

@Composable
fun RecordListWidget(
    records: List<Record>,
    onMoreClick: (Record) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(8.dp)
) {
    if (records.isNotEmpty()) {
        NotEmptyListWidget(
            records = records,
            onMoreClick = { onMoreClick(it) },
            modifier = modifier,
            contentPadding = contentPadding
        )
    } else {
        EmptyListPlaceHolder(modifier)
    }
}

@Composable
fun EmptyListPlaceHolder(modifier: Modifier) {
    Box(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.feature_song_no_records_placeholder)
        )
    }
}

@Composable
fun NotEmptyListWidget(
    records: List<Record>,
    onMoreClick: (Record) -> Unit,
    modifier: Modifier,
    contentPadding: PaddingValues
) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
        contentPadding = contentPadding
    ) {
        items(records) {
            RecordItemWidget(
                record = it,
                onMoreClick = { onMoreClick(it) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Preview("Record List Widget")
@Composable
private fun PreviewSongListWidget(
    @PreviewParameter(SampleRecordListProvider::class) records: List<Record>
) {
    SongRecordsTheme {
        RecordListWidget(
            records = records,
            onMoreClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
