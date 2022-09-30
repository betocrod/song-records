package com.betocrod.features.audios.impl.wiring

import android.content.Context
import androidx.room.Room
import com.betocrod.features.audios.impl.database.SongRecordsDatabase
import com.betocrod.features.audios.impl.database.daos.SongDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoWiringModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SongRecordsDatabase {
        return Room.databaseBuilder(
            /* context = */ context,
            /* klass = */ SongRecordsDatabase::class.java,
            /* name = */ DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideSongsDao(database: SongRecordsDatabase): SongDao {
        return database.songDao()
    }

    companion object {
        private const val DATABASE_NAME = "song-records-database"
    }
}
