package io.orly.loginapp.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.orly.loginapp.MainActivity


@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity
}