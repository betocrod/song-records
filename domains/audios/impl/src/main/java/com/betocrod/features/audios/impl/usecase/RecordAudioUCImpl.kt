package com.betocrod.features.audios.impl.usecase

import android.content.Context
import android.media.MediaRecorder
import com.betocrod.features.audios.api.usecases.RecordAudioUC
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RecordAudioUCImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : RecordAudioUC {

    private var mediaRecorder: MediaRecorder? = null
    private var filePath = ""

    override fun start() {
        filePath = getFilePath()
        mediaRecorder = MediaRecorder(context).apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(filePath)
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

    override fun stop(): String {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
        return filePath
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
