package com.jery.mvvm.example.models.res

import com.google.gson.annotations.SerializedName

data class FeedRes (
    @SerializedName("items")
    val feeds: List<FeedInfoRes>)