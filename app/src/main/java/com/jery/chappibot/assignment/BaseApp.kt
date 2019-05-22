package com.jery.chappibot.assignment

import android.app.Application
import com.jery.chappibot.assignment.di.components.ApplicationComponent
import com.jery.chappibot.assignment.di.components.DaggerApplicationComponent

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