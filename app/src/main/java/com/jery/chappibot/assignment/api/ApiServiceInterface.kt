package com.jery.chappibot.assignment.api

import com.jery.chappibot.assignment.models.res.FeedRes
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("s/fy6ny7syutxl1yd/newsfeed.json?dl=1")
    fun getNewsFeed() : Observable<FeedRes>
}