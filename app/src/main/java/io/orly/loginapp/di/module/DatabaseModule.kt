package io.orly.loginapp.di.module

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import io.orly.loginapp.data.local.room.AppDatabase
import io.orly.loginapp.util.AppConstant
import io.orly.loginapp.util.AppExecutors
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application, appExecutors: AppExecutors) =
        AppDatabase.getInstance(app, appExecutors)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()
}