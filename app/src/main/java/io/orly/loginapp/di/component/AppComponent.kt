package io.orly.loginapp.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.orly.loginapp.LoginApp
import io.orly.loginapp.di.module.AppModule
import io.orly.loginapp.di.module.DatabaseModule
import io.orly.loginapp.di.module.MainActivityModule
import io.orly.loginapp.di.module.ViewModelModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        DatabaseModule::class,
        ViewModelModule::class,
        MainActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(loginApp: LoginApp)
}
