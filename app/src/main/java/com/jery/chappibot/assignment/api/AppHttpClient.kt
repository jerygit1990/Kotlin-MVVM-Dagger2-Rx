package com.jery.chappibot.assignment.api

import okhttp3.OkHttpClient

class AppHttpClient {
    companion object {
        fun getHttpClient() : OkHttpClient {
            return OkHttpClient()
        }
    }
}