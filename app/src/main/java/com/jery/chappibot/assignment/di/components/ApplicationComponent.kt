package com.jery.chappibot.assignment.di.components

import com.jery.chappibot.assignment.api.ApiServiceInterface
import com.jery.chappibot.assignment.di.modules.ApiModule
import com.jery.chappibot.assignment.di.modules.HttpClientModule
import dagger.Component

@Component(modules = arrayOf(ApiModule::class, HttpClientModule::class))
interface ApplicationComponent {

    fun getApi() : ApiServiceInterface
}