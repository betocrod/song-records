package com.betocrod.features.audios.impl.resource

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppResources @Inject constructor(@ApplicationContext private val context: Context) {

    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }
}
