package com.jery.chappibot.assignment.models.res

import com.google.gson.annotations.SerializedName

data class FeedRes (
    @SerializedName("items")
    val feeds: List<FeedInfoRes>)