package com.team.krontesttask.app

import android.app.Application
import com.team.krontesttask.di.AppComponent
import com.team.krontesttask.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .create()
    }
}