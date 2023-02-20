package com.example.servicesapp.di

import com.example.servicesapp.presentation.activities.MainActivity
import com.example.servicesapp.presentation.activities.ServiceActivity
import dagger.Component

@ApplicationScope
@Component(modules = [Module::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: ServiceActivity)
}