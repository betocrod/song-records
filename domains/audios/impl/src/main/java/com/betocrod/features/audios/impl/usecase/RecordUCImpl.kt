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
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(path)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            prepare()
            start()
        }
    }

    private fun getFilePath(): String {
        val dir = getRecordsDir()
        val fileName = getFileName()
        val file = File(dir, fileName)
        file.createNewFile()
        return file.absolutePath
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
            dir.mkdir()
        }
        return dir
    }

    private fun getFileName(): String {
        val pattern = "dd-MM-yyyy - HH:mm:ss"
        val dateTime = SimpleDateFormat(pattern, Locale.getDefault()).format(Date())
        return "record_$dateTime.3gp"
    }

    companion object {
        private const val RECORD_DIR_NAME = "records"
    }
}
