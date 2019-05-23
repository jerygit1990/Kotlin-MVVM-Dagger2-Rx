package com.jery.mvvm.example.di.components

import com.jery.mvvm.example.api.ApiServiceInterface
import com.jery.mvvm.example.di.modules.ApiModule
import com.jery.mvvm.example.di.modules.HttpClientModule
import dagger.Component

@Component(modules = arrayOf(ApiModule::class, HttpClientModule::class))
interface ApplicationComponent {

    fun getApi() : ApiServiceInterface
}