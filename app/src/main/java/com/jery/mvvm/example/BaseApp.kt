package com.jery.mvvm.example

import android.app.Application
import com.jery.mvvm.example.di.components.ApplicationComponent
import com.jery.mvvm.example.di.components.DaggerApplicationComponent

class BaseApp : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        config()
        instance = this
    }

    private fun config() {
        component = DaggerApplicationComponent.builder().build()
    }

    fun getApplicationComponent() : ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp
    }
}