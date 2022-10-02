package com.betocrod.features.foregroundplayer

import android.app.Notification
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import com.betocrod.designsystem.DSDrawable
import com.betocrod.features.foregroundplayer.api.PlayerDatasource
import com.betocrod.features.foregroundplayer.impl.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.ui.PlayerNotificationManager.MediaDescriptionAdapter
import com.google.android.exoplayer2.ui.PlayerNotificationManager.NotificationListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayerService : Service() {

    private lateinit var mediaSession: MediaSessionCompat

    @Inject
    lateinit var exoPlayer: ExoPlayer

    @Inject
    lateinit var playerDataSource: PlayerDatasource

    private lateinit var notificationManager: PlayerNotificationManager

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaSession = MediaSessionCompat(applicationContext, getString(R.string.channel_name))
        mediaSession.isActive = true
        notificationManager = PlayerNotificationManager.Builder(
            /* context = */ this,
            /* notificationId = */ NOTIFICATION_ID,
            /* channelId = */ CHANNEL_ID
        )
            .setNotificationListener(notificationListener)
            .setMediaDescriptionAdapter(descriptionAdapter)
            .setSmallIconResourceId(DSDrawable.ic_music_note)
            .setChannelImportance(IMPORTANCE_HIGH)
            .setPauseActionIconResourceId(DSDrawable.ic_pause_circle)
            .setPlayActionIconResourceId(DSDrawable.ic_play_circle)
            .setChannelNameResourceId(R.string.channel_name)
            .build()
        notificationManager.setMediaSessionToken(mediaSession.sessionToken)
        notificationManager.setPlayer(exoPlayer)
        notificationManager.setPriority(NotificationCompat.PRIORITY_MAX)
        notificationManager.setUseRewindAction(false)
        notificationManager.setUseFastForwardAction(false)
        notificationManager.setUseNextAction(false)
        notificationManager.setUsePreviousAction(false)
    }

    private val notificationListener = object : NotificationListener {
        override fun onNotificationCancelled(notificationId: Int, dismissedByUser: Boolean) {
            super.onNotificationCancelled(notificationId, dismissedByUser)
            stopForeground(STOP_FOREGROUND_REMOVE)
            if (exoPlayer.isPlaying) exoPlayer.pause()
        }

        override fun onNotificationPosted(
            notificationId: Int,
            notification: Notification,
            ongoing: Boolean
        ) {
            super.onNotificationPosted(notificationId, notification, ongoing)
            startForeground(notificationId, notification)
        }
    }

    private val descriptionAdapter = object : MediaDescriptionAdapter {
        override fun getCurrentContentTitle(player: Player): CharSequence {
            return playerDataSource.currentTitle
        }

        override fun createCurrentContentIntent(player: Player): PendingIntent? {
            return null
        }

        override fun getCurrentContentText(player: Player): CharSequence? {
            return playerDataSource.currentContentText
        }

        override fun getCurrentLargeIcon(
            player: Player,
            callback: PlayerNotificationManager.BitmapCallback
        ): Bitmap? {
            return playerDataSource.currentBitmap
        }

    }

    override fun onDestroy() {
        if (exoPlayer.isPlaying) exoPlayer.stop()
        exoPlayer.release()
        notificationManager.setPlayer(null)
        stopForeground(STOP_FOREGROUND_REMOVE)
        mediaSession.release()
        super.onDestroy()
    }

    companion object {
        private const val NOTIFICATION_ID = 0x001
        private const val CHANNEL_ID = "CHANNEL_ID"
    }
}
