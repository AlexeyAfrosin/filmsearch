package com.afrosin.filmsearch.di.module.settings

import com.afrosin.filmsearch.view.setting.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class SettingsDiModule {
    @ContributesAndroidInjector
    abstract fun bindSettingsFragment(): SettingsFragment
}
