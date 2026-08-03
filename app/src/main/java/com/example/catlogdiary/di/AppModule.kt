package com.example.catlogdiary.di

import android.content.Context
import androidx.room.Room
import com.example.catlogdiary.data.database.CatDao
import com.example.catlogdiary.data.database.AppDatabase
import com.example.catlogdiary.data.repository.CatRepositoryImpl
import com.example.catlogdiary.domain.repository.CatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "catlog_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(db: AppDatabase): CatDao = db.catDao()

    @Provides
    @Singleton
    fun provideRepository(impl: CatRepositoryImpl): CatRepository = impl
}
