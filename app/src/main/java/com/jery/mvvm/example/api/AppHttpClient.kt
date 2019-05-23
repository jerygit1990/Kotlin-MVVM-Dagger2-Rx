package com.jery.mvvm.example.api

import okhttp3.OkHttpClient

class AppHttpClient {
    companion object {
        fun getHttpClient() : OkHttpClient {
            return OkHttpClient()
        }
    }
}