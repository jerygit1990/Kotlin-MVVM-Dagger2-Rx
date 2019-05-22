package com.jery.chappibot.assignment.di.modules

import com.jery.chappibot.assignment.api.AppHttpClient
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