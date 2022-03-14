package com.cahrusat.surveyapplication.di

import android.app.Application
import android.content.Context
import com.cahrusat.surveyapplication.database.AppDatabase
import com.cahrusat.surveyapplication.database.VillageDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application:Application) {
    @Singleton
    @Provides
    fun getVillageDao(appDatabase: AppDatabase):VillageDao
    {
        return appDatabase.getVillageDao()
    }
    @Singleton
    @Provides
    fun getRoomDbInstance():AppDatabase{
        return AppDatabase.getAppDatabaseInstance(provideAppContext())
    }
    @Singleton
    @Provides
    fun provideAppContext():Context
    {
        return application.applicationContext
    }
}