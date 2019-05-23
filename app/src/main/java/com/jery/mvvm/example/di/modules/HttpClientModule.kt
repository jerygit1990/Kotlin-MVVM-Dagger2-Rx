package com.jery.mvvm.example.di.modules

import com.jery.mvvm.example.api.AppHttpClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class HttpClientModule {

    @Provides
    fun provideHttpClient() : OkHttpClient {
        return AppHttpClient.getHttpClient()
    }
}