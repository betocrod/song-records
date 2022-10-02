package com.betocrod.features.foregroundplayer

import android.content.Context
import android.content.Intent
import com.betocrod.features.foregroundplayer.api.PlayerServiceLauncher
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PlayerServiceLauncherImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PlayerServiceLauncher {

    override fun start() {
        context.startService(Intent(context, PlayerService::class.java))
    }
}
