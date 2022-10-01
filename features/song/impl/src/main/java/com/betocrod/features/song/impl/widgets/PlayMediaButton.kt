package com.betocrod.features.song.impl.widgets

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.betocrod.designsystem.DSDrawable
import com.betocrod.features.audios.api.models.MediaData
import com.betocrod.features.song.impl.models.PlayerState
import com.betocrod.features.song.impl.widgets.compositionlocal.LocalPlayerState

@Composable
fun PlayMediaButton(
    onPlayClick: (MediaData) -> Unit,
    onPauseClick: (MediaData) -> Unit,
    mediaData: MediaData,
    modifier: Modifier = Modifier
) {
    val current = (LocalPlayerState.current as? PlayerState.Playing)?.mediaData
    IconButton(
        onClick = {
            when (current) {
                mediaData -> onPauseClick(current)
                else -> onPlayClick(mediaData)
            }
        }
    ) {
        val iconRes = if (current != mediaData) {
            DSDrawable.ic_play_circle
        } else {
            DSDrawable.ic_pause_circle
        }
        Icon(
            modifier = modifier.size(48.dp),
            painter = painterResource(id = iconRes),
            contentDescription = null
        )
    }
}
