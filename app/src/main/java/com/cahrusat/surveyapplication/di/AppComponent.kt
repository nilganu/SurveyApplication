package com.cahrusat.surveyapplication.di

import com.cahrusat.surveyapplication.FamilyHeadActivityVillageViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivityViewModel: FamilyHeadActivityVillageViewModel)
    {

    }
}