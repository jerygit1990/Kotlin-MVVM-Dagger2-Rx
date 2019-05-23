package com.jery.mvvm.example.di.modules

import com.jery.mvvm.example.api.ApiServiceInterface
import com.jery.mvvm.example.configs.ApiConfigs
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideApi(httpClient: OkHttpClient) : ApiServiceInterface {
        val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConfigs.BASE_URL)
                .client(httpClient)
                .build()

        return retrofit.create(ApiServiceInterface::class.java)
    }
}