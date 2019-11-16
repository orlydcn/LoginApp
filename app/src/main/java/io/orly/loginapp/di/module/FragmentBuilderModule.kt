package io.orly.loginapp.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.orly.loginapp.ui.login.LoginFragment
import io.orly.loginapp.ui.main_screen.MainFragment
import io.orly.loginapp.ui.register.RegisterFragment
import io.orly.loginapp.ui.splash_screen.SplashScreenFragment
import io.orly.loginapp.ui.welcome.WelcomeFragment

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashScreenFragment

    @ContributesAndroidInjector
    abstract fun contributeWelcomeFragment(): WelcomeFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
}