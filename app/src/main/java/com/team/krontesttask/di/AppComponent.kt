package com.team.krontesttask.di

import com.team.krontesttask.presentation.MainFragment
import com.team.krontesttask.presentation.UserDetailFragment
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class])
interface AppComponent {

    fun inject(fragment: UserDetailFragment)

    fun inject(fragment: MainFragment)
}