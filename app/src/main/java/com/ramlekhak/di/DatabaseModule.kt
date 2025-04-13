package com.ramlekhak.di

import android.content.Context
import android.content.SharedPreferences
import com.ramlekhak.data.AppDatabase
import com.ramlekhak.data.dao.CountDao
import com.ramlekhak.data.CountRepository
import com.ramlekhak.data.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }
    
    @Provides
    fun provideCountDao(database: AppDatabase): CountDao {
        return database.countDao()
    }
    
    @Singleton
    @Provides
    fun provideRepository(countDao: CountDao): CountRepository {
        return CountRepository(countDao)
    }

    @Singleton
    @Provides
    fun provideUserPreferences(@Named("default_preferences") prefs: SharedPreferences): UserPreferences {
        return UserPreferences(prefs)
    }
} 