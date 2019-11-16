package io.orly.loginapp.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.orly.loginapp.data.local.preference.AppSharedPreference
import io.orly.loginapp.util.AppConstant


@Module
class AppModule {

    @Reusable
    @Provides
    fun provideSharePreference(app: Application) =
        AppSharedPreference(
            app.getSharedPreferences(
                AppConstant.PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        )


}