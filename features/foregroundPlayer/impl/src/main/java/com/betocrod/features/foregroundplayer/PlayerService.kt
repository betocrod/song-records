package com.betocrod.features.foregroundplayer

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayerService : Service() {

    @Inject
    lateinit var exoPlayer: ExoPlayer

    private lateinit var notificationManager: PlayerNotificationManager

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager = PlayerNotificationManager.Builder(
            /* context = */ this,
            /* notificationId = */ NOTIFICATION_ID,
            /* channelId = */ CHANNEL_ID
        ).build()
        notificationManager.setPlayer(exoPlayer)
    }

    override fun onDestroy() {
        if (exoPlayer.isPlaying) exoPlayer.stop()
        exoPlayer.release()
        stopForeground(STOP_FOREGROUND_REMOVE)
        super.onDestroy()
    }

    companion object {
        private const val NOTIFICATION_ID = 0x000
        private const val CHANNEL_ID = "CHANNEL_ID"
    }
}
