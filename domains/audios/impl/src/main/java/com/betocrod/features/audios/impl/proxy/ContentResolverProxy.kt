package com.betocrod.features.audios.impl.proxy

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.FileUtils
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class ContentResolverProxy @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun getAudioFile(uri: Uri): File? = withContext(Dispatchers.IO) {
        val fileName = getFileName(uri)
        val file = File(context.filesDir, fileName)
        file.createNewFile()
        val outputStream = FileOutputStream(file)
        context.contentResolver.openInputStream(uri)?.let { inputStream ->
            FileUtils.copy(inputStream, outputStream)
            outputStream.flush()
            file
        }
    }

    private fun getFileName(uri: Uri): String {
        val fileName: String = getFileNameFromCursor(uri) ?: randomName()
        val fileExtension = getFileExtension(uri)

        return if (!fileName.contains(".") && fileExtension != null) {
            "$fileName.$fileExtension"
        } else {
            fileName
        }
    }

    private fun getFileExtension(uri: Uri): String? {
        val fileType: String? = context.contentResolver.getType(uri)
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(fileType)
    }

    private fun randomName(): String {
        val pattern = "dd-MM-yyyy - HH:mm:ss"
        return SimpleDateFormat(
            pattern,
            Locale.getDefault()
        ).format(Date())
    }

    private fun getFileNameFromCursor(uri: Uri): String? {
        val fileCursor: Cursor? = context.contentResolver
            .query(
                /* uri = */ uri,
                /* projection = */ arrayOf(OpenableColumns.DISPLAY_NAME),
                /* selection = */ null,
                /* selectionArgs = */ null,
                /* sortOrder = */ null
            )
        return fileCursor?.moveToFirst()
            ?.takeIf { it }
            ?.let { fileCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME) }
            ?.takeIf { it != -1 }
            ?.let { fileCursor.getString(it) }
    }
}
