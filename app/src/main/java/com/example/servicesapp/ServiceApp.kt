package com.example.servicesapp

import android.app.Application
import com.example.servicesapp.di.ApplicationComponent
import com.example.servicesapp.di.DaggerApplicationComponent

class ServiceApp : Application() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .build()
    }
}