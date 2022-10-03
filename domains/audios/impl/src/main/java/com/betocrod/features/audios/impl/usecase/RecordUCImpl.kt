package com.betocrod.features.audios.impl.usecase

import android.content.Context
import android.media.MediaRecorder
import com.betocrod.features.audios.api.usecases.RecordUC
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RecordUCImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : RecordUC {

    private var mediaRecorder: MediaRecorder? = null

    override fun start() {
        val path = getFilePath()
        mediaRecorder = MediaRecorder(context).apply {
            setOutputFile(path)
            prepare()
            start()
        }
    }

    private fun getFilePath(): String {
        val dir = getRecordsDir()
        val fileName = getFileName()
        return File(dir, fileName).absolutePath
    }

    override fun stop() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
    }

    private fun getRecordsDir(): File {
        val dir = File(context.filesDir, RECORD_DIR_NAME)
        if (!dir.isDirectory) {
            context.filesDir.mkdir()
        }
        return dir
    }

    private fun getFileName(): String {
        val pattern = "dd-MM-yyyy - HH:mm:ss"
        val dateTime = SimpleDateFormat(pattern, Locale.getDefault()).format(Date())
        return "record_$dateTime"
    }

    companion object {
        private const val RECORD_DIR_NAME = "records"
    }
}
